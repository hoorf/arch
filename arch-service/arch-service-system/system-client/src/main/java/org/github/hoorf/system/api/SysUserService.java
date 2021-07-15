package org.github.hoorf.system.api;

import com.alibaba.cola.dto.PageResponse;
import org.github.hoorf.system.dto.UserPageVO;
import org.github.hoorf.system.dto.query.UserPageQuery;

public interface SysUserService {

    PageResponse<UserPageVO> list(UserPageQuery query);
}
