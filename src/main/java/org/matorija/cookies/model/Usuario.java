package org.matorija.cookies.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import validation.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@CoincidenciaClaves(groups = UsuarioErrores.class)
//@ExisteUsuario(groups = UsuarioErrores.class)
public class Usuario {
    //DatosUsuario
    @NotBlank(groups = {UsuarioErrores.class, ResumenErrores.class})
    private String nombre;
    @NotBlank(groups = {UsuarioErrores.class, ResumenErrores.class})
    private String clave;
    @NotBlank(groups = {UsuarioErrores.class, ResumenErrores.class})
    private String confirmarClave;

    //DatosPersonales
    private String tratamientoDeseado;
    private String apellido;
    private String genero;

    @NotNull(groups = {PersonalesErrores.class, ResumenErrores.class})
    private LocalDate fechaNacimiento;

    private boolean casado_pareja;
    private boolean hijos;

    @Size(min = 2, groups = {PersonalesErrores.class, ResumenErrores.class})
    @NotNull(groups = ResumenErrores.class)
    private String[] nacionalidades;

    //DatosProfesionales
    private String departamento;
    private String comentarios;


    private int edad;

    @Min(value = 1075, groups = {ProfesionalesErrores.class, ResumenErrores.class})
    private int salario;

}
