package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.services.UserServiceI;
import tn.esprit.pidev4sae2back.services.UserServiceImp;

@RestController
public class UserController {



    @Autowired
    UserServiceI us;


    @PostMapping("/addUser")
    public User addUser(@RequestBody User user)
    {
        return us.addUser(user);
    }




}
