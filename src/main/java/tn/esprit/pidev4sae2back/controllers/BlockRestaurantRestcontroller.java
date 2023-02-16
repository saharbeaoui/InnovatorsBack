package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.BlockRestau;
import tn.esprit.pidev4sae2back.services.BlockRestauServiceI;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
public class BlockRestaurantRestcontroller {
    BlockRestauServiceI blockRestauServiceI;
    @GetMapping("/retrieveAllBlockRestau")
    @ResponseBody
    public List<BlockRestau> getAllBlockRestau(){
        List<BlockRestau> blockRestauList = blockRestauServiceI.retrieveAllBlockRestau();
        return blockRestauList;
    }
    @PostMapping("/addBlockRestau")
    @ResponseBody
    public BlockRestau addBlockRestau(@RequestBody BlockRestau bRestau){
        BlockRestau blockRestau = blockRestauServiceI.addBlockRestau(bRestau);
        return blockRestau;
    }
    @PutMapping("/updateBlockRestau")
    @ResponseBody
    public BlockRestau updateBlockRestau(@RequestBody BlockRestau bRestau){
        BlockRestau blockRestau = blockRestauServiceI.updateBlockRestau(bRestau);
        return blockRestau;
    }
    @GetMapping("/retrieveBlockRestau/{blockrestau-id}")
    @ResponseBody
    public BlockRestau getBlockRestau(@PathParam("blockrestau-id") Long idBlock){
        BlockRestau blockRestau = blockRestauServiceI.retrieveBlockRestau(idBlock);
        return blockRestau;
    }
    @PostMapping("/archive/{blockrestau-id}")
    @ResponseBody
    public void archiveBlock(@PathParam("blockrestau-id") Long idBlock){
        blockRestauServiceI.archiveBlock(idBlock);
    }
    @PostMapping("/unarchive/{blockrestau-id}")
    @ResponseBody
    public void unarchiveBlock(@PathParam("blockrestau-id") Long idBlock){
        blockRestauServiceI.unarchiveBlock(idBlock);
    }
}
