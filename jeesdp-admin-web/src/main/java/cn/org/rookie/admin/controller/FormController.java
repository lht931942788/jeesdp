package cn.org.rookie.admin.controller;

import cn.org.rookie.admin.service.FormService;
import cn.org.rookie.common.base.controller.BaseController;
import cn.org.rookie.common.base.entity.Form;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("form")
public class FormController extends BaseController<Form, String, FormService> {

}
