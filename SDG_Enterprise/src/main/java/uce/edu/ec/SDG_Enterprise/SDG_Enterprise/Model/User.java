package uce.edu.ec.SDG_Enterprise.Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rucCedula;
    private String name;
    private String username;
    private String password;
    private String rol;
    private String email;
    private String phone;

    public User() {
    }

    public User(String email, String name, String password, String phone, String rol, String rucCedula, String username) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.pedidos = pedidos;
        this.phone = phone;
        this.rol = rol;
        this.rucCedula = rucCedula;
        this.username = username;
    }

    @OneToMany(mappedBy = "user")
    private Set<Requested> pedidos;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Requested> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Requested> pedidos) {
        this.pedidos = pedidos;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRucCedula() {
        return rucCedula;
    }

    public void setRucCedula(String rucCedula) {
        this.rucCedula = rucCedula;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
