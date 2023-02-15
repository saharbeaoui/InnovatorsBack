package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.UserRepository;

@Service
public class UserServiceImp implements UserServiceI{

    @Autowired
    UserRepository ur;

    @Override
    public User addUser(User user){
        return ur.save(user);
    }
}