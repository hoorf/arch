package org.github.hoorf.system.web;

import com.alibaba.cola.dto.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.github.hoorf.system.api.SysUserService;
import org.github.hoorf.system.dto.UserPageVO;
import org.github.hoorf.system.dto.query.UserPageQuery;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "账户管理")
@RestController
@RequestMapping("/system/user")
public class SysUserController {
    @Autowired
    private SysUserService userService;


   // @Autowired
//    private TokenService tokenService;

    /**
     * 获取用户列表
     */
//    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public PageResponse<UserPageVO> list(UserPageQuery query) {
        return userService.list(query);
    }


}
