package ru.javawebinar.topjava.dao;

import java.util.List;

public interface Dao<T> {

    T add(T obj);

    void delete(int id);

    T update(T obj);

    List<T> getAll();

    T getById(int id);
}
