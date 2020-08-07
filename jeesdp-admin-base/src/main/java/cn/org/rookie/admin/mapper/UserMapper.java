package cn.org.rookie.admin.mapper;

import cn.org.rookie.common.base.entity.User;
import cn.org.rookie.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User, String> {

    void updateUserById(User user);
}
