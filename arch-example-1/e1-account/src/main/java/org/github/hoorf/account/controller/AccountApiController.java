package org.github.hoorf.account.controller;

import org.github.hoorf.account.api.AccountApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountApiController implements AccountApi {
    @Override
    public String getAccountInfo() {
        return "accountInfo";
    }
}
