package org.matorija.cookies.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.matorija.cookies.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static org.matorija.cookies.model.Colecciones.*;
import java.util.Map;

@Controller
@RequestMapping("cookiesSesion")
public class ControladorSesion {

    @ModelAttribute("listaGenero")
    public static Map generos(){
        return getGeneros();
    }

    @ModelAttribute("listaTratamiento")
    public static Map tratamientos(){
        return getTratamientoDeseado();
    }

    @ModelAttribute("listaNacionalidad")
    public static Map nacionalidades(){
        return getNacionalidades();
    }

    @ModelAttribute("listaDepartamento")
    public static Map departamentos(){
        return getDepartamentos();
    }

    //Vista del input de usuario.
    @GetMapping("datosUsuario")
    public static String datosUsuario(@ModelAttribute Usuario usuario, HttpSession session, Model model){
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
            return "datosUsuario";
    }

    //Comprueba si el usuario existe.
    @PostMapping("entraDatosUsuario")
    public static String compruebaDatosUsuario(@ModelAttribute Usuario usuario, HttpSession session, Model model){
        session.setAttribute("usuario", usuario);
        return "redirect:/cookiesSesion/datosPersonales";
    }

    //Vista del input de clave.
    @GetMapping("datosPersonales")
    public static String datosPersonales(@ModelAttribute Usuario usuario,HttpSession session, Model model){
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
        return "datosPersonales";
    }

    //Comprueba si la clave es correcta.

    @PostMapping("entraDatosPersonales")
    public static String compruebaDatosPersonales(@ModelAttribute Usuario usuario, HttpSession session, Model model){
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        usuarioSesion.setTratamientoDeseado(usuario.getTratamientoDeseado());
        usuarioSesion.setApellido(usuario.getApellido());
        usuarioSesion.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioSesion.setEdad(usuario.getEdad());
        usuarioSesion.setGenero(usuario.getGenero());
        usuarioSesion.setHijos(usuario.isHijos());
        usuarioSesion.setCasado_pareja(usuario.isCasado_pareja());
        usuarioSesion.setNacionalidades(usuario.getNacionalidades());
        session.setAttribute("usuario", usuarioSesion);
        return "redirect:/cookiesSesion/datosProfesionales";
    }

    //Pasos intermedios hechos en un solo método.
    @GetMapping("datosProfesionales")
    public static String datosProfesionales(@ModelAttribute Usuario usuario, Model model, HttpSession session){
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
        return "datosProfesionales";
    }

    @PostMapping("entraDatosProfesionales")
    public static String compruebaDatosProfesionales(@ModelAttribute Usuario usuario, HttpSession session, Model model){
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        usuarioSesion.setDepartamento(usuario.getDepartamento());
        usuarioSesion.setSalario(usuario.getSalario());
        usuarioSesion.setComentarios(usuario.getComentarios());
        session.setAttribute("usuario", usuarioSesion);
        return "redirect:/cookiesSesion/finalDatos";
    }

    //Pasos intermedios antes de loguearse al completo uno por uno.
    @GetMapping("finalDatos")
    public static String finalFormulario(HttpSession session, Model model){
        if (session.getAttribute("usuario") == null) {
            model.addAttribute("usuario", new Usuario());
        }else {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
        return "finalDatos";
    }
}

