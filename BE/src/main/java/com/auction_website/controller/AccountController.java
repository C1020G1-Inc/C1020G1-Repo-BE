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
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@RestController
@CrossOrigin(origins = "*")
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
     * Author : ThinhHN
     * Get all user
     */
    @RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllUser() {
        try {
            List<Account> userList = accountService.findAllUser();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    /**
     * Author : ThinhHN
     * Lock user by id
     */
    @PutMapping("/admin/lock-user/{idUser}")
    public ResponseEntity<Void> lockUserById(@PathVariable Integer idUser) {
        accountService.lockUserById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Author : ThinhHN
     * Unlock user by id
     */
    @PutMapping("/admin/unlock-user/{idUser}")
    public ResponseEntity<Void> unLockUserById(@PathVariable Integer idUser) {
        accountService.unLockUserById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Author : ThinhHN
     * Search advance
     */
    @GetMapping("/admin/search-user")
    public ResponseEntity<List<Account>> searchUser(@RequestParam String userName, @RequestParam Integer userId,
                                                    @RequestParam String address, @RequestParam String userEmail) {
        try {
            if (userName.equals("undefined")) {
                userName = null;
            }
            if (address.equals("undefined")) {
                address = null;
            }
            if (userEmail.equals("undefined")) {
                userEmail = null;
            }
            if (userId == 0) {
                userId = null;
            }
            System.out.println(userName);
            System.out.println(userId);
            System.out.println(userEmail);
            System.out.println(userId);
            List<Account> userList = accountService.searchUser(userName, userId, address, userEmail);
            System.out.println(userList);
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }

    /**
     * Author : ThinhHN
     * Get all user by date
     */
    @GetMapping("/admin/user-chart")
    public ResponseEntity<List<Account>> getUserByDate(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year) {
        try {
            if (month == 0) {
                month = null;
            }
            List<Account> accountList = accountService.getUserByDate(month, year);
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


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
