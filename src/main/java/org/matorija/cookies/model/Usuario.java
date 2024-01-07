package org.matorija.cookies.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    private String nombre;
    private String clave;
    private String confirmarClave;
    private String tratamientoDeseado;
    private String apellido;
    private String genero;
    private String departamento;
    private String comentarios;

    private boolean casado_pareja;
    private boolean hijos;

//    private LocalDate fechaNacimiento;
    private String fechaNacimiento;

    private int salario;
    private int edad;

    private String[] nacionalidades;

}
