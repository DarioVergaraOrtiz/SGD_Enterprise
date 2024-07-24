package uce.edu.ec.SDG_Enterprise.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String material;
    private double price;

    @OneToMany(mappedBy = "product")
    private Set<Requested> pedidos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_process",
            joinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "process_id", referencedColumnName = "id"))
    private Set<Process> process = new HashSet<>();

    public Set<Process> getProcess() {
        return process;
    }

    public void setProcess(Set<Process> process) {
        this.process = process;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Requested> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Requested> pedidos) {
        this.pedidos = pedidos;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
