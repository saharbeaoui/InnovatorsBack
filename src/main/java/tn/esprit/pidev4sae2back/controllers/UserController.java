package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Role;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.services.UserServiceI;
import tn.esprit.pidev4sae2back.services.UserServiceImp;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserController {



    @Autowired
    UserServiceI us;


    @PostMapping("/addUser")
    public User addUser(@RequestBody User user)
    {
        return us.addUser(user);
    }

    @GetMapping("get-all-users")
    @ResponseBody
    @Transactional
    public List<User> getAllUsers(){
        return  us.getAllUsers();
    }



    @PostMapping("updateUser/{idUser}")
    @ResponseBody
    User update(@RequestBody User user, @PathVariable Long idUser){
        return us.update(user, idUser);
    }


    @DeleteMapping("delete-user/{id}")
    @ResponseBody
    public boolean DeleteUser(@PathVariable("id") Long id){
        us.delete(id);
        return true;
    }


    @GetMapping("getUserById/{userID}")
    @ResponseBody
    User getUserById(@PathVariable Long userID){
        return us.getUserById(userID);
    }


}
