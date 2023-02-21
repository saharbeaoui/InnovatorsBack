package tn.esprit.pidev4sae2back.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMembershipValidationEmail(String email) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("nawrez.shili@esprit.tn");
        helper.setTo(email);
        helper.setSubject("Membership Validated");
        helper.setText("Your membership has been validated!");

        mailSender.send(message);
    }
}
