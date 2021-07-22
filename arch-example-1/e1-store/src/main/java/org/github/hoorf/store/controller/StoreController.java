package org.github.hoorf.store.controller;

import org.github.hoorf.account.api.AccountApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @Autowired
    private AccountApi accountApi;

    @GetMapping("getAccountInfo")
    public String getAccountInfo() {
        return accountApi.getAccountInfo();
    }
}
