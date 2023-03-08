package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.BlockRestau;
import tn.esprit.pidev4sae2back.entities.Restaurant;
import tn.esprit.pidev4sae2back.repositories.BlockRestauRepository;
import tn.esprit.pidev4sae2back.repositories.RestaurantRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BlockRestauServiceImp implements BlockRestauServiceI{
    BlockRestauRepository blockRestauRepository;
    RestaurantRepository restaurantRepository;
    @Override
    public List<BlockRestau> retrieveAllBlockRestau() {
        return blockRestauRepository.findAll();
    }

    @Override
    public BlockRestau addBlockRestau(BlockRestau br) {
        return blockRestauRepository.save(br);
    }

    @Override
    public BlockRestau updateBlockRestau(BlockRestau br) {
        return blockRestauRepository.save(br);
    }

    @Override
    public BlockRestau retrieveBlockRestau(Long idBlock) {
        return blockRestauRepository.findById(idBlock).get();
    }

    @Override
    public void archiveBlock(Long idBlock) {
        BlockRestau blockRestau = blockRestauRepository.findById(idBlock).get();
        blockRestau.archiveBlock();
        blockRestauRepository.save(blockRestau);

    }

    @Override
    public void unarchiveBlock(Long idBlock) {
        BlockRestau blockRestau = blockRestauRepository.findById(idBlock).get();
        blockRestau.unarchiveBlock();
        blockRestauRepository.save(blockRestau);

    }

    @Override
    public void assignBlockRtoRestaurant(Long idBlock, Long idRestau) {
        BlockRestau blockRestau = blockRestauRepository.findById(idBlock).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(idRestau).orElse(null);
        blockRestau.setRestaurant(restaurant);
        restaurant.getBlockRestaus().add(blockRestau);
        restaurantRepository.save(restaurant);
        blockRestauRepository.save(blockRestau);


    }
}
