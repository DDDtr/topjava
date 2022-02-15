package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(SecurityUtil.authUserId());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        Meal meal = repository.get(id);
        if (meal.getUserId() != SecurityUtil.authUserId()) {
            return false;
        }
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        Meal meal = repository.get(id);
        if (meal.getUserId() != SecurityUtil.authUserId()) {
            return null;
        }
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() { //todo
        Collection<Meal> collection = repository.values();
        Comparator<Meal> comparator = Comparator.comparing(meal -> meal.getDateTime());
        comparator = comparator.reversed();
        return Collections.emptyList();
    }

}

