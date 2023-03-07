package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User update(User user) {
        return ur.save(user);
    }

    @Override
    public void delete(Long id) {
        ur.deleteById(id);
    }

    @Override
    public User retrieveUser(Long userId) {
        Optional<User> userOptional = ur.findById(userId);
        return userOptional.orElse(null);
    }


}
