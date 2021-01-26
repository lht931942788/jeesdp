package cn.org.rookie.jeesdp.base.entity;

import cn.org.rookie.jeesdp.mapper.annotation.Column;
import cn.org.rookie.jeesdp.mapper.annotation.Table;
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
