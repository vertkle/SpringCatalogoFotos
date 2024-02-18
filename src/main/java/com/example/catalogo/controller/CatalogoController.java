package com.example.catalogo.controller;

import com.example.catalogo.model.dto.CatalogoDto;
import com.example.catalogo.service.CatalogoService;
import com.example.catalogo.service.CatalogoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
    @Autowired
    private CatalogoServiceImpl catalogoService;

    @GetMapping
    public ResponseEntity<List<CatalogoDto>>getAllCatalogo(){
        return new ResponseEntity<>(catalogoService.getAllCatalogos(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String>createCatalogo(@RequestBody CatalogoDto catalogoDto){
        catalogoService.createCatalogo(catalogoDto);
        return new ResponseEntity<>("creado correctamente", HttpStatus.CREATED);
    }
    @PostMapping("/upload")
    public ResponseEntity<String>uploadImg(@RequestParam("file")MultipartFile file, @RequestParam("idCatalogo") String idCatalogo) throws IOException {
        catalogoService.uploadImage(idCatalogo, file);
        return new ResponseEntity<>("imagen subida", HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CatalogoDto>getCatalogoById(@PathVariable Long id){
        return new ResponseEntity<>(catalogoService.getCatalogoById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCatalogo(@PathVariable Long id){
        catalogoService.deleteCatalogo(id);
        return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
    }
    @GetMapping("/test")
    public ResponseEntity<List<String>>getall(){
        return new ResponseEntity<>(catalogoService.getBlobs(), HttpStatus.OK);
    }
}
