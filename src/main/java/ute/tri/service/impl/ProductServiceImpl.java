package ute.tri.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ute.tri.entity.Product;
import ute.tri.repository.ProductRepository;
import ute.tri.service.ProductServices;

@Service
public class ProductServiceImpl implements ProductServices {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> listAll() {
        return repo.findAll();
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public Product get(Long id) {
        Optional<Product> product = repo.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found for ID: " + id));
    }

    @Override
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. Product not found for ID: " + id);
        }
    }
}
