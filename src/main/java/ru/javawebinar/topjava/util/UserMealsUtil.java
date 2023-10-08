package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> result = new ArrayList<>();
        int mealCaloriesPerDay = 0;
        LocalDate localDate = LocalDate.of(0, 1, 1);
        for(UserMeal meal : meals ) {
            if (localDate.isEqual(meal.getDateTime().toLocalDate())){
                for(UserMeal meal1 : meals ){
                    if (meal1.getDateTime().toLocalDate().isEqual(meal.getDateTime().toLocalDate())){
                        mealCaloriesPerDay = mealCaloriesPerDay + meal1.getCalories();
                    }
                }
            }
            if (meal.getDateTime().toLocalTime().isAfter(startTime) & meal.getDateTime().toLocalTime().isBefore(endTime)){
                result.add(new UserMealWithExcess(meal.getDateTime(),meal.getDescription(), meal.getCalories(),
                        mealCaloriesPerDay > caloriesPerDay));
                localDate = meal.getDateTime().toLocalDate();
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        List<UserMeal> auxUserMealList = meals.stream().filter(meal -> meal.getDateTime().toLocalTime().isAfter(startTime) &&
                meal.getDateTime().toLocalTime().isBefore(endTime))
                .collect(Collectors.toList());
        List<UserMealWithExcess> result;
        result = auxUserMealList.stream().map(meal -> new UserMealWithExcess(meal.getDateTime(),meal.getDescription(),
                meal.getCalories(), false))
                .collect(Collectors.toList());
        return result;
    }
}
