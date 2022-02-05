package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.ConstantsInit;

import java.util.List;

public class DaoHelper {
    public static void main(String[] args) {
        MealDao mealDao = new MealDao();

        mealDao.createTable();

        List<Meal> meals = ConstantsInit.meals;
        for (Meal meal : meals) {
            mealDao.addMeal(meal);
        }

        final List<Meal> allMeals = mealDao.getAllMeals();
        allMeals.forEach(System.out::println);
    }
}
