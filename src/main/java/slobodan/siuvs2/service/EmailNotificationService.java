package slobodan.siuvs2.service;

import slobodan.siuvs2.model.PasswordResetToken;
import slobodan.siuvs2.shared.SiuvsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailNotificationService {

    @Value("${siuvs.mail.noreply}")
    private String noReplyEmail;

    @Value("${siuvs.rootUrl}")
    private String rootUrl;

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleEmail(String destinationEmail, String subject, String body) throws SiuvsException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(this.noReplyEmail);
            message.setTo(destinationEmail);
            message.setSubject(subject);
            message.setText(body);
            this.emailSender.send(message);
        } catch (MailException e) {
            throw new SiuvsException("Грешка у слању маила", e);
        }
    }

    public void sendHtmlEmail(String destinationEmail, String subject, String body) throws SiuvsException {
        try {
            MimeMessage mimeMessage = this.emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessageHelper.setFrom(this.noReplyEmail);
            mimeMessageHelper.setTo(destinationEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            this.emailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            throw new SiuvsException("Грешка у слању маила", e);
        }
    }

    public void sendForgotPasswordEmail(PasswordResetToken token) throws SiuvsException {
        String resetLink = this.rootUrl + "/forgotpassword/" + token.getToken();
        String body =
            "<html>" +
            "<body>" +
            "<p>Вашу лозинку можете поставити на следећем линку: </p>" +
            "<p><a href=\""+resetLink+"\">"+resetLink+"</a></p>" +
            "</body>" +
            "</html>";
        this.sendHtmlEmail(
            token.getUser().getEmail(),
            "СИУВС - Заборављена лозинка",
            body
        );
    }

}
