package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class UsersUtil {
    public static final List<User> users = Arrays.asList(
            new User(1, "Ivan Petrov", "user@yandex.ru", "password", Role.USER ),
            new User(2, "Adam Sidorov", "admin@gmail.com", "admin", Role.ADMIN )
    );

    public static User getByEmail(List<User> users, String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
