package cn.org.rookie.common.base.entity;

import cn.org.rookie.mapper.annotation.Column;
import cn.org.rookie.mapper.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("admin_form")
public class Form extends BaseEntity {

    @Column(primary = true)
    private String id;
    private String name;
    private String remark;
    private String content;
}
