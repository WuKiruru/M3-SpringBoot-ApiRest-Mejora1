package com.villoldo.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
        
public class Product {
    @Id @GeneratedValue
    private Integer id;
    private String product_name;
    private float price;
}