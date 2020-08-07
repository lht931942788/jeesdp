package cn.org.rookie.common.base.entity;

import cn.org.rookie.mapper.annotation.Column;
import cn.org.rookie.mapper.annotation.Table;
import cn.org.rookie.mapper.annotation.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("admin_resource")
public class Resource extends BaseEntity {

    @Column(primary = true)
    private String id;
    private String name;
    private String url;
    private String path;
    private String iconClass;
    private String type;
    private String code;
    private long sort;
    private String viewType = "0";
    private String pid;
    @Transient
    private List<Resource> children;
}
