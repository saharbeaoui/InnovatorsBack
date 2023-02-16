package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.BlockFoyer;
import tn.esprit.pidev4sae2back.entities.Claim;

import java.util.List;

public interface BlockFoyerServiceI {
    List<BlockFoyer> retrieveAllBlocks();
    BlockFoyer addBlockFoyer(BlockFoyer bf);
    BlockFoyer updateBlockFoyer (BlockFoyer bf);
    BlockFoyer retrieveBlockFoyer (Long idBlockFoyer);
    void deleteBlockFoyer(Long idBlockFoyer);
}
