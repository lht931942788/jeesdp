package cn.org.rookie.jeesdp.interceptor;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletInputStream;

/**
 * @author LIHAITAO
 */

public class ArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        System.out.println(methodParameter.getMethod());
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer container, NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        RequestFacade requestFacade = (RequestFacade) request.getNativeRequest();
        String contentType = requestFacade.getContentType();
        if (RequestMethod.GET.toString().equals(requestFacade.getMethod())) {

        }
        if (RequestMethod.POST.toString().equals(requestFacade.getMethod())) {
            if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                ServletInputStream is = requestFacade.getInputStream();
                byte[] bytes = is.readAllBytes();
                if (!methodParameter.hasParameterAnnotation(RequestBody.class)) {
                    System.out.println(methodParameter.getParameterType().getName());
                }
            }
        }


        System.out.println(request.getNativeRequest().getClass().getName());
        return null;
    }
}
