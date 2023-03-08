package tn.esprit.pidev4sae2back.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleMailController {
	@Autowired
	private JavaMailSender sender;

	@GetMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo("nawrez.shili@esprit.tn");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}

	@GetMapping("/sendMailAtt")
	public String sendMailAttachment() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		try {
			helper.setTo("nawrez.shili@esprit.tn");
			helper.setText("Greetings :)\n Please find the attached docuemnt for your reference.");
			helper.setSubject("Mail From Spring Boot");
			ClassPathResource file = new ClassPathResource("document.PNG");
			helper.addAttachment("document.PNG", file);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}

}