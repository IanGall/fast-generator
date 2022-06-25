package net.maku.generator.controller;

import lombok.AllArgsConstructor;
import net.maku.generator.common.page.PageResult;
import net.maku.generator.common.query.Query;
import net.maku.generator.common.utils.Result;
import net.maku.generator.entity.DataDictionaryEntity;
import net.maku.generator.service.DataDictionaryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 数据字典表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-06-25
*/
@RestController
@RequestMapping("datadictionary")
@AllArgsConstructor
public class DataDictionaryController {
    private final DataDictionaryService dataDictionaryService;

    @GetMapping("page")
    public Result<PageResult<DataDictionaryEntity>> page( Query query){
        PageResult<DataDictionaryEntity> page = dataDictionaryService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    public Result<DataDictionaryEntity> get(@PathVariable("id") Long id){
        DataDictionaryEntity entity = dataDictionaryService.getById(id);

        return Result.ok(entity);
    }

    @PostMapping
    public Result<String> save(@RequestBody DataDictionaryEntity vo){
        dataDictionaryService.save(vo);

        return Result.ok();
    }

    @PutMapping
    public Result<String> update(@RequestBody @Valid DataDictionaryEntity vo){
        dataDictionaryService.updateById(vo);

        return Result.ok();
    }

    @DeleteMapping
    public Result<String> delete(@RequestBody List<Long> idList){
        dataDictionaryService.removeBatchByIds(idList);

        return Result.ok();
    }
}