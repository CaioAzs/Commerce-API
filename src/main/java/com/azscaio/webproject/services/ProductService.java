package com.azscaio.webproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.azscaio.webproject.models.Product;
import com.azscaio.webproject.models.User;
import com.azscaio.webproject.repositories.ProductRepository;
import com.azscaio.webproject.services.exceptions.DatabaseException;
import com.azscaio.webproject.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.get();
    }

    public Product insert(Product obj) {
        return productRepository.save(obj);
    }

    public void delete(Long id) {
        try{
            productRepository.deleteById(id);    
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    } //Only possible if the product isnt at a Order

    public Product update (Long id, Product obj){
        try{
            Product entity = productRepository.getReferenceById(id);
            updateData(entity, obj);
            return productRepository.save(entity);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new ResourceNotFoundException(id);
        }
    }
    
    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setImg_url(obj.getImg_url());
        entity.getCategories().clear();
        entity.getCategories().addAll(obj.getCategories());
    }
}