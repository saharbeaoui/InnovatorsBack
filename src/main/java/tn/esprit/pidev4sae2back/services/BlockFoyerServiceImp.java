package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.BlockFoyer;
import tn.esprit.pidev4sae2back.entities.Foyer;
import tn.esprit.pidev4sae2back.repositories.FoyerRepository;
import tn.esprit.pidev4sae2back.repositories.BlockFoyerRepository;
import java.util.List;

@Service
public class BlockFoyerServiceImp implements BlockFoyerServiceI{
    @Autowired
    BlockFoyerRepository blockFoyerRepository;

    @Autowired
    FoyerRepository foyerRepository;

    @Override
    public BlockFoyer addBlockFoyer(BlockFoyer blockFoyer){
        return blockFoyerRepository.save(blockFoyer);
    }

    @Override
    public BlockFoyer getBlockFoyer(Long idBlockFoyer) {
        return blockFoyerRepository.findById(idBlockFoyer).orElse(null);
    }
    @Override
    public List<BlockFoyer> getAllBlockFoyers() {
        return blockFoyerRepository.findAll();
    }

    @Override
    public BlockFoyer updateBlockFoyer(BlockFoyer blockFoyer) {
        return blockFoyerRepository.save(blockFoyer);
    }

    @Override
    public void removeBlockFoyer(Long idBlockFoyer) {
        blockFoyerRepository.deleteById(idBlockFoyer);
    }
    @Override

    public BlockFoyer addBlockFoyerToAFoyer(BlockFoyer blockFoyer, Long foyerId) {
        Foyer foyer = foyerRepository.findById(foyerId).orElse(null);
        if (foyer != null) {
            blockFoyer.setFoyer(foyer);
            return blockFoyerRepository.save(blockFoyer);
        }
        return null;
    }
}
