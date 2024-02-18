package com.example.catalogo.utils;

import java.util.UUID;

public class Utilitarios {
    public static String generateUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
