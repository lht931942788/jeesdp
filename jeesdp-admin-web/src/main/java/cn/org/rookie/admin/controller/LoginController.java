package cn.org.rookie.admin.controller;

import cn.org.rookie.common.component.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/oauth/user")
    public Object user() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping("/oauth/userinfo")
    public Object userinfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/oauth/logout")
    public Response logout() {
        SecurityContextHolder.clearContext();
        return Response.success();
    }

}
