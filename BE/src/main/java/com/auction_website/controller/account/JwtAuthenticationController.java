package com.auction_website.controller.account;

import com.auction_website.security.SocialResponse;
import com.auction_website.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;


@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {



    @Autowired
    public  JavaMailSender mailSender;

    @Autowired
    public AccountService accountService ;

    @GetMapping("/api/recover/{accountEmail}")
    public ResponseEntity<?> mailSender(@PathVariable("accountEmail") String accountEmail) throws MessagingException {


        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        generatedString += "C10a";

        accountService.changePassword(accountEmail, generatedString);



        MimeMessage message = mailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Your new password is <i>"+generatedString+"<i></h3>" +
                "<h2><img src='https://static.tapchitaichinh.vn/images/upload/hoangthuviet/2016_09_19/ban-dau-gia-tsdbgega_WHBQ.jpg'>C10_Auction<h2>";

        message.setContent(htmlMsg, "text/html");

        helper.setTo(accountEmail);

        helper.setSubject("Auction Support Recover Password");


        mailSender.send(message);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/api/account/admin/confirm")
    public ResponseEntity<?> mailSenderConfirm( @RequestParam String accountEmail,
                                                @RequestParam String confirmContent,
                                                @RequestParam String userName) throws MessagingException {


        MimeMessage message = mailSender.createMimeMessage();


        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        String htmlMsg = "<h3>Dear "+userName+"</h3>" +
                "<span>" + confirmContent + "</span>" +
                "<h2><img src='https://static.tapchitaichinh.vn/images/upload/hoangthuviet/2016_09_19/ban-dau-gia-tsdbgega_WHBQ.jpg'>C10_Auction<h2>";

        message.setContent(htmlMsg,"text/html; charset=utf-8");

        helper.setTo(accountEmail);

        helper.setSubject("Thư phản hồi từ C10Auction");


        mailSender.send(message);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/api/confirm/guest/{accountEmail}")
    public ResponseEntity<?> mailSenderCode(@PathVariable("accountEmail") String accountEmail) throws MessagingException {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 6;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        MimeMessage message = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
        String htmlMsg = "  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "          <tr>\n" +
                "            <td align=\"center\" valign=\"top\" style=\"padding: 36px 24px;\">\n" +
                "              <a href=\"http://localhost:4200\" target=\"_blank\" style=\"display: inline-block;\">\n" +
                "                <img src=\"https://img.icons8.com/plasticine/2x/auction.png\" alt=\"Logo\" border=\"0\" " +
                "width=\"48\" style=\"display: block; width: 48px; max-width: 48px; min-width: 48px;\">\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "          <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 36px 24px 0; font-family: " +
                "'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;\">\n" +
                "              <h1 style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; " +
                "line-height: 48px;\">Xác Nhận Địa Chỉ Email</h1>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: " +
                "600px;\">\n" +
                "          <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans " +
                "Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n" +
                "              <p style=\"margin: 0;\">Sao chép đoạn mã này và dán vào ô xác nhận.</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td align=\"left\" bgcolor=\"#ffffff\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                <tr>\n" +
                "                  <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 12px;\">\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                      <tr>\n" +
                "                        <td align=\"center\" bgcolor=\"#1a82e2\" style=\"border-radius: 6px;\">\n" +
                "                          <a style=\"display: inline-block; padding: 16px 36px; font-family: 'Source" +
                " Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; " +
                "border-radius: 6px;\">" + generatedString + "</a>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 24px;\">\n" +
                "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "\n" +
                "          <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source " +
                "Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n" +
                "              <p style=\"margin: 0;\">Bạn nhận được email này vì chúng tối nhận được một yêu cầu xác " +
                "nhận email từ việc đăng ký tài khoản của bạn. Nếu không phải là bạn đăng ký, bạn có thể xóa email này.</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "\n" +
                "          <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source " +
                "Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n" +
                "              <p style=\"margin: 0;\">C10Auction</p>\n" +
                "              <p style=\"margin: 0;\">Website đấu giá hàng đầu Việt Nam</p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "\n" +
                "        </table>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "  </table>";
        message.setContent(htmlMsg, "text/html;charset=UTF-8");
        helper.setTo(accountEmail);
        helper.setSubject("Auction Support Confirm Email");
        mailSender.send(message);
        SocialResponse socialResponse = new SocialResponse();
        socialResponse.setJwtToken(generatedString);
        return new ResponseEntity<>(socialResponse,HttpStatus.OK);
    }
}
