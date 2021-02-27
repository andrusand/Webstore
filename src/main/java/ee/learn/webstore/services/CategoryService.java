package ee.learn.webstore.services;


import ee.learn.webstore.entities.Category;
import ee.learn.webstore.repositories.CategoryRepository;
import ee.learn.webstore.utils.ProductDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Autowired
@Service
public class CategoryService implements GenericService<Category> {

    @Autowired
    CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category read(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void createOrUpdate(Category object) {
        repository.save(object);
    }

    @Override
    public void delete(Category object) {
        repository.delete(object);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
