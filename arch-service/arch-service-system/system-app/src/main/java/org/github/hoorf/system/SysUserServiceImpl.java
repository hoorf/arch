package org.github.hoorf.system;

import com.alibaba.cola.dto.PageResponse;
import org.github.hoorf.system.api.SysUserService;
import org.github.hoorf.system.dto.UserPageVO;
import org.github.hoorf.system.dto.query.UserPageQuery;
import org.github.hoorf.system.executor.query.SysUserQueryExe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserQueryExe sysUserQueryExe;
    @Override
    public PageResponse<UserPageVO> list(UserPageQuery query) {
        return sysUserQueryExe.list(query);
    }
}
