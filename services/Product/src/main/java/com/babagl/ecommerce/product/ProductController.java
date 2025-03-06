package com.babagl.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    @PostMapping
    private ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ){
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    @GetMapping("{product_id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product_id") Integer productId
    ){
        return ResponseEntity.ok(service.findById(productId));
    }
    /*
    * Dont forget to add Pagination
    * @return List ResponseProduct size page */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        log.info("Find all products######");
        return ResponseEntity.ok(service.findAll());
    }

}
