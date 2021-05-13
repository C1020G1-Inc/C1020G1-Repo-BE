package com.auction_website.controller;

import com.auction_website.config.JwtTokenUtil;
import com.auction_website.model.Account;
import com.auction_website.model.AccountRole;
import com.auction_website.model.User;
import com.auction_website.security.JwtRequest;
import com.auction_website.security.JwtResponse;
import com.auction_website.security.SocialResponse;
import com.auction_website.service.account.AccountService;
import com.auction_website.service.account_role.AccountRoleService;
import com.auction_website.service.jwt.JwtAccountDetailService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Value("${google.clienId}")
    String googleClientId;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAccountDetailService jwtAccountDetailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = login(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("oauth/google")
    public ResponseEntity<?> google(@RequestBody SocialResponse jwtResponseSocial) throws IOException {
        final NetHttpTransport netHttpTransport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder builder =
                new GoogleIdTokenVerifier.Builder(netHttpTransport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(builder.getJsonFactory(), jwtResponseSocial.getJwtToken());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        Account account = accountService.findByEmail(payload.getEmail());
        JwtResponse jwtResponse = new JwtResponse("");
        if (account == null) {
            account = new Account();
            account.setEmail(payload.getEmail());
            jwtResponse.setAccount(account);
            return ResponseEntity.ok(jwtResponse);
        }
        JwtRequest jwtRequest = new JwtRequest(account.getAccountName(), account.getPassword());
        jwtResponse = loginSocial(jwtRequest, account);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("oauth/facebook")
    public ResponseEntity<?> facebook(@RequestBody SocialResponse jwtResponseSocial) {
        Facebook facebook = new FacebookTemplate(jwtResponseSocial.getJwtToken());

        final String[] fields = {"email", "name"};
        org.springframework.social.facebook.api.User user = facebook
                .fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        Account account = accountService.findByEmail(user.getEmail());
        JwtResponse jwtResponse = new JwtResponse("");

        if (account == null) {
            account = new Account();
            account.setEmail(user.getEmail());
            account.setUser(new User());
            account.getUser().setUserName(user.getName());
            jwtResponse.setAccount(account);
            return ResponseEntity.ok(jwtResponse);
        }
        JwtRequest jwtRequest = new JwtRequest(account.getAccountName(), account.getPassword());
        jwtResponse = loginSocial(jwtRequest, account);
        return ResponseEntity.ok(jwtResponse);
    }

    private JwtResponse loginSocial(JwtRequest jwtRequest, Account account) {
        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        String token = null;
        if (userDetails.isEnabled()){
            token = jwtTokenUtil.generateToken(userDetails);
        }
        JwtResponse jwtResponse = new JwtResponse(token);
        List<String> roleList = new ArrayList<>();
        for (AccountRole accountRole : accountRoleService.findAllByAccountId(account.getAccountId())) {
            roleList.add(accountRole.getRole().getName());
        }
        jwtResponse.setAccount(account);
        jwtResponse.setRoles(roleList);
        return jwtResponse;
    }

    private JwtResponse login(JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getAccountName(), jwtRequest.getPassword());
        } catch (Exception e) {
            if (e.getMessage().equals("INVALID_CREDENTIALS")){
                return new JwtResponse(e.getMessage());
            }
        }

        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        String token = null;
        if (userDetails.isEnabled()){
            token = jwtTokenUtil.generateToken(userDetails);
        }
        JwtResponse jwtResponse = new JwtResponse(token);
        Account account = accountService.findByAccountName(jwtRequest.getAccountName());
        jwtResponse.setAccount(account);
        List<String> roleList = new ArrayList<>();
        for (AccountRole accountRole : accountRoleService.findAllByAccountId(account.getAccountId())) {
            roleList.add(accountRole.getRole().getName());
        }
        jwtResponse.setRoles(roleList);
        return jwtResponse;
    }

    private void authenticate(String accountName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}