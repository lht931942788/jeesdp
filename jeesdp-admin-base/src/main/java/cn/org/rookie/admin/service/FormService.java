package cn.org.rookie.admin.service;

import cn.org.rookie.admin.mapper.FormMapper;
import cn.org.rookie.common.base.entity.Form;
import cn.org.rookie.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FormService extends BaseServiceImpl<Form, String, FormMapper> {
}
