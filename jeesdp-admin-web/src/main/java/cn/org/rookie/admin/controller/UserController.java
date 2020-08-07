package cn.org.rookie.admin.controller;

import cn.org.rookie.admin.service.UserService;
import cn.org.rookie.common.annotation.Perms;
import cn.org.rookie.common.base.entity.User;
import cn.org.rookie.common.component.CommonUtils;
import cn.org.rookie.common.component.Response;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("getUsersByPage")
    public PageInfo<User> getUsersByPage(User user) {
        return new PageInfo<>(userService.select(user));
    }

    @PostMapping("getUserById")
    @Perms("system:user:edit")
    public User getUserById(String id) {
        return userService.selectUserById(id);
    }

    @PostMapping("saveUser")
    @Perms("system:user:add")
    public Response saveUser(User user, @RequestParam String[] roles) {
        if (user.getId() == null) {
            user.setId(CommonUtils.uuid());
            user.setPassword(passwordEncoder.encode("123456"));
            userService.insertUser(user, roles);
        } else {
            userService.updateById(user, roles);
        }
        return Response.success("message.success");

    }

    @PostMapping("deleteUsers")
    @Perms("system:user:delete")
    public Response deleteResources(@RequestParam String[] ids) {
        userService.deleteUserByIds(ids);
        return Response.success("message.success");
    }

}
