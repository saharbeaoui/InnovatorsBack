package tn.esprit.pidev4sae2back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.BlockFoyer;
import tn.esprit.pidev4sae2back.services.BlockFoyerServiceI;

import java.util.List;

    @RestController
    @RequestMapping("/blockfoyer")
    public class BlockFoyerController {

        @Autowired
        BlockFoyerServiceI blockFoyerServiceI;


        @PostMapping("/addBlockFoyer")
        public BlockFoyer addBlockFoyer(@RequestBody BlockFoyer blockFoyer) {
            return blockFoyerServiceI.addBlockFoyer(blockFoyer);
        }

        @GetMapping("/getBlockFoyer/{idBlockFoyer}")
        @ResponseBody
        public BlockFoyer getBlockFoyer(@PathVariable Long idBlockFoyer){
            return blockFoyerServiceI.getBlockFoyer(idBlockFoyer);
        }

        @GetMapping("/getAllBlockFoyers")
        public List<BlockFoyer> getAllBlockFoyers(){
            return blockFoyerServiceI.getAllBlockFoyers();
        }

        @PutMapping("/updateBlockFoyer")
        public BlockFoyer updateBlockFoyer(@RequestBody BlockFoyer blockFoyer){
            return blockFoyerServiceI.updateBlockFoyer(blockFoyer);
        }


        @DeleteMapping("/removeBlockFoyer/{idBlockFoyer}")
        public void removeBlockFoyer(@PathVariable Long idBlockFoyer) {
            blockFoyerServiceI.removeBlockFoyer(idBlockFoyer);
        }

        @PostMapping("/addBlockFoyerAndAssToFoyer/{idFoyer}")
        public BlockFoyer addBlockFoyerAndAssToFoyer(@RequestBody BlockFoyer blockFoyer,@PathVariable Long idFoyer) {
            return blockFoyerServiceI.addBlockFoyerToAFoyer(blockFoyer,idFoyer);
        }


    }
