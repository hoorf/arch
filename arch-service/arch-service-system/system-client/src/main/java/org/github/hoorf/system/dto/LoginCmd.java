package org.github.hoorf.system.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

@Data
public class LoginCmd extends Command {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;


    private String uuid;
}
