package ee.learn.webstore.services;

import ee.learn.webstore.entities.Category;

import java.util.List;

//Using generic service for a CRUD category, products etc.
public interface GenericService<T> {

    List<T> findAll();
    T read(Long id);
    void createOrUpdate(T object);
    void delete (T object);
    void delete (Long id);

}
