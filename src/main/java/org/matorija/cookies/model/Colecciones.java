package org.matorija.cookies.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;
public class Colecciones {

    public static ArrayList<Usuario> usuarios = new ArrayList<>();
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

    public static void anadirUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public static boolean existeNombre(String nombre){
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static boolean existeClave(String nombre,String clave){
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getClave().equals(clave)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }
}
