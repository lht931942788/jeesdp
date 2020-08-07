package cn.org.rookie.admin.controller;

import cn.org.rookie.admin.service.ResourceService;
import cn.org.rookie.common.annotation.Perms;
import cn.org.rookie.common.annotation.Log;
import cn.org.rookie.common.base.entity.Resource;
import cn.org.rookie.common.component.Response;
import cn.org.rookie.common.component.UserUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("resource")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("getResources")
    @Log(value = "测试", type = 1)
    public List<Resource> getResources() {
        return resourceService.getResources(UserUtils.getUserType(), UserUtils.getUserId());
    }

    @PostMapping("getResourceById")
    @Perms("system:menu:edit")
    public Resource getResourceById(String id) {
        return resourceService.selectById(id);
    }

    @PostMapping("saveResource")
    @Perms("system:menu:add")
    public Response saveResource(Resource resource, Integer isCreate) {
        if (resource.getId() == null) {
            resource.setId(resourceService.generateId(resource.getPid()));
            resourceService.insert(resource, isCreate);
        } else {
            resourceService.update(resource);
        }
        return Response.success("message.success");
    }

    @PostMapping("deleteResources")
    @Perms("system:menu:delete")
    public Response deleteResources(@RequestParam(value = "ids") String[] ids) {
        resourceService.delete(ids);
        return Response.success("message.success");
    }

}
