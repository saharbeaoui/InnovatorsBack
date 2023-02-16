package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.BlockRestau;

import java.util.List;

public interface BlockRestauServiceI {
    List<BlockRestau> retrieveAllBlockRestau();

    BlockRestau addBlockRestau (BlockRestau br);

    BlockRestau updateBlockRestau (BlockRestau br);

    BlockRestau  retrieveBlockRestau(Long idBlock);

    void archiveBlock(Long idBlock);

    void unarchiveBlock(Long idBlock);
}
