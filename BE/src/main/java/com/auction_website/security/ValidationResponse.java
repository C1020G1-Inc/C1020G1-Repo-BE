package com.auction_website.security;

public class ValidationResponse {
    private String accountDuplicate;
    private String emailDuplicate;
    private String phoneDuplicate;
    private String identityDuplicate;

    public ValidationResponse() {
    }

    public String getAccountDuplicate() {
        return accountDuplicate;
    }

    public void setAccountDuplicate(String accountDuplicate) {
        this.accountDuplicate = accountDuplicate;
    }

    public String getEmailDuplicate() {
        return emailDuplicate;
    }

    public void setEmailDuplicate(String emailDuplicate) {
        this.emailDuplicate = emailDuplicate;
    }

    public String getPhoneDuplicate() {
        return phoneDuplicate;
    }

    public void setPhoneDuplicate(String phoneDuplicate) {
        this.phoneDuplicate = phoneDuplicate;
    }

    public String getIdentityDuplicate() {
        return identityDuplicate;
    }

    public void setIdentityDuplicate(String identityDuplicate) {
        this.identityDuplicate = identityDuplicate;
    }
}
