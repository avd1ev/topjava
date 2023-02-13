package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            //mealRestController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, 1), 1);
            mealRestController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 800, 2), 1);
            mealRestController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 12, 0), "Завтрак", 600, 2), 1);
            mealRestController.create(new Meal(LocalDateTime.of(2021, Month.JANUARY, 30, 10, 0), "Завтрак", 500, 2), 1);

            //mealRestController.getBetweenDateTime(LocalDate.of(2020, Month.JANUARY, 29), LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(8, 0), LocalTime.of(12, 1), SecurityUtil.authUserId());
            System.out.println(mealRestController.getAll(SecurityUtil.authUserId()));
            System.out.println(mealRestController.getBetweenDateTime(LocalDate.of(2020, Month.JANUARY, 29), LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(8, 0), LocalTime.of(12, 1), 1));
            System.out.println(adminUserController.getAll());
        }
    }
}
