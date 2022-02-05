package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.JdbcMealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    JdbcMealDaoImpl dao = new JdbcMealDaoImpl();
    private static final int CALORIES_PER_DAY = 2000;
    private static final String CREATE_OR_UPDATE = "/add_meal.jsp";
    private static final String ALL_MEALS = "/meals.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = "";

        if (action.equalsIgnoreCase("update")) {
            forward = CREATE_OR_UPDATE;
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = dao.getById(id);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("all")) {
            forward = ALL_MEALS;
            log.debug("forwarded to meals.jsp");
            List<Meal> meals = dao.getAll();
            List<MealTo> mealTo = MealsUtil.markDateWithExcess(meals, CALORIES_PER_DAY);
            request.setAttribute("mealToList", mealTo);
        }
        request.getRequestDispatcher(forward).forward(request,response);
    }
}