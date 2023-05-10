package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Role;
import tn.esprit.pidev4sae2back.entities.User;

import java.util.List;

public interface UserServiceI {
    User addUser(User user);
    List<User> getAllUsers();


    User update(User user, Long idUser);

    void delete(Long id);


    void updatePassword(User user, String newPassword) ;

    User getByResetPasswordToken(String token);

    public User getuser(String username);


    void updateResetPasswordToken(String token, String email)throws UserNotFoundException;

    User getUserById(Long idUser);

    int numberofusers();

    int numberofactiveusers();

    List<User> getManagers();

    User retrieveUser(Long userId);

}
