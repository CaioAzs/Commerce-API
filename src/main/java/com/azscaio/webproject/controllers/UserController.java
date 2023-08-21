package com.azscaio.webproject.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.azscaio.webproject.models.User;
import com.azscaio.webproject.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "User", description = "Users CRUD")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Finds all users", description = "Finds all users.", tags = { "User" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			//@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
	})
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Finds someone by id", description = "Finds someone by id", tags = {
			"User" }, responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = User.class))),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					//@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Operation(summary = "Creates a new User", description = "Creates a new User by consuming a JSON representation of the specified User", tags = {
        "User" }, responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = User.class))),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                //@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Updates a User", description = "Updates a User by consuming a JSON representation of the specified User", tags = {
        "User" }, responses = {
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    @Operation(summary = "Updates a User", description = "Updates a User by consuming a JSON or XML representation of the specified User", tags = {
        "User" }, responses = {
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User entity) {
        entity = userService.update(id, entity);
        return ResponseEntity.ok().body(entity);
    }
}