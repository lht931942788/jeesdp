package cn.org.rookie.jeesdp.core.interceptor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> returnType = handlerMethod.getMethod().getReturnType();
            if (returnType.equals(PageInfo.class)) {
                String pageNum = request.getParameter("pageNum");
                String pageSize = request.getParameter("pageSize");
                if (pageNum != null && pageSize != null) {
                    PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
                }
                String order = request.getParameter("order");
                if (order != null) {
                    PageHelper.orderBy(order);
                }
            }
        }
        return true;
    }

}
