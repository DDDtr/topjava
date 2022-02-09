package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryMealImpl implements Dao<Meal> {
    private static MemoryMealImpl instance;
    public List<Meal> meals = MealsUtil.mealList;
    private final AtomicInteger id = new AtomicInteger(meals.size());

    public static MemoryMealImpl getInstance() {
        if (instance == null) {
            instance = new MemoryMealImpl();
        }
        return instance;
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(id.incrementAndGet());
        meals.add(meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        meals.removeIf(item -> item.getId() == id);
    }

    @Override
    public Meal update(Meal meal) {
        Meal oldMeal = this.getById(meal.getId());
        oldMeal.setDateTime(meal.getDateTime());
        oldMeal.setDescription(meal.getDescription());
        oldMeal.setCalories(meal.getCalories());
        return oldMeal;
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public Meal getById(int id) {
        return meals.stream()
                .filter(meal -> meal.getId() == id)
                .findAny()
                .orElse(new Meal());
    }
}
