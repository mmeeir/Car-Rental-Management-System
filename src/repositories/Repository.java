package repositories;

import java.util.List;

public interface Repository<T> {

    boolean create(T entity);

    List<T> findAll();

    T findById(int id);

    void add(T rental);

    T findById();
}
