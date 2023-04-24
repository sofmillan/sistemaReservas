package com.example.workshopLHotelAshir.model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "clientId")
    private Long clientId;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "address")
    private String address;
    @Column(name = "age")
    private String age;

    @Column(name = "email")
    private String email;

    public Client() {
    }

    public Client(Long clientId, String name, String lastName, String address, String age, String email) {
        this.clientId = clientId;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
