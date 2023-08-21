package com.azscaio.webproject.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.azscaio.webproject.models.Product;
import com.azscaio.webproject.models.User;
import com.azscaio.webproject.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Product", description = "Products CRUD")

public class ProductController {

    @Autowired
    private ProductService ProductService;

    @GetMapping
    @Operation(summary = "Finds all products", description = "Finds all products.", tags = { "Product" }, responses = {
        @ApiResponse(description = "Success", responseCode = "200", content = @Content),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        //@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
})
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = ProductService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Finds a product by id", description = "Finds a product by id", tags = {
			"Product" }, responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = User.class))),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					//@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product Product = ProductService.findById(id);
        return ResponseEntity.ok().body(Product);
    }

    @PostMapping
    @Operation(summary = "Creates a new product", description = "Creates a new product by consuming a JSON representation of the specified product", tags = {
        "Product" }, responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = Product.class))),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                //@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
    public ResponseEntity<Product> insert(@RequestBody Product obj) {
        obj = ProductService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Updates a product", description = "Updates a product by consuming a JSON representation of the specified product", tags = {
        "Product" }, responses = {
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                //@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ProductService.delete(id);
        return ResponseEntity.noContent().build();
    }//Only possible if the product isnt in any Order

    @PutMapping(value="/{id}")
    @Operation(summary = "Updates a product", description = "Updates a product by consuming a JSON or XML representation of the specified product", tags = {
        "Product" }, responses = {
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                //@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product entity) {
        entity = ProductService.update(id, entity);
        return ResponseEntity.ok().body(entity);
    }

}