package com.auction_website.service.email;
import com.auction_website.model.Account;
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
    public void sendMailToWinner(ProductTransaction productTransaction) {
        try {
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
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void sendEmailNearEndTransaction(ProductTransaction productTransaction){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = "<h4>Chào bạn,</h4>\n" +
                    "<p>Đơn hàng của bạn sắp kết thúc trong vòng 1 phút nữa:  '" + productTransaction.getProduct().getProductName() + "</p>\n" +
                    "<p>Vui lòng vào đơn hàng và thực hiện thanh toán: </p><a href='http://localhost:4200/auction/cart'>Giỏ hàng</a>" +
                    " <p>Lưu ý trong vòng 24h kể từ khi nhận mail này nếu không có bất kì thành toán nào thì giao dịch sẽ bị hủy</p>\n" +
                    "<br>\n" +
                    "<p>C10 Auction Support Team</p>";
            System.out.println(htmlMsg);
            message.setContent(htmlMsg, "text/html;charset=utf-8");
            helper.setTo(productTransaction.getAccount().getEmail());
            helper.setSubject("[C10-Auction] Lưu ý - Đơn hàng sắp kết thúc");
            this.javaMailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void sendEmailLockUserByOverCancelTransaction(Account account){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = "<h4>Chào " + account.getAccountName() + ",</h4>\n" +
                    "<p>Tài khoản của quý khách đã bị khóa do số lần hủy đơn hàng của quý khách đã vượt quá số lượng quy đinh (3 lần)</p>\n" +
                    "<p>Nếu có bất kì thắc mắc nào, xin liên hệ qua email: C10Tinder@gmail.com hoặc thông qua đường dây nóng 0935507895</p>\n" +
                    "<p>Cảm ơn quý khách đã ủng hộ cho C10Aution trong thời gian qua</p>\n" +
                    "<br>\n" +
                    "<p>C10 Auction Support Team</p>";
            System.out.println(htmlMsg);
            message.setContent(htmlMsg, "text/html;charset=utf-8");
            helper.setTo(account.getEmail());
            helper.setSubject("[C10-Auction] Khóa tài khoản");
            this.javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void sendEmailCancelTransaction(ProductTransaction productTransaction, int numberCancel){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = "<h4>Chào bạn,</h4>\n" +
                    "<p>Phiên giao dich sản phẩm '" + productTransaction.getProduct().getProductName() + " đã kết thúc sau 24h không có thanh toán</p>\n" +
                    "<p>Bạn còn " + (3 - numberCancel) + " lần hủy sản phẩm</p>" +
                    "<p>Sau 3 lần hủy giao dịch thì tài khoản của bạn sẽ bị khóa theo quy định của C10Auction</p>\n" +
                    "<br>\n" +
                    "<p>C10 Auction Support Team</p>";
            System.out.println(htmlMsg);
            message.setContent(htmlMsg, "text/html;charset=utf-8");
            helper.setTo(productTransaction.getAccount().getEmail());
            helper.setSubject("[C10-Auction] Cảnh báo - Giao dịch thất bại");
            this.javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
