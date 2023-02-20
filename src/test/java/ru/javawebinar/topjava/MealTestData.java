package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int FIRST_MEAL = START_SEQ + 3;
    public static final int SECOND_MEAL = START_SEQ + 4;
    public static final int THIRD_MEAL = START_SEQ + 5;

    public static final Meal first = new Meal(FIRST_MEAL, LocalDateTime.of(2022, 2, 22, 8, 0), "breakfast", 900);
    public static final Meal second = new Meal(SECOND_MEAL, LocalDateTime.of(2022, 2, 22, 12, 0), "dinner", 1400);
    public static final Meal third = new Meal(THIRD_MEAL, LocalDateTime.of(2022, 2, 22, 18, 0), "vechir", 800);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(1000, 1, 1, 0, 0), "new", 0);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(first);
        updated.setDescription("updated");
        updated.setDateTime(LocalDateTime.of(1000, 1, 1, 0, 0));
        updated.setCalories(1);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }


}
