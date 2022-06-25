package net.maku.generator.config.template;

import lombok.Data;

@Data
public class TemplateInfo {
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 模块名
     */
    private String module;
    /**
     * 模块包名
     */
    private String packageName;
    /**
     * 生成代码的路径
     */
    private String generatorPath;
}
