package cn.org.rookie.common.interceptor;

import cn.org.rookie.common.annotation.Perms;
import cn.org.rookie.common.base.controller.BaseController;
import cn.org.rookie.common.base.entity.BaseEntity;
import cn.org.rookie.common.base.service.BaseService;
import cn.org.rookie.common.component.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (!"1".equals(UserUtils.getUserType())) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Perms perms = handlerMethod.getMethodAnnotation(Perms.class);
                if (perms != null) {
                    String permission = perms.value();
                    if (perms.auto()) {
                        Object bean = handlerMethod.getBean();
                        if (bean instanceof BaseController<?, ?, ?>) {
                            BaseController<?, ?, ?> baseController = (BaseController<?, ?, ?>) bean;
                            permission = baseController.getModel() + ":" + permission;
                        }
                    }
                    Set<String> userPermissions = UserUtils.getUserPermissions();
                    if (userPermissions != null) {
                        if (userPermissions.contains(permission)) {
                            return true;
                        } else {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            return false;
                        }
                    } else {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
