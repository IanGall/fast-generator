package net.maku.generator.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.maku.generator.common.enumration.DataDictionary;
import net.maku.generator.common.exception.FastException;
import net.maku.generator.common.utils.DateUtils;
import net.maku.generator.config.DataSourceInfo;
import net.maku.generator.config.template.GeneratorConfig;
import net.maku.generator.config.template.GeneratorInfo;
import net.maku.generator.config.template.TemplateInfo;
import net.maku.generator.dto.ClearanceInfoTable;
import net.maku.generator.entity.*;
import net.maku.generator.service.*;
import net.maku.generator.utils.DbUtils;
import net.maku.generator.utils.GenUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 代码生成
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@Slf4j
@AllArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    private final TableInfoService tableInfoService;
    private final TableFieldService tableFieldService;
    private final DataSourceService datasourceService;
    private final FieldTypeService fieldTypeService;
    private final BaseClassService baseClassService;
    private final DataSource dataSource;
    private final DataDictionaryService dataDictionaryService;
    private final GeneratorConfig generatorConfig;

    @Override
    public DataSourceInfo getDataSourceInfo(Long datasourceId) {
        //初始化配置信息
        DataSourceInfo info = null;
        if (datasourceId.intValue() == 0) {
            try {
                info = new DataSourceInfo(dataSource.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            info = new DataSourceInfo(datasourceService.getById(datasourceId));
        }

        return info;
    }


    @Override
    public void datasourceTable(TableInfoEntity tableInfo) {
        //初始化配置信息
        DataSourceInfo info = getDataSourceInfo(tableInfo.getDatasourceId());

        TableInfoEntity table = tableInfoService.getByTableNameAndTableOwner(tableInfo.getTableName(), tableInfo.getTableOwner());
        //表存在
        if (table != null) {
            throw new FastException(tableInfo.getTableName() + "数据表已存在");
        }

        // table = DbUtils.getTablesInfo(info, tableInfo.getTableName());
        table = tableInfo;

        //代码生成器信息
        GeneratorInfo generator = generatorConfig.getGeneratorConfig(tableInfo.getTemplatePath());

        //保存表信息
        assert table != null;
        table.setPackageName(generator.getProject().getPackageName());
        table.setVersion(generator.getProject().getVersion());
        table.setBackendPath(generator.getProject().getBackendPath());
        table.setFrontendPath(generator.getProject().getFrontendPath());
        table.setAuthor(generator.getDeveloper().getAuthor());
        table.setEmail(generator.getDeveloper().getEmail());
        tableInfoService.save(table);

        //获取原生列数据
        List<TableFieldEntity> tableFieldList = DbUtils.getTableColumns(info, table.getId(), tableInfo.getTableName(),tableInfo.getTableOwner());
        //初始化列数据
        initFieldList(tableFieldList);
        //批量保存列数据
        tableFieldService.saveBatch(tableFieldList);

        try {
            //释放数据源
            info.getConnection().close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateTableField(Long tableId, List<TableFieldEntity> tableFieldList) {
        //删除旧列信息
        tableFieldService.deleteBatchTableIds(new Long[]{tableId});

        //保存新列数据
        int sort = 0;
        for (TableFieldEntity tableField : tableFieldList) {
            tableField.setSort(sort++);
            tableFieldService.save(tableField);
        }

    }

    /**
     * 初始化列数据
     */
    private void initFieldList(List<TableFieldEntity> tableFieldList) {
        //字段类型、属性类型映射
        Map<String, FieldTypeEntity> fieldTypeMap = fieldTypeService.getMap();
        int index = 0;
        for (TableFieldEntity tableField : tableFieldList) {
            tableField.setAttrName(StringUtils.uncapitalize(GenUtils.columnToJava(tableField.getColumnName())));
            //获取字段对应的类型
            FieldTypeEntity fieldTypeMapping = fieldTypeMap.get(tableField.getColumnType().toLowerCase());
            if (fieldTypeMapping == null) {
                //没找到对应的类型，则为Object类型
                tableField.setAttrType("Object");
            } else {
                tableField.setAttrType(fieldTypeMapping.getAttrType());
                tableField.setPackageName(fieldTypeMapping.getPackageName());
            }

            tableField.setList(true);
            tableField.setForm(true);

            tableField.setQueryType("=");
            tableField.setFormType("text");

            tableField.setSort(index++);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generatorCode(TableInfoEntity tableInfo) throws Exception {
        //代码生成器信息
        GeneratorInfo generator = generatorConfig.getGeneratorConfig(tableInfo.getTemplatePath());

        tableInfo.setBackendPath(generator.getProject().getBackendPath());
        tableInfo.setFrontendPath(generator.getProject().getFrontendPath());

        //保存表信息
        tableInfoService.updateById(tableInfo);

        //删除旧表信息
        tableInfoService.removeById(tableInfo.getId());
        //删除旧列信息
        tableFieldService.deleteByTableId(tableInfo.getId());

        //保存新表信息
        tableInfoService.save(tableInfo);

        List<TableFieldEntity> fields = tableInfo.getFields();
        //保存新列信息
        tableFieldService.saveBatch(fields);

        handleFields(tableInfo, fields);

        //数据模型
        Map<String, Object> dataModel = new HashMap<>();
        //项目信息
        dataModel.put("package", tableInfo.getPackageName());
        dataModel.put("packagePath", tableInfo.getPackageName().replace(".", File.separator));
        dataModel.put("version", tableInfo.getVersion());

        String moduleName = tableInfo.getModuleName();
        if (StringUtils.isBlank(moduleName)) {
            moduleName = null;
        }
        dataModel.put("moduleName", moduleName);

        String subModuleName = tableInfo.getSubModuleName();
        if (StringUtils.isBlank(subModuleName)) {
            subModuleName = null;
        }
        dataModel.put("subModuleName", subModuleName);
        dataModel.put("backendPath", tableInfo.getBackendPath());
        dataModel.put("frontendPath", tableInfo.getFrontendPath());
        //开发者信息
        dataModel.put("author", tableInfo.getAuthor());
        dataModel.put("email", tableInfo.getEmail());
        dataModel.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        dataModel.put("date", DateUtils.format(new Date(), DateUtils.DATE_PATTERN));
        //表信息
        dataModel.put("tableName", tableInfo.getTableName());
        dataModel.put("tableComment", tableInfo.getTableComment());
        dataModel.put("ClassName", tableInfo.getClassName());
        dataModel.put("className", StringUtils.uncapitalize(tableInfo.getClassName()));
        dataModel.put("classname", tableInfo.getClassName().toLowerCase());
        dataModel.put("columnList", fields);

        //主键
        for (TableFieldEntity tableField : fields) {
            if (tableField.isPk()) {
                dataModel.put("pk", tableField);
                break;
            }
        }

        //导入的包列表
        Set<String> imports = fieldTypeService.getPackageListByTableId(tableInfo.getId());
        //过滤为空的数据
        imports = imports.stream().filter(i -> StringUtils.isNotBlank(i)).collect(Collectors.toSet());
        dataModel.put("imports", imports);

        //基类
        if (tableInfo.getBaseclassId() != null) {
            BaseClassEntity baseClassEntity = baseClassService.getById(tableInfo.getBaseclassId());
            baseClassEntity.setPackageName(GenUtils.getTemplateContent(baseClassEntity.getPackageName(), dataModel));
            dataModel.put("baseClassEntity", baseClassEntity);
        }

        List<TemplateInfo> templates = generator.getTemplates();

        for (TemplateInfo template : templates) {
            if (StringUtils.isBlank(template.getTemplateType())) {
                continue;
            }

            String key = template.getTemplateType() + "Package";
            if (dataModel.put(key, template.getPackageName()) != null) {
                throw new FastException("属性冲突");
            }
        }

        //渲染模板并输出
        for (TemplateInfo template : templates) {
            dataModel.put("templateName", template.getTemplateName());
            if (StringUtils.isNotBlank(template.getPackageName())) {
                dataModel.put("package", template.getPackageName());
                dataModel.put("packagePath", template.getPackageName().replace(".", File.separator));
            }
            dataModel.put("module", template.getModule());
            String content = GenUtils.getTemplateContent(template.getTemplateContent(), dataModel);
            String path = GenUtils.getTemplateContent(template.getGeneratorPath(), dataModel);
            //FileUtil.writeUtf8String(content, path);
            FileUtils.writeStringToFile(new File(path), content, "utf-8");
            System.out.println(path);
        }
    }

    private void handleFields(TableInfoEntity tableInfo, List<TableFieldEntity> fields) {
        DataDictionaryEntity dataDic = dataDictionaryService.getById(tableInfo.getDataDicId());
        if (dataDic == null) {
            return;
        }
        List<ClearanceInfoTable> contentObjs = dataDic.getContentObj(DataDictionary.CLEARANCE_INFO.getTypeReference());
        // LinkedHashMap<String, ClearanceInfoTable> columnNameToTableObj = contentObj.stream()
        //         .collect(Collectors.toMap(ClearanceInfoTable::getColumnName, clearanceInfoTable -> clearanceInfoTable
        //                 , (o1, o2) -> o1, LinkedHashMap::new));
        //
        // columnNameToTableObj.in

        Map<String, TableFieldEntity> columnNameToField = fields.stream()
                .collect(Collectors.toMap(TableFieldEntity::getColumnName, tableFieldEntity -> tableFieldEntity));

        for (int i = 0; i < contentObjs.size(); i++) {
            ClearanceInfoTable table = contentObjs.get(i);
            TableFieldEntity field = columnNameToField.get(table.getColumnName());
            if (field == null) {
                continue;
            }
            field.getParamNameList().add(table.getParamName());
            field.setDicOrder(i);
        }

        fields.sort(Comparator.comparingInt(TableFieldEntity::getDicOrder));
    }

    @Override
    public List<String> templatePaths() {
        return generatorConfig.getPaths();
    }

}