package cn.org.rookie.jeesdp.base.entity;

import cn.org.rookie.jeesdp.mapper.annotation.Column;
import cn.org.rookie.jeesdp.mapper.annotation.Table;
import lombok.Data;

@Data
@Table("admin_role_resource")
public class RoleResource {

    @Column(primary = true)
    private String id;
    private String roleId;
    private String resourceId;
}
