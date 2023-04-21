package com.example.workshopLHotelAshir.model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Client {

    @Id
    @Column(name = "cedula")
    private Long cedula;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;
    @Column(name = "edad")
    private String edad;

    @Column(name = "correoElectronico")
    private String correoElectronico;

    public Client() {
    }

    public Client(Long cedula, String nombre, String apellido,
                  String direccion, String edad, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
    }

    public Long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEdad() {
        return edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

}
