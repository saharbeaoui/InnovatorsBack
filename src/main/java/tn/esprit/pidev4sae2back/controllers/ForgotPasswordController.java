package tn.esprit.pidev4sae2back.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.bytebuddy.utility.RandomString;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.UserRepository;
import tn.esprit.pidev4sae2back.services.UserNotFoundException;
import tn.esprit.pidev4sae2back.services.UserServiceImp;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
	
	
	 @Autowired
	    private JavaMailSender mailSender;
	     
	    @Autowired
		UserServiceImp userService;

	    @Autowired
		UserRepository userRepository;
	    
	    @PostMapping("/forgot_password")
	    public String processForgotPassword( Long idUser ,  String password) throws UserNotFoundException {
	    		User user = new User();
	    		user = userRepository.getById(idUser);
	    		String email = user.getEmail();
	    		
	    		String token = RandomString.make(30);
	    		userService.updateResetPasswordToken(token, email);
	    		MimeMessage message = mailSender.createMimeMessage();
	    		MimeMessageHelper helper = new MimeMessageHelper(message);

	    		try {
	    			helper.setTo(email);
	    			helper.setText( "http://localhost:8082/test/forgot-password/reset_password/"+token+"/"+password);
	    			helper.setSubject("Reset Password");
	    		} catch (MessagingException e) {
	    			e.printStackTrace();
	    			return "Error while sending mail ..";
	    		}
	    		mailSender.send(message);
	    		return "Mail Sent Success!";
	    }
	    
	    
	     @GetMapping("/reset_password/{token}/{password}")
	    public String processResetPassword( @PathVariable("token") String token, @PathVariable("password") String password) {
//	        String token = request.getParameter("token");
//	        String password = request.getParameter("password");
	         
	        User user = userService.getByResetPasswordToken(token);
	         
	        if (user == null) {
	            return "Invalid Token";
	        } else {           
	        	userService.updatePassword(user, password);
	            return "You have successfully changed your password.";
	        }
	         
	    }
	    
	    
}
	    



