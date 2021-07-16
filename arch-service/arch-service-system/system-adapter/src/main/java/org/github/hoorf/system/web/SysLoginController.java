package org.github.hoorf.system.web;

import com.alibaba.cola.dto.SingleResponse;
import org.github.hoorf.system.api.SysLoginService;
import org.github.hoorf.system.dto.LoginCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;


    @PostMapping("/login")
    public SingleResponse<Map<String, Object>> login(@RequestBody LoginCmd cmd) {
        return sysLoginService.login(cmd);
    }

}
