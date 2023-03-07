package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.User;

import java.util.List;

public interface UserServiceI {
    User addUser(User user);
    List<User> getAllUsers();
    User update (User user);

    void delete(Long id);

    User retrieveUser(Long userId);
}
