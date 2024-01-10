package org.matorija.cookies.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.matorija.cookies.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import validation.PersonalesErrores;
import validation.ProfesionalesErrores;
import validation.ResumenErrores;
import validation.UsuarioErrores;

import static org.matorija.cookies.model.Colecciones.*;
import java.util.Map;
import java.util.Set;

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

    //Vista del login con nombre @GetMapping
    @GetMapping("loginNombre")
    public static String loginNombre(Model model, HttpSession session){
//        session.removeAttribute("usuario");
        model.addAttribute("usuarios",getUsuarios());
        return "loginUsuario";
    }

    //Vista del login con nombre @PostMapping
    @PostMapping("entraNombre")
    public static String entraNombre(@RequestParam("nombre") String nombre, HttpSession session){
        if (existeNombre(nombre)){
            session.setAttribute("usuario", nombre);
            return "redirect:/cookiesSesion/loginClave";
        }
        return "redirect:/cookiesSesion/loginNombre";
    }

    //Vista del login con clave @GetMapping
    @GetMapping("loginClave")
    public static String loginClave(Model model, HttpSession session){
        model.addAttribute("nombre", (Usuario) session.getAttribute("nombre"));
        return "loginClave";
    }

    //Vista del login con clave @PostMapping
    @PostMapping("entraClave")
    public static String entraClave(@RequestParam("clave") String clave, HttpSession session){
        if (existeClave((String) session.getAttribute("nombre"),clave)){
            return "redirect:/cookiesSesion/logueado";
        }
        return "redirect:/cookiesSesion/loginClave";
    }

    //Vista del registro de datos usuario @GetMapping
    @GetMapping("datosUsuario")
    public static String datosUsuario(@ModelAttribute Usuario usuario, HttpSession session, Model model){
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
            return "datosUsuario";
    }

    //Validación de datos de usuario @PostMapping
    @PostMapping("entraDatosUsuario")
    public static String entraDatosUsuario(@Validated(value = UsuarioErrores.class) @ModelAttribute Usuario usuario,
                                           BindingResult errores, HttpSession session, Model model){
        if (errores.hasErrors()) {
            return "datosUsuario";
        }
        session.setAttribute("usuario", usuario);
        return "redirect:/cookiesSesion/datosPersonales";
    }

    //Vista del registro de datos personales @GetMapping
    @GetMapping("datosPersonales")
    public static String datosPersonales(@ModelAttribute Usuario usuario,HttpSession session, Model model){
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
        return "datosPersonales";
    }

    //Validación de datos personales @PostMapping
    @PostMapping("entraDatosPersonales")
    public static String entraDatosPersonales(@Validated(value = PersonalesErrores.class) @ModelAttribute Usuario usuario, BindingResult errores, HttpSession session, Model model){
        if (errores.hasErrors()) {
            return "datosPersonales";
        }
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

    //Vista del registro de datos profesales @GetMapping
    @GetMapping("datosProfesionales")
    public static String datosProfesionales(@ModelAttribute Usuario usuario, Model model, HttpSession session){
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
        return "datosProfesionales";
    }

    //Validación de datos personales @PostMapping
    @PostMapping("entraDatosProfesionales")
    public static String entraDatosProfesionales(@Validated(value = ProfesionalesErrores.class) @ModelAttribute Usuario usuario,
                                                 BindingResult errores, HttpSession session, Model model){
        if (errores.hasErrors()) {
            return "datosProfesionales";
        }
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        usuarioSesion.setDepartamento(usuario.getDepartamento());
        usuarioSesion.setSalario(usuario.getSalario());
        usuarioSesion.setComentarios(usuario.getComentarios());
        session.setAttribute("usuario", usuarioSesion);
        return "redirect:/cookiesSesion/finalDatos";
    }

    //Vista del resumen final @GetMapping
    @GetMapping("finalDatos")
    public static String finalDatos(HttpSession session, Model model){
        if (session.getAttribute("usuario") == null) {
            model.addAttribute("usuario", new Usuario());
        }else {
            model.addAttribute("usuario", (Usuario) session.getAttribute("usuario"));
        }
        return "finalDatos";
    }

    //Validación de todos los datos en el resumen @PostMapping

    @PostMapping("entraFinal")
    public static String entraFinal(HttpSession session, Model model){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        if (usuario == null){
            usuario = new Usuario();
        }
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario, ResumenErrores.class);
        if (violations.isEmpty()) {
            anadirUsuario(usuario);
            session.removeAttribute("usuario");
            return "redirect:/cookiesSesion/loginNombre";
        }
        model.addAttribute("usuario", usuario);
        for (ConstraintViolation<Usuario> violation : violations) {
            model.addAttribute(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return "finalDatos";
    }

    //Vista final del usuario logueado @GetMapping

    @GetMapping("logueado")
    public static String logueado(){
        return "logueado";
    }

    // Vuelta al login inicial @PostMapping
    @PostMapping("entraLogueado")
    public static String entraLogueado(){
        return "redirect:/cookiesSesion/loginNombre";
    }
}

