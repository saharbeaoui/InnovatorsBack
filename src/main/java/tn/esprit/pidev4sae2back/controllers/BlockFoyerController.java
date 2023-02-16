package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.BlockFoyer;
import tn.esprit.pidev4sae2back.entities.Claim;
import tn.esprit.pidev4sae2back.services.BlockFoyerServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blockFoyer")
public class BlockFoyerController {
    BlockFoyerServiceImp blockFoyerServiceImp;
    //http://localhost:8082/test/blockFoyer/retrieveAllBlocks
    @GetMapping("/retrieveAllBlocks")
    public List<BlockFoyer> retrieveAllBlocks(){
        return blockFoyerServiceImp.retrieveAllBlocks();
    }

    //http://localhost:8082/test/blockFoyer/addBlockFoyer
    @PostMapping("/addBlockFoyer")
    public BlockFoyer addBlockFoyer(@RequestBody BlockFoyer bf) {
        return blockFoyerServiceImp.addBlockFoyer(bf);
    }
    //http://localhost:8082/test/blockFoyer/updateBlockFoyer
    @PutMapping("/updateBlockFoyer")
    public BlockFoyer updateBlockFoyer(@RequestBody BlockFoyer bf) {
        return blockFoyerServiceImp.updateBlockFoyer(bf);
    }
    //http://localhost:8082/test/blockFoyer/BlockFoyer/{idBlockFoyer}
    @GetMapping("/BlockFoyer/{idBlockFoyer}")
    public BlockFoyer retrieveBlockFoyer(@PathVariable(value = "idBlockFoyer") Long idBlockFoyer) {
        return blockFoyerServiceImp.retrieveBlockFoyer(idBlockFoyer);
    }
    //http://localhost:8082/test/blockFoyer/deleteBlockFoyer/{idBlockFoyer}
    @DeleteMapping("/deleteBlockFoyer/{idBlockFoyer}")
    public void deleteBlockFoyer(@PathVariable("idBlockFoyer")Long idBlockFoyer){
        blockFoyerServiceImp.deleteBlockFoyer(idBlockFoyer);
    }

}
