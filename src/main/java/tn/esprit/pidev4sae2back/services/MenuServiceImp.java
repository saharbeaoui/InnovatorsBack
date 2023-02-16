package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Menu;
import tn.esprit.pidev4sae2back.repositories.MenuRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImp implements MenuServiceI{
    MenuRepository menuRepository;


    @Override
    public List<Menu> retrieveAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu addMenu(Menu m) {
        return menuRepository.save(m);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu retrieveMenu(Long idMenu) {
        return menuRepository.findById(idMenu).get();
    }

    @Override
    public void removeMenu(Long idMenu) {
        menuRepository.deleteById(idMenu);

    }
}
