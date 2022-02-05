package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.JdbcMealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class CreateMealServlet extends HttpServlet {
    private final Dao<Meal> mealDao;
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public CreateMealServlet() {
        super();
        mealDao = new JdbcMealDaoImpl();
    }

    private static final Logger log = getLogger(CreateMealServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Creating new meal");

        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"), DATE_TIME_FORMATTER);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal mealFromRequest = new Meal(dateTime, description, calories);
        mealDao.add(mealFromRequest);

        String path = request.getContextPath() + "/meals";
        response.sendRedirect(path);
    }
}
