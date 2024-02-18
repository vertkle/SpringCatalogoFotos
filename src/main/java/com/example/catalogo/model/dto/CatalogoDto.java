package com.example.catalogo.model.dto;

import com.example.catalogo.model.Catalogo;
import com.example.catalogo.model.Categorias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoDto {
    private Long id;
    private Categorias categorias;
    private String nombre;
    private String pathImg;

    public static Catalogo dtoToEntity(CatalogoDto catalogoDto){
        ModelMapper mp = new ModelMapper();
        return mp.map(catalogoDto, Catalogo.class);
    }
    public static CatalogoDto entityToDto(Catalogo catalogo){
        ModelMapper mp = new ModelMapper();
        return mp.map(catalogo, CatalogoDto.class);
    }
}


