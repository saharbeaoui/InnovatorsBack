package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserServiceI{

    @Autowired
    UserRepository ur;

    @Override
    public User addUser(User user){
        return ur.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return ur.findAll();
    }

    @Override
    public User update(User user, Long idUser) {
        user.setIdUser(idUser);
        return ur.save(user);
    }



    @Override
    public void delete(Long id) {
        ur.deleteById(id);
    }

    @Override

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        ur.save(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return ur.findByResetPasswordToken(token);
    }

    @Override
    public User getuser(String username) {
        return ur.getByusername(username);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = ur.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            ur.save(user);
        } else {
            throw new UserNotFoundException("Could not find any user with the email " + email);
        }


    }

    @Override
    public User getUserById(Long userID) {
        return null;
    }

    @Override
    public int numberofusers() {
        return 0;
    }

    @Override
    public int numberofactiveusers() {
        return 0;
    }

    @Override
    public List<User> getManagers() {
        return null;

    public User retrieveUser(Long userId) {
        Optional<User> userOptional = ur.findById(userId);
        return userOptional.orElse(null);

    }


}
