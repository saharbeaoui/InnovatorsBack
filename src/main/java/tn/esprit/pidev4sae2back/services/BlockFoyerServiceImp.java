package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.BlockFoyer;
import tn.esprit.pidev4sae2back.repositories.BlockFoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class BlockFoyerServiceImp implements BlockFoyerServiceI{
    BlockFoyerRepository blockFoyerRepository;
    @Override
    public List<BlockFoyer> retrieveAllBlocks() {
        return blockFoyerRepository.findAll();
    }

    @Override
    public BlockFoyer addBlockFoyer(BlockFoyer bf) {
        return blockFoyerRepository.save(bf);
    }

    @Override
    public BlockFoyer updateBlockFoyer(BlockFoyer bf) {
        return blockFoyerRepository.save(bf) ;
    }

    @Override
    public BlockFoyer retrieveBlockFoyer(Long idBlockFoyer) {
        return blockFoyerRepository.findById((Long)idBlockFoyer).orElse(null);
    }

    @Override
    public void deleteBlockFoyer(Long idBlockFoyer) {
        blockFoyerRepository.deleteById(idBlockFoyer);
    }
}
