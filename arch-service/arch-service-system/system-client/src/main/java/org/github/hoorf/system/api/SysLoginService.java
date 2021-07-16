package org.github.hoorf.system.api;

import com.alibaba.cola.dto.SingleResponse;
import org.github.hoorf.system.dto.LoginCmd;

import java.util.Map;

public interface SysLoginService {

    SingleResponse<Map<String, Object>> login(LoginCmd cmd);
}
