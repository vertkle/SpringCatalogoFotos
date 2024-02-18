package com.example.catalogo.service;

import com.example.catalogo.model.Catalogo;
import com.example.catalogo.model.Categorias;
import com.example.catalogo.model.dto.CatalogoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CatalogoService {
    void createCatalogo(CatalogoDto catalogoDto);
    List<CatalogoDto>getAllCatalogos();
    CatalogoDto getCatalogoById(Long id);
    void deleteCatalogo(Long id);

    void uploadImage(String idCatalogo, MultipartFile multipartFile) throws IOException;
}
