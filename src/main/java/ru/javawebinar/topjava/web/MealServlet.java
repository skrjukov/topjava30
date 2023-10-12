package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

//        request.getRequestDispatcher("/users.jsp").forward(request, response);

            List<MealTo> mealsTo = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.of(00,00),
                            LocalTime.of(23,59), MealsUtil.caloriesPerDay);

        request.setAttribute("mealsTo", mealsTo);
//        getServletConfig().getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
