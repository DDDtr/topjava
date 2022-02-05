package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Dao<T> {
    void createTable();

    void add(T type);

    void delete(int id);

    void update(T type);

    List<T> getAll();

    T getById(int id);
}
