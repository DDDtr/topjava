package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.ConstantsInit;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forwarded to meals.jsp");
        List<MealTo> mealTo = MealsUtil.groupedByDayWithExcess(ConstantsInit.meals, ConstantsInit.CALORIES_PER_DAY);
        request.setAttribute("mealList", mealTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
