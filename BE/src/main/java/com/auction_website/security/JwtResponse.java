package com.auction_website.security;

import com.auction_website.model.Account;

import java.io.Serializable;
import java.util.List;


public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private Account account;
    private List<String> roles;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
