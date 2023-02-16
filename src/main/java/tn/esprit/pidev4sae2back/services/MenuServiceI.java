package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Menu;

import java.util.List;

public interface MenuServiceI {
    List<Menu> retrieveAllMenu();

    Menu addMenu (Menu menu);

    Menu updateMenu (Menu menu);

    Menu retrieveMenu(Long idMenu);

    void removeMenu(Long idMenu);
}
