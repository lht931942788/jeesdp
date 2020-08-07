package cn.org.rookie.common.component;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestUtils {

    /**
     * 获取当前request
     *
     * @return 当前request
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        ServletRequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes != null) {
            request = requestAttributes.getRequest();
        }
        return request;
    }

    /**
     * 获取当前RequestAttributes
     *
     * @return 当前RequestAttributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        ServletRequestAttributes servletRequestAttributes = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        }
        return servletRequestAttributes;
    }

    /**
     * 获取当前Request的ParameterMap
     *
     * @return 获取当前Request的ParameterMap
     */
    public static Map<String, String[]> getParameterMap() {
        return getRequest().getParameterMap();
    }

    /**
     * 通过key获取当前Request的Parameter
     *
     * @param key 参数
     * @return key对应的Parameter
     */
    public static String getParameter(String key) {
        return getRequest().getParameter(key);
    }

    /**
     * 通过key获取当前Request的Parameter数组
     *
     * @param key 参数
     * @return key对应的Parameter数组
     */
    public static String[] getParameterValues(String key) {
        return getRequest().getParameterValues(key);
    }

}
