alter table workflow_process
    add create_user varchar(32) null comment '创建人';

alter table workflow_process
    add create_time datetime null comment '创建时间';

alter table workflow_process
    add update_user varchar(32) null comment '修改人';

alter table workflow_process
    add update_time datetime null comment '修改时间';

alter table workflow_process
    add flag int null comment '标识';