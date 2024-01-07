package org.matorija.cookies.model;

import java.util.Map;

public class Colecciones {
    public static Map<String, String> getGeneros() {
        Map<String, String> generos = Map.of(
                "H" , "Hombre", "M", "Mujer", "O", "Otro"
        );
        return generos;
    }

    public static Map<String, String> getTratamientoDeseado() {
        Map<String, String> tratamientosDeseados = Map.of(
                "Sr" , "Señor", "Sra", "Señora", "Srto", "Señorito", "Srta", "Señorita",
                "C", "Caballero", "Ca", "Caballera"
        );
        return tratamientosDeseados;
    }

    public static Map<String, String> getNacionalidades() {
        Map<String, String> tratamientosDeseados = Map.of(
                "E", "España", "P", "Perú", "C", "Chile", "B", "Brasil"
        );
        return tratamientosDeseados;
    }

    public static Map<String, String> getDepartamentos(){
        Map<String, String> departamentos = Map.of(
                "A", "Accounting", "R", "Research", "S", "Sales", "O", "Operations"
        );
        return departamentos;
    }

//    public static boolean esValido(String usuario, String clave){
//
//        return usuarios_y_claves().containsKey(usuario) && clave.equals(usuarios_y_claves().get(usuario));
//    }
}
