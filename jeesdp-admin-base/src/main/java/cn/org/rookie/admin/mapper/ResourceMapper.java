package cn.org.rookie.admin.mapper;

import cn.org.rookie.common.base.entity.Resource;
import cn.org.rookie.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource, String> {

    List<Resource> selectConsumerResources(@Param("userId") String userId);

    String getMaxIdByPid(String pid);
}
