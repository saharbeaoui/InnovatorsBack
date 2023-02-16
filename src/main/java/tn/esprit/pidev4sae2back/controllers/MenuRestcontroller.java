package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Menu;
import tn.esprit.pidev4sae2back.services.MenuServiceI;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
public class MenuRestcontroller {
    MenuServiceI menuServiceI;
    @GetMapping("/retrieveAllMenus")
    @ResponseBody
    public List<Menu> getAllMenus(){
        List<Menu> listmenu = menuServiceI.retrieveAllMenu();
        return listmenu;
    }
    @PostMapping("/addMenu")
    @ResponseBody
    public Menu addMenu(@RequestBody Menu m){
        Menu menu = menuServiceI.addMenu(m);
        return menu;
    }
    @PutMapping("/updateMenu")
    @ResponseBody
    public Menu updateMenu(@RequestBody Menu m){
        Menu menu = menuServiceI.updateMenu(m);
        return  menu;
    }
    @GetMapping("/retrievemenu/{menu-id}")
    @ResponseBody
    public Menu getMenu(@PathParam("menu-id") Long idMenu){
        Menu menu = menuServiceI.retrieveMenu(idMenu);
        return menu;
    }
    @DeleteMapping("/remove-menu/{menu-id}")
    @ResponseBody
    public void removemenu(@PathParam("menu-id")Long idMenu){
       menuServiceI.removeMenu(idMenu);
    }
}
