package br.com.security.exercicio.model.product;

import java.util.UUID;

public record ProductResponseDTO(Integer id,  String name, Double price) {
    public ProductResponseDTO(Product product){
        this(product.getId(),  product.getName(), product.getPrice());
    }
}
