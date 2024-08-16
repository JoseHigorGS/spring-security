package br.com.security.exercicio.controller;

import br.com.security.exercicio.model.product.Product;
import br.com.security.exercicio.model.product.ProductRequestDTO;
import br.com.security.exercicio.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("product")
public class ProductCotroller {
    @Autowired
    private ProductRepository repository;

    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody ProductRequestDTO productRequestDTO){
        Product product = new Product(productRequestDTO);
        repository.save(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProduct(){
        return ResponseEntity.ok().body((ArrayList<Product>) repository.findAll());
    }
}
