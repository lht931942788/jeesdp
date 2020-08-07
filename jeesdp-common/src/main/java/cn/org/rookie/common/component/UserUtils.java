package cn.org.rookie.common.component;

import cn.org.rookie.common.base.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Set;

@SuppressWarnings("ALL")
public class UserUtils {

    public static User getUser() {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        Object principal = userAuthentication.getPrincipal();
        User user = null;
        if (principal != null && principal instanceof User) {
            user = (User) principal;
        } else {
            LinkedHashMap details = (LinkedHashMap) userAuthentication.getDetails();
            user = new User();
            try {
                BeanUtils.populate(user, details);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static Set<String> getUserPermissions() {
        return getUser().getPermissions();
    }

    public static String getUserType() {
        return getUser().getUserType();
    }

    public static String getUserId() {
        return getUser().getId();
    }

}
