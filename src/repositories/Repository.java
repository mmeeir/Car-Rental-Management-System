package repositories;

import java.util.List;
import models.Rental;

public interface Repository<T> {
    void add(T entity);
    T findById(int id);
    List<T> findAll();
}