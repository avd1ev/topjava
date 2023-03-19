package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController extends AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String getMeals(Model model) {
        log.info("meals");
        model.addAttribute("meals",
                MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()),
                        SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/meal-delete/{id}")
    public String deleteMeal(@PathVariable("id") Integer id) {
        log.info("delete meal");
        service.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @GetMapping("/meal-update/{id}")
    public String updateMeal(@PathVariable("id") Integer id, Model model) {
        log.info("update meal");
        model.addAttribute("meal", service.get(id, SecurityUtil.authUserId()));
        model.addAttribute("command", "update");
        return "mealForm";
    }

    @GetMapping("/meal-create")
    public String createMeal(Model model) {
        log.info("create meal");
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        model.addAttribute("command", "create");
        return "mealForm";
    }


    @PostMapping("/meal-save")
    public String saveMeal(HttpServletRequest request) {
        log.info("save meal");
        //request.setCharacterEncoding("UTF-8");

        int userId = SecurityUtil.authUserId();
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            meal.setId(Integer.valueOf(request.getParameter("id")));
            service.update(meal, userId);
        } else {
            service.create(meal, userId);
        }
        return "redirect:/meals";
    }

    @GetMapping("/meal-filter")
    public String filterMeal(HttpServletRequest request) {
        log.info("filter meal");

        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        request.setAttribute("meals", getBetween(startDate, startTime, endDate, endTime));

        return "meals";
    }
}
