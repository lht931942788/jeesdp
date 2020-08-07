package cn.org.rookie.admin.service;

import cn.org.rookie.admin.mapper.RoleMapper;
import cn.org.rookie.admin.mapper.RoleResourceMapper;
import cn.org.rookie.admin.mapper.RoleUserMapper;
import cn.org.rookie.common.base.entity.Role;
import cn.org.rookie.common.base.entity.RoleResource;
import cn.org.rookie.common.base.service.impl.BaseServiceImpl;
import cn.org.rookie.common.component.CommonUtils;
import cn.org.rookie.common.component.Response;
import cn.org.rookie.mapper.sql.where.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RoleService extends BaseServiceImpl<Role, String, RoleMapper> {

    private final RoleResourceMapper roleResourceMapper;
    private final RoleUserMapper roleUserMapper;

    public RoleService(RoleResourceMapper roleResourceMapper, RoleUserMapper roleUserMapper) {
        this.roleResourceMapper = roleResourceMapper;
        this.roleUserMapper = roleUserMapper;
    }

    @Transactional
    public boolean insertRole(Role entity, String[] resources) {
        insert(entity);
        for (String id : resources) {
            RoleResource roleResource = new RoleResource();
            roleResource.setId(CommonUtils.uuid());
            roleResource.setRoleId(entity.getId());
            roleResource.setResourceId(id);
            roleResourceMapper.insert(roleResource);
        }
        return true;
    }

    @Transactional
    public boolean updateRole(Role entity, String[] resources) {
        update(entity);
        roleResourceMapper.delete(Wrapper.build().eq("role_id", entity.getId()));
        for (String id : resources) {
            RoleResource roleResource = new RoleResource();
            roleResource.setId(CommonUtils.uuid());
            roleResource.setRoleId(entity.getId());
            roleResource.setResourceId(id);
            roleResourceMapper.insert(roleResource);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(String[] ids) {
        super.delete(ids);
        for (String id : ids) {
            roleResourceMapper.delete(Wrapper.build().eq("role_id", id));
            roleUserMapper.delete(Wrapper.build().eq("role_id", id));
        }
        return true;
    }

    public Response selectRoleById(String id) {
        List<RoleResource> roleResources = roleResourceMapper.selectList(Wrapper.build().eq("role_id", id));
        String[] ids = new String[roleResources.size()];
        for (int i = 0; i < roleResources.size(); i++) {
            ids[i] = roleResources.get(i).getResourceId();
        }
        return Objects.requireNonNull(Response.success().put("role", selectById(id))).put("resources", ids);
    }
}
