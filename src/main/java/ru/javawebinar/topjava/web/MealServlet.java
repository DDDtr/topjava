package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MemoryMealImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final Dao<Meal> dao = MemoryMealImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("forwarded to meals.jsp");

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equalsIgnoreCase("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Meal meal = dao.getById(id);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("add_meal.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
            }
        }

        List<Meal> meals = dao.getAll();
        List<MealTo> mealTo = MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), MealsUtil.CALORIES_PER_DAY);
        request.setAttribute("mealToList", mealTo);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}