package com.auction_website.controller;
import com.auction_website.model.Account;
import com.auction_website.model.AccountRole;
import com.auction_website.model.Role;
import com.auction_website.security.ValidationResponse;
import com.auction_website.service.account.AccountService;
import com.auction_website.service.account_role.AccountRoleService;
import com.auction_website.service.role.RoleService;
import com.auction_website.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private UserService userService;

    /**
     * Author: DungNV
     * Cập nhật email.
     * @param oldEmail
     * @param newEmail
     * @return
     */
    @PutMapping("/update-email/{oldEmail}/{newEmail}")
    public ResponseEntity<?> updateAccountEmail(@PathVariable("oldEmail") String oldEmail,@PathVariable("newEmail") String newEmail){
        accountService.updateEmail(oldEmail, newEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Author: DungNV
     * Tìm kiếm Account.
     * @param accountId
     * @return
     */
    @GetMapping("/find/{accountId}")
    public ResponseEntity<Account> findAccount(@PathVariable Integer accountId) {
        Account account = accountService.findAccountById(accountId);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * register (save account)
     */
    @PostMapping("/guest/save")
    public ResponseEntity<Account> saveAccount(@RequestBody @Validated Account account, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            userService.save(account.getUser());
            account.setUser(userService.findByPhone(account.getUser().getPhone()));
            accountService.save(account);
            account = accountService.findByAccountName(account.getAccountName());
            Role role = roleService.findById(2);
            AccountRole accountRole = new AccountRole();
            accountRole.setAccount(account);
            accountRole.setRole(role);
            accountRoleService.save(accountRole);
            return new ResponseEntity<Account>(account, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @author PhinNL
     * validate accountName duplicate
     */
    @GetMapping("/guest/exist/name")
    public ResponseEntity<ValidationResponse> findAccountNameExist(@RequestParam String accountName) {
        try {
            Account account = accountService.findByAccountName(accountName);
            ValidationResponse validationResponse = new ValidationResponse();
            if (account != null) {
                validationResponse.setAccountDuplicate(account.getAccountName());
            }
            return new ResponseEntity<>(validationResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @author PhinNL
     * validate email duplicate
     */
    @GetMapping("/guest/exist/email")
    public ResponseEntity<ValidationResponse> findEmailExist(@RequestParam String email) {
        try {
            Account account = accountService.findByEmail(email);
            ValidationResponse validationResponse = new ValidationResponse();
            if (account != null) {
                validationResponse.setEmailDuplicate(account.getEmail());
            }
            return new ResponseEntity<>(validationResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @author PhinNL
     * update logout time
     */
    @PutMapping("/guest/logout")
    public ResponseEntity<Void> logout(@RequestBody Account account) {
        try {
            Account accountFind = accountService.findByAccountName(account.getAccountName());
            if (accountFind != null){
                if (accountFind.getPassword().equals(account.getPassword())){
                    accountService.logout(accountFind.getAccountId());
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
