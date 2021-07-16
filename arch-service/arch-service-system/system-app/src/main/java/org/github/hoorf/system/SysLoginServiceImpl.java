package org.github.hoorf.system;

import cn.hutool.core.map.MapUtil;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import org.github.hoorf.arch.framework.manager.AsyncManager;
import org.github.hoorf.arch.framework.manager.factory.AsyncFactory;
import org.github.hoorf.constant.Constants;
import org.github.hoorf.system.api.SysLoginService;
import org.github.hoorf.system.dto.LoginCmd;
import org.github.hoorf.system.entity.SysUser;
import org.github.hoorf.system.gateway.SysCacheGateway;
import org.github.hoorf.system.gateway.SysConfigGateway;
import org.github.hoorf.system.gateway.SysUserGateway;
import org.github.hoorf.system.security.LoginUser;
import org.github.hoorf.utils.IpUtils;
import org.github.hoorf.utils.MessageUtils;
import org.github.hoorf.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

//@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysUserGateway sysUserGateway;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysConfigGateway sysConfigGateway;


    @Autowired
    private SysCacheGateway redisCache;

    @Override
    public SingleResponse<Map<String, Object>> login(LoginCmd cmd) {

        String username = cmd.getUsername();
        String password = cmd.getPassword();
        String code = cmd.getCode();
        String uuid = cmd.getUuid();

        boolean captchaOnOff = sysConfigGateway.selectCaptchaOnOff();
        // 验证码开关
        if (captchaOnOff) {
            validateCapcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new BizException(MessageUtils.message("user.password.not.match"));
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new BizException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUser());
        // 生成token
        String token = tokenService.createToken(loginUser);
        return SingleResponse.of(MapUtil.<String, Object>builder().put(Constants.TOKEN, token).build());
    }

    public void validateCapcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new BizException(MessageUtils.message("user.jcaptcha.expire"));
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new BizException(MessageUtils.message("user.jcaptcha.error"));
        }
    }


    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(new Date());
        sysUserGateway.updateUserProfile(user);
    }

}
