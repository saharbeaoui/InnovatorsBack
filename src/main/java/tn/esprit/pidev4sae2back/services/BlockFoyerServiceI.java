package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.BlockFoyer;

import java.util.List;

public interface BlockFoyerServiceI {
    BlockFoyer addBlockFoyer(BlockFoyer blockFoyer);


    List<BlockFoyer> getAllBlockFoyers();

    BlockFoyer updateBlockFoyer(BlockFoyer blockFoyer);

    BlockFoyer getBlockFoyer(Long idBlockFoyer);

    void removeBlockFoyer(Long idBlockFoyer);

    BlockFoyer addBlockFoyerToAFoyer(BlockFoyer blockFoyer, Long foyerId);
}
