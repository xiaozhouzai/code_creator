create table sys_file (
    id bigint not null primary key,
    name varchar(64) null comment '文件名',
    path varchar(255) null comment '文件路径',
    url varchar(512) not null comment '文件访问路径',
    size bigint null default 0 comment '文件大小',
    created_at datetime not null default current_timestamp comment '创建时间',
    updated_at datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    deleted bit not null default 0 comment '是否删除'
) engine=innodb default charset=utf8mb4 default collate=utf8mb4_general_ci comment '文件表';