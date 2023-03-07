package tn.esprit.pidev4sae2back.utils;
import ch.qos.logback.core.read.ListAppender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Meal;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.MealRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class MailService implements MealEmailService{
    @Autowired
    MealRepository mealRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;




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

    @Override
    public void sendEmail(List<String> recipients, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(recipients.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public void sendMealNotification(List<String> recipients, String mealName, String description) {
        String subject = "New meal added!";
        String body = String.format("We've added a new meal to our menu! Try the %s. Description: %s", mealName, description);
        sendEmail(Arrays.asList(recipients.toArray(new String[0])), subject, body);

    }
    public void sendMenuNotification(List<String> recipients,List<Meal> menu) {
        String subject = "New meal added!";
        String body = String.format("We've added a new meal to our menu! Try the %s. Description: %s", menu);
        sendEmail(Arrays.asList(recipients.toArray(new String[0])), subject, body);

    }

}
