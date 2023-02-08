package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final int CALORIES_PER_DAY = 2000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");

        LocalTime startTime = LocalTime.MIN;
        LocalTime endTime = LocalTime.MAX;
        req.setAttribute("list_mealsTo", MealsUtil.filteredByStreams(MealsUtil.getMeals(), startTime, endTime, CALORIES_PER_DAY));
        req.setAttribute("calories_per_day", CALORIES_PER_DAY);

        //resp.sendRedirect("meals.jsp");
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
