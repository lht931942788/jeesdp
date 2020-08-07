package cn.org.rookie.admin.controller;

import cn.org.rookie.admin.service.ResourceService;
import cn.org.rookie.common.base.controller.BaseController;
import cn.org.rookie.common.base.entity.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController extends BaseController<Resource, String, ResourceService> {

}
