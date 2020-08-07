package cn.org.rookie.admin.service;

import cn.org.rookie.admin.mapper.RoleUserMapper;
import cn.org.rookie.admin.mapper.UserMapper;
import cn.org.rookie.common.base.entity.RoleUser;
import cn.org.rookie.common.base.entity.User;
import cn.org.rookie.common.base.service.impl.BaseServiceImpl;
import cn.org.rookie.common.component.CommonUtils;
import cn.org.rookie.mapper.sql.where.Wrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService extends BaseServiceImpl<User, String, UserMapper> implements UserDetailsService {

    private final ResourceService resourceService;
    private final RoleUserMapper roleUserMapper;

    public UserService(ResourceService resourceService, RoleUserMapper roleUserMapper) {
        this.resourceService = resourceService;
        this.roleUserMapper = roleUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.selectOne(Wrapper.build().eq("username", username));
        user.setResources(resourceService.getResources(user.getUserType(), user.getId()));
        //TODO 加权限串
        return user;
    }

    @Transactional
    public void insertUser(User user, String[] roles) {
        insert(user);
        insertUserRoles(user.getId(), roles);
    }

    @Transactional
    public void updateById(User user, String[] roles) {
        mapper.updateUserById(user);
        roleUserMapper.delete(Wrapper.build().eq("user_id", user.getId()));
        insertUserRoles(user.getId(), roles);
    }

    @Transactional
    public void insertUserRoles(String userId, String[] roles) {
        for (String id : roles) {
            RoleUser roleUser = new RoleUser(CommonUtils.uuid(), id, userId);
            roleUserMapper.insert(roleUser);
        }
    }

    @Transactional
    public void deleteUserByIds(String[] ids) {
        delete(ids);
        for (String id : ids) {
            roleUserMapper.delete(Wrapper.build().eq("user_id", id));
        }
    }

    public User selectUserById(String id) {
        User user = selectById(id);
        List<RoleUser> roleUsers = roleUserMapper.selectList(Wrapper.build().eq("user_id", id));
        String[] userRoleIds = new String[roleUsers.size()];
        for (int i = 0; i < roleUsers.size(); i++) {
            userRoleIds[i] = roleUsers.get(i).getRoleId();
        }
        user.setRoles(userRoleIds);
        return user;
    }
}
