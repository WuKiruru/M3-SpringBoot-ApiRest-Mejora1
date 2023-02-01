package com.villoldo.api.controller;

import com.villoldo.api.model.Product;
import com.villoldo.api.servicio.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    @GetMapping("/product")
    public List<Product> listProducts() {
        return productService.listProducts();
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> obtainProduct(@PathVariable Integer id) {
        try {
            Product product = productService.obtainProductId(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping ("/product")
    public void newProduct (@RequestBody Product product) {
        productService.saveProduct(product);
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<?> editProduct (@RequestBody Product product, @PathVariable Integer id) {
        try {
            Product productExists = productService.obtainProductId(id);
            productExists.setProduct_name(product.getProduct_name());
            productExists.setPrice(product.getPrice());
            productService.saveProduct(productExists);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/product/{id}")
    public void deleteProduct (@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
