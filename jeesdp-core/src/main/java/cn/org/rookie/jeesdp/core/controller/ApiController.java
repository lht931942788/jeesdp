package cn.org.rookie.jeesdp.core.controller;

import cn.org.rookie.jeesdp.component.Record;
import cn.org.rookie.jeesdp.component.Response;
import cn.org.rookie.jeesdp.core.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("{name}/save")
    public Response save(@PathVariable String name, @RequestBody Record param) {
        try {
            Object id = apiService.save(name, param);
            return Response.success(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }
}
