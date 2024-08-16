package br.com.security.exercicio.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "product")
@Table(name = "product")

@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private Double price;


    public Product(ProductRequestDTO productRequestDTO){
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
