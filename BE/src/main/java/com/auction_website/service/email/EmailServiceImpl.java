package com.auction_website.service.email;

import com.auction_website.model.ProductTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * author: PhucPT
     * method: send email to winner
     *
     * @param productTransaction
     * @throws MessagingException
     */
    @Override
    public void sendMailToWinner(ProductTransaction productTransaction) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMsg = "<h4>Chào bạn,</h4>\n" +
                "<p>Chúc mừng bạn đã đấu giá thành công sản phẩm '" + productTransaction.getProduct().getProductName() + "</p>\n" +
                "<p>Đăng nhập vào giỏ hàng để theo dõi chi tiết đơn hàng: </p><a href='http://localhost:4200/auction/cart'>Giỏ hàng</a>" +
                " <p>Lưu ý trong vòng 24h kể từ khi nhận mail này nếu không có bất kì thành toán nào thì giao dịch sẽ bị hủy</p>\n" +
                "<br>\n" +
                "<p>C10 Auction Support Team</p>";
        System.out.println(htmlMsg);
        message.setContent(htmlMsg, "text/html;charset=utf-8");
        helper.setTo(productTransaction.getAccount().getEmail());
        helper.setSubject("[C10-Auction] Đấu giá thành công");
        this.javaMailSender.send(message);
    }
}
