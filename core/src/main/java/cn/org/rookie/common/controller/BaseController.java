package cn.org.rookie.common.controller;

import cn.org.rookie.annotation.Perms;
import cn.org.rookie.common.service.BaseService;
import cn.org.rookie.component.Response;
import cn.org.rookie.entity.BaseEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;


public abstract class BaseController<T extends BaseEntity, E, M extends BaseService<T, E>> implements ApplicationContextAware, InitializingBean {

    protected String model;
    protected M baseService;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            model = ((Class<T>) ((ParameterizedType) type).getActualTypeArguments()[1]).getSimpleName().toLowerCase();
            baseService = applicationContext.getBean((Class<M>) ((ParameterizedType) type).getActualTypeArguments()[2]);
        }
    }

    @RequestMapping("save")
    @Perms(value = "add", auto = true)
    public Response save(T entity) {
        entity.setCreateTime(new Date());
        //entity.setCreateUser(UserUtils.getUserId());
        if (baseService.insert(entity)) {
            return Response.success("message.success");
        } else {
            return Response.error("message.error");
        }
    }

    @RequestMapping("remove")
    @Perms(value = "remove", auto = true)
    public Response remove(@RequestParam(value = "ids") E id) {
        if (baseService.delete(id)) {
            return Response.success("message.success");
        } else {
            return Response.error("message.error");
        }
    }

    @RequestMapping("removeByIds")
    @Perms(value = "remove", auto = true)
    public Response remove(@RequestParam(value = "ids") E[] ids) {
        if (baseService.delete(ids)) {
            return Response.success("message.success");
        } else {
            return Response.error("message.error");
        }
    }

    @RequestMapping("update")
    @Perms(value = "edit", auto = true)
    public Response update(T entity) {
        entity.setUpdateTime(new Date());
        //entity.setUpdateUser(cn.org.rookie.jeesdp.base.utils.UserUtils.getUserId());
        if (baseService.update(entity)) {
            return Response.success("message.success");
        } else {
            return Response.error("message.error");
        }
    }

    @RequestMapping("getById")
    public T get(E id) {
        return baseService.selectById(id);
    }

    @RequestMapping("get")
    public T get(T entity) {
        return baseService.selectOne(entity);
    }

    @Perms(value = "list", auto = true)
    @RequestMapping("list")
    public List<T> list(T entity) {
        return baseService.select(entity);
    }

    @RequestMapping("listByIds")
    public List<T> list(E[] ids) {
        return baseService.select(ids);
    }

    @RequestMapping("listByPage")
    public PageInfo<T> listByPage(T entity) {
        return new PageInfo<>(baseService.select(entity));
    }

    @RequestMapping("listByIdsAndPage")
    public PageInfo<T> listByPage(E[] ids) {
        return new PageInfo<>(baseService.select(ids));
    }

    public String getModel() {
        return model;
    }
}
