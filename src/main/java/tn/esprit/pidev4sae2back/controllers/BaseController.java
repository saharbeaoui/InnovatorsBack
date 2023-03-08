package tn.esprit.pidev4sae2back.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.pidev4sae2back.entities.BaseEntity;
import tn.esprit.pidev4sae2back.services.BaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T extends BaseEntity, ID> {
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    BaseService<T, ID> baseService;

    @GetMapping(produces = "application/json")
    @Operation(summary = "get all")
    public ResponseEntity<List<T>> getAll() {
        if (baseService.getAll().isEmpty())
            return new ResponseEntity<>(baseService.getAll(), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(baseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "get by id")
    @GetMapping(path = "/byId/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable ID id) {
        if (baseService.findById(id).isEmpty())
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(baseService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "delete by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ID id) {
        if (baseService.findById(id).isEmpty())
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        baseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get all by owner")
    @GetMapping(path = "/byOwner/{id}", produces = "application/json")
    public ResponseEntity<?> findAllByOwner(@PathVariable ID id) {
        if (baseService.findAllByOwner(id).isEmpty())
            return new ResponseEntity<>(baseService.findAllByOwner(id), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(baseService.findAllByOwner(id), HttpStatus.OK);
    }


}
