package com.example.catalogo.service;

import com.example.catalogo.model.dto.CatalogoDto;
import com.example.catalogo.repository.CatalogoRepository;
import com.example.catalogo.utils.Utilitarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CatalogoServiceImpl implements CatalogoService{
    @Autowired
    private CatalogoRepository catalogoRepository;
    @Autowired
    private BlobStorageService blobStorageService;
    @Override
    public void createCatalogo(CatalogoDto catalogoDto) {
        catalogoRepository.save(CatalogoDto.dtoToEntity(catalogoDto));
    }

    @Override
    public List<CatalogoDto> getAllCatalogos() {
        return catalogoRepository.findAll().stream()
                .map(CatalogoDto::entityToDto)
                .toList();
    }

    @Override
    public CatalogoDto getCatalogoById(Long id) {
        return catalogoRepository.findById(id)
                .map(CatalogoDto::entityToDto)
                .orElseThrow(() -> new RuntimeException("no se encontró"));
    }

    @Override
    public void deleteCatalogo(Long id) {
        CatalogoDto catalogoDto = getCatalogoById(id);
        catalogoRepository.delete(CatalogoDto.dtoToEntity(catalogoDto));

        //eliminar de storage
        blobStorageService.deleteBlob(catalogoDto.getPathImg());
    }

    @Override
    public void uploadImage(String idCatalogo, MultipartFile file) throws IOException {
        String format = file.getOriginalFilename().split("\\.")[1];
        String filenameGenerated = Utilitarios.generateUuid().concat(".").concat(format);

        //update db
        CatalogoDto catalogoDto = getCatalogoById(Long.valueOf(idCatalogo));
        catalogoDto.setPathImg(filenameGenerated);
        catalogoRepository.save(CatalogoDto.dtoToEntity(catalogoDto));

        //upload storage
        blobStorageService.uploadBlob(file, filenameGenerated);
    }

    public List<String> getBlobs(){
        return blobStorageService.listBlobs();
    }
}
