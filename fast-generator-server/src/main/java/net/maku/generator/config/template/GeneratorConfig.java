package net.maku.generator.config.template;

import lombok.Data;
import net.maku.generator.common.exception.FastException;
import net.maku.generator.common.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Configuration
@Data
public class GeneratorConfig {
    @Value("${fast.template}")
    private String template;

    @Value("classpath:template")
    private File path;


    @Value("${user.home}")
    private String userHome;

    public List<String> getPaths() {
        LinkedList<String> dirs = new LinkedList<>();
        for (File file : Objects.requireNonNull(path.listFiles())) {
            if (file.isDirectory()) {
                dirs.add(file.getName());
            }
        }
        return dirs;
    }

    ;

    // public List<File> getPaths(){
    //     LinkedList<File> dirs = new LinkedList<>();
    //     for (File file : Objects.requireNonNull(path.listFiles())) {
    //         if (file.isDirectory()) {
    //             dirs.add(file);
    //         }
    //     }
    //     return dirs;
    // };
    //

    public GeneratorInfo getGeneratorConfig(String path) {
        // String template1 = getPaths().get(0);
        if (StringUtils.isBlank(path)) {
            throw new FastException("模板不存在，需指定模板");
        }

        // 模板所在路径
        String templatePath = "/template/" + path + "/";

        // 模板配置文件
        InputStream isConfig = this.getClass().getResourceAsStream(templatePath + "config.json");
        if (isConfig == null) {
            throw new FastException("模板配置文件，config.json不存在");
        }

        try {
            // 读取模板配置文件
            String configContent = StreamUtils.copyToString(isConfig, StandardCharsets.UTF_8);
            GeneratorInfo info = JsonUtils.parseObject(configContent, GeneratorInfo.class);
            assert info != null;

            ProjectInfo project = info.getProject();
            String defaultPath = userHome + "\\generator";
            if (StringUtils.isNotBlank(project.getProjectName())) {
                defaultPath = defaultPath + "\\" + project.getProjectName();
            }
            project.setBackendPath(defaultPath + project.getBackendPath());
            project.setFrontendPath(defaultPath + project.getFrontendPath());
            for (TemplateInfo templateInfo : info.getTemplates()) {
                // 模板文件
                InputStream isTemplate = this.getClass().getResourceAsStream(templatePath + templateInfo.getTemplateName());
                if (isTemplate == null) {
                    throw new FastException("模板文件 " + templateInfo.getTemplateName() + " 不存在");
                }
                // 读取模板内容
                String templateContent = StreamUtils.copyToString(isTemplate, StandardCharsets.UTF_8);

                templateInfo.setTemplateContent(templateContent);
            }
            return info;
        } catch (IOException e) {
            throw new FastException("读取config.json配置文件失败");
        }
    }
}
