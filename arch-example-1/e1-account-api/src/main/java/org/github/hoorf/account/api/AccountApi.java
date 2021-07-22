package org.github.hoorf.account.api;


import org.github.hoorf.support.AdaptiveFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@AdaptiveFeign("account-app")
@RequestMapping("/account")
public interface AccountApi {
    @GetMapping("/getAccountInfo")
    String getAccountInfo();
}
