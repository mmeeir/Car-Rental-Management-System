package repositories;

import java.util.List;


public interface Repository<T> {
    void add(T entity);
    T findById(int id);
    List<T> findAll();

}