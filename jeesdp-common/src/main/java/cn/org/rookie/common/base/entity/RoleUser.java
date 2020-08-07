package cn.org.rookie.common.base.entity;

import cn.org.rookie.mapper.annotation.Column;
import cn.org.rookie.mapper.annotation.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Table("admin_role_user")
public class RoleUser implements Serializable {

    @Column(primary = true)
    private String id;
    private String roleId;
    private String userId;

    public RoleUser() {
    }

    public RoleUser(String id, String roleId, String userId) {
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
    }
}
