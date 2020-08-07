package cn.org.rookie.admin.controller;

import cn.org.rookie.admin.service.RoleService;
import cn.org.rookie.common.annotation.Perms;
import cn.org.rookie.common.base.entity.Role;
import cn.org.rookie.common.component.CommonUtils;
import cn.org.rookie.common.component.Response;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("getRolesByPage")
    public PageInfo<Role> getRolesByPage(Role role) {
        return new PageInfo<>(roleService.select(role));
    }

    @PostMapping("getRoles")
    public List<Role> getRoles() {
        return roleService.select();
    }

    @PostMapping("getRoleById")
    @Perms("system:role:edit")
    public Response getRoleById(String id) {
        return roleService.selectRoleById(id);
    }

    @PostMapping("saveRole")
    @Perms("system:role:add")
    public Response saveRole(Role role, @RequestParam(value = "resources") String[] resources) {
        if (role.getId() == null) {
            role.setId(CommonUtils.uuid());
            roleService.insertRole(role, resources);
        } else {
            roleService.updateRole(role, resources);
        }
        return Response.success("message.success");
    }

    @PostMapping("deleteRoles")
    @Perms("system:role:delete")
    public Response deleteRoles(@RequestParam(value = "ids") String[] ids) {
        roleService.delete(ids);
        return Response.success("message.success");
    }

}
