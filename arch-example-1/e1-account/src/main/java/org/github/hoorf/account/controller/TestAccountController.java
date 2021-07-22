package org.github.hoorf.account.controller;

import org.github.hoorf.account.api.AccountApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAccountController {

    @Autowired
    private AccountApi accountApi;

    @GetMapping("/test")
    public String account() {
        return accountApi.getAccountInfo();
    }
}
