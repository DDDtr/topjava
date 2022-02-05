package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcMealDaoImpl implements Dao<Meal>{

    private final Connection conn;

    public JdbcMealDaoImpl() {
        conn = DbUtil.getConn();
    }

    public void createTable() {
        try {
            Statement stmnt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS MEALS (\n" +
                    "    meal_id int NOT NULL AUTO_INCREMENT,\n" +
                    "    datetime timestamp DEFAULT NULL,\n" +
                    "    description varchar(50) DEFAULT NULL,\n" +
                    "    calories int DEFAULT NULL,\n" +
                    "    PRIMARY KEY (meal_id))";
            stmnt.executeUpdate(sql);
            stmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Meal meal) {
        try {
            PreparedStatement prepStmnt =
                    conn.prepareStatement("INSERT INTO MEALS(datetime, description, calories) VALUES ( ?,?,? ) ");
            prepStmnt.setTimestamp(1, Timestamp.valueOf(meal.getDateTime()));
            prepStmnt.setString(2, meal.getDescription());
            prepStmnt.setInt(3, meal.getCalories());
            prepStmnt.executeUpdate();
            prepStmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement prepStmnt =
                    conn.prepareStatement("DELETE FROM meals WHERE meal_id=?");
            prepStmnt.setInt(1, id);
            prepStmnt.executeUpdate();
            prepStmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Meal meal) {
        try {
            PreparedStatement prepStmnt =
                    conn.prepareStatement("UPDATE meals SET datetime=?, description=?, calories=? WHERE meal_id=?");
            prepStmnt.setTimestamp(1, Timestamp.valueOf(meal.getDateTime()));
            prepStmnt.setString(2, meal.getDescription());
            prepStmnt.setInt(3, meal.getCalories());
            prepStmnt.setInt(4, meal.getId());
            prepStmnt.executeUpdate();
            prepStmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>();
        try {
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM MEALS");
            while (rs.next()) {
                int id = rs.getInt("meal_id");
                Timestamp timestamp = rs.getTimestamp("datetime");
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                String description = rs.getString("description");
                int calories = rs.getInt("calories");
                Meal meal = new Meal();
                meal.setId(id);
                meal.setDateTime(dateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
                meals.add(meal);
            }
            stmnt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    @Override
    public Meal getById(int id) {
        Meal meal = new Meal();
        try {
            PreparedStatement prepStmnt = conn.prepareStatement("SELECT * FROM meals WHERE meal_id=?");
            prepStmnt.setInt(1, id);
            ResultSet rs = prepStmnt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("datetime");
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                String description = rs.getString("description");
                int calories = rs.getInt("calories");
                meal.setId(id);
                meal.setDateTime(dateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
            }
            prepStmnt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }
}
