package cn.org.rookie.jeesdp.core.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.LinkedHashMap;
import java.util.Set;

@SuppressWarnings("ALL")
public abstract class UserUtils {

    /*public static User getUser() {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        Object principal = userAuthentication.getPrincipal();
        User user = null;
        if (principal != null && principal instanceof User) {
            user = (User) principal;
        } else {
            LinkedHashMap details = (LinkedHashMap) userAuthentication.getDetails();
            ReflectUtils.populate(details, User.class);
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
    }*/

}
