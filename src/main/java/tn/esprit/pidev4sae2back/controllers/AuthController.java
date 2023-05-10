package tn.esprit.pidev4sae2back.controllers;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.esprit.pidev4sae2back.entities.ERole;
import tn.esprit.pidev4sae2back.entities.Role;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.RoleRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;
import tn.esprit.pidev4sae2back.security.jwt.JwtUtils;
import tn.esprit.pidev4sae2back.security.services.UserDetailsImpl;
import tn.esprit.pidev4sae2back.payload.request.LoginRequest;
import tn.esprit.pidev4sae2back.payload.request.SignupRequest;
import tn.esprit.pidev4sae2back.payload.response.JwtResponse;
import tn.esprit.pidev4sae2back.payload.response.MessageResponse;


@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private JavaMailSender sender;
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;


  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    if (userDetails.getState()) {
      return ResponseEntity.ok(new JwtResponse(jwt,
              userDetails.getId(),
              userDetails.getUsername(),
              userDetails.getEmail(),
              roles));
    } else {
      return ResponseEntity.ok(new MessageResponse("activate your account !"));
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }


    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(studentRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "teacher":
            Role teacherRole = roleRepository.findByName(ERole.ROLE_TEACHER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(teacherRole);

            break;
          case "guest":
            Role guestRole = roleRepository.findByName(ERole.ROLE_GUEST)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(guestRole);

            break;
          default:
            Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(studentRole);
        }
      });
    }

    user.setRoles(roles);
    MimeMessage message = sender.createMimeMessage();
   MimeMessageHelper helper = new MimeMessageHelper(message);

   try {
    helper.setTo(user.getEmail());


  user = userRepository.save(user);
 Long uid = user.getIdUser();
 String suid = String.valueOf(uid);
      helper.setText("http://localhost:8082/test/api/auth/activateUser/" + suid);
    helper.setSubject("confirm your account creation");
} catch (MessagingException e) {
e.printStackTrace();
}
//return "Error while sending mail ..";

sender.send(message);
    userRepository.save(user);
      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


  @GetMapping("/activateUser/{id}")
  @ResponseBody
  public  ResponseEntity<?> activateUser(@PathVariable Long id){

    User user= userRepository.findById(id).get();
    user.setState(true);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User activated successfully!"));
  }
}
