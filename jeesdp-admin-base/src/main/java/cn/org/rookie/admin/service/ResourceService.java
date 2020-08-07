package cn.org.rookie.admin.service;

import cn.org.rookie.admin.mapper.ResourceMapper;
import cn.org.rookie.admin.mapper.RoleResourceMapper;
import cn.org.rookie.common.base.entity.Resource;
import cn.org.rookie.common.base.service.impl.BaseServiceImpl;
import cn.org.rookie.mapper.sql.where.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceService extends BaseServiceImpl<Resource, String, ResourceMapper> {

    private final RoleResourceMapper roleResourceMapper;

    public ResourceService(RoleResourceMapper roleResourceMapper) {
        this.roleResourceMapper = roleResourceMapper;
    }

    public List<Resource> getResources(String userType, String userId) {
        List<Resource> resources;
        if ("3".equals(userType)) {
            resources = mapper.selectConsumerResources(userId);
        } else {
            Wrapper wrapper = Wrapper.build().order("sort");
            if ("2".equals(userType)) {
                wrapper.gt("view_type", 0);
            }
            resources = mapper.selectList(wrapper);
        }
        return resources;
    }

    @Transactional
    public void remove(String[] ids) {
        for (String id : ids) {
            mapper.delete(Wrapper.build().like("id", "'" + id + "%'"));
            roleResourceMapper.delete(Wrapper.build().like("id", "'" + id + "%'"));
        }
    }

    public String generateId(String pid) {
        String id = mapper.getMaxIdByPid(pid);
        if (id == null) {
            return pid + "001";
        } else {
            return String.valueOf(Long.parseLong(id) + 1);
        }
    }

    @Transactional
    public void insert(Resource resource, Integer isCreate) {
        super.insert(resource);
        if (isCreate == 1 && "1".equals(resource.getType())) {
            Resource add = new Resource();
            add.setId(generateId(resource.getId()));
            add.setName("新增");
            add.setCode("add");
            add.setType("2");
            add.setPid(resource.getId());
            add.setSort(1);
            add.setViewType("0");
            insert(add);
            Resource edit = new Resource();
            edit.setId(generateId(resource.getId()));
            edit.setName("修改");
            edit.setCode("edit");
            edit.setType("2");
            edit.setPid(resource.getId());
            edit.setSort(2);
            edit.setViewType("0");
            insert(edit);
            Resource delete = new Resource();
            delete.setId(generateId(resource.getId()));
            delete.setName("删除");
            delete.setCode("remove");
            delete.setType("2");
            delete.setPid(resource.getId());
            delete.setSort(3);
            delete.setViewType("0");
            insert(delete);
        }
    }
}
