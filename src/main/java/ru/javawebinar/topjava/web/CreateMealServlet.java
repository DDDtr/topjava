package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MemoryMealImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class CreateMealServlet extends HttpServlet {
    private static final Logger log = getLogger(CreateMealServlet.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final Dao<Meal> dao = MemoryMealImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Creating new meal");

        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"), DATE_TIME_FORMATTER);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal mealFromRequest = new Meal(dateTime, description, calories);

        if (id.isEmpty()) {
            dao.add(mealFromRequest);
        } else {
            mealFromRequest.setId(Integer.parseInt(id));
            dao.update(mealFromRequest);
        }

        String path = request.getContextPath() + "/meals";
        response.sendRedirect(path);
    }
}
