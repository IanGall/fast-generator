create table gen_datasource
(
    id          bigint auto_increment comment 'id'
        primary key,
    db_type     varchar(200) null comment '数据库类型 MySQL、Oracle、PostgreSQL',
    conn_name   varchar(200) not null comment '连接名',
    conn_url    varchar(500) null comment 'URL',
    username    varchar(200) null comment '用户名',
    password    varchar(200) null comment '密码',
    create_time datetime     null comment '创建时间'
)
    comment '数据源管理' charset = utf8mb4;



create table gen_field_type
(
    id           bigint auto_increment comment 'id'
        primary key,
    column_type  varchar(200) null comment '字段类型',
    attr_type    varchar(200) null comment '属性类型',
    package_name varchar(200) null comment '属性包名',
    create_time  datetime     null comment '创建时间',
    constraint column_type
        unique (column_type)
)
    comment '字段类型管理' charset = utf8mb4;

create table gen_base_class
(
    id           bigint auto_increment comment 'id'
        primary key,
    package_name varchar(200) null comment '基类包名',
    code         varchar(200) null comment '基类编码',
    fields       varchar(500) null comment '基类字段，多个用英文逗号分隔',
    remark       varchar(200) null comment '备注',
    create_time  datetime     null comment '创建时间'
)
    comment '基类管理' charset = utf8mb4;


create table gen_table_info
(
    id              bigint auto_increment comment 'id'
        primary key,
    table_name      varchar(200)                     null comment '表名',
    class_name      varchar(200)                     null comment '类名',
    table_comment   varchar(200)                     null comment '功能名',
    author          varchar(200)                     null comment '作者',
    email           varchar(200)                     null comment '邮箱',
    package_name    varchar(200)                     null comment '项目包名',
    version         varchar(200)                     null comment '项目版本号',
    backend_path    varchar(500)                     null comment '后端生成路径',
    frontend_path   varchar(500)                     null comment '前端生成路径',
    module_name     varchar(200)                     null comment '模块名',
    sub_module_name varchar(200)                     null comment '子模块名',
    datasource_id   bigint                           null comment '数据源ID',
    baseclass_id    bigint                           null comment '基类ID',
    create_time     datetime                         null comment '创建时间',
    template_path   varchar(200) default 'fast-boot' null comment '模板路径',
    data_dic_id     bigint                           null comment '数据字典id',
    constraint table_name
        unique (table_name)
)
    comment '代码生成表' charset = utf8mb4;



create table gen_table_field
(
    id                       bigint auto_increment comment 'id'
        primary key,
    table_id                 bigint       null comment '表ID',
    table_name               varchar(200) null comment '表名',
    column_name              varchar(200) null comment '列名',
    column_type              varchar(200) null comment '类型',
    column_comment           varchar(200) null comment '列说明',
    attr_name                varchar(200) null comment '属性名',
    attr_type                varchar(200) null comment '属性类型',
    package_name             varchar(200) null comment '属性包名',
    is_pk                    tinyint      null comment '是否主键 0：否  1：是',
    is_required              tinyint      null comment '是否必填 0：否  1：是',
    is_form                  tinyint      null comment '是否表单字段 0：否  1：是',
    is_list                  tinyint      null comment '是否列表字段 0：否  1：是',
    is_query                 tinyint      null comment '是否查询字段 0：否  1：是',
    query_type               varchar(200) null comment '查询方式',
    form_type                varchar(200) null comment '表单类型',
    dict_name                varchar(200) null comment '字典名称',
    validator_type           varchar(200) null comment '效验方式',
    sort                     int          null comment '排序',
    create_time              datetime     null comment '创建时间',
    character_maximum_length int          null comment '字符串最大长度',
    numeric_precision        int          null comment '数字精度',
    numeric_scale            int          null comment '数字刻度'
)
    comment '代码生成表列' charset = utf8mb4;

create index table_name
    on gen_table_field (table_name);



create table data_dictionary
(
    id            bigint auto_increment comment 'id'
        primary key,
    table_name    varchar(200) not null comment '表名',
    datasource_id bigint       not null comment '数据源ID',
    key_column    varchar(200) not null comment 'key所在column',
    content       json         not null comment '内容',
    create_time   datetime     null comment '创建时间'
)
    comment '数据字典表';


-- 用于测试代码生成器的表结构 --
CREATE TABLE gen_test_member
(
    member_id   bigint NOT NULL AUTO_INCREMENT COMMENT '会员ID',
    name        varchar(50) COMMENT '姓名',
    gender      tinyint COMMENT '性别',
    age         int COMMENT '年龄',
    occupation  varchar(100) COMMENT '职业',
    create_time datetime COMMENT '创建时间',
    PRIMARY KEY (member_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='测试1';

CREATE TABLE gen_test_student
(
    id          bigint NOT NULL AUTO_INCREMENT COMMENT '学生ID',
    name        varchar(50) COMMENT '姓名',
    gender      tinyint COMMENT '性别',
    age         int COMMENT '年龄',
    class_name  varchar(50) COMMENT '班级',
    version     int COMMENT '版本号',
    deleted     tinyint COMMENT '删除标识',
    creator     bigint COMMENT '创建者',
    create_time datetime COMMENT '创建时间',
    updater     bigint COMMENT '更新者',
    update_time datetime COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='测试2';



INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (1, 'datetime', 'Date', 'java.util.Date', now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (2, 'date', 'Date', 'java.util.Date', now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (3, 'tinyint', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (4, 'smallint', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (5, 'mediumint', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (6, 'int', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (7, 'integer', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (8, 'bigint', 'Long', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (9, 'float', 'Float', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (10, 'double', 'Double', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (11, 'decimal', 'BigDecimal', 'java.math.BigDecimal', now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (12, 'bit', 'Boolean', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (13, 'char', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (14, 'varchar', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (15, 'tinytext', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (16, 'text', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (17, 'mediumtext', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (18, 'longtext', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (19, 'timestamp', 'Date', 'java.util.Date', now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (20, 'NUMBER', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (21, 'BINARY_INTEGER', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (22, 'BINARY_FLOAT', 'Float', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (23, 'BINARY_DOUBLE', 'Double', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (24, 'VARCHAR2', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (25, 'NVARCHAR', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (26, 'NVARCHAR2', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (27, 'CLOB', 'String', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (28, 'int8', 'Long', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (29, 'int4', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (30, 'int2', 'Integer', NULL, now());
INSERT INTO gen_field_type (id, column_type, attr_type, package_name, create_time)
VALUES (31, 'numeric', 'BigDecimal', 'java.math.BigDecimal', now());
