package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exeption.ObjectValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserApplicationTests {
    private static Validator validator;

    @BeforeEach
    public void setValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void postUserTest() {
        User user = new User("kt-62a@mail.ru","kt-62a", "Vasiliy", LocalDate.of(2023,8,
                5));
        Set<ConstraintViolation<User>> violations = validator.validate(user, CreateGroup.class);
        assertTrue(violations.isEmpty(), getMessageError(violations));

        user = new User("kt-62a@mail.ru","kt-62a", "Vasiliy", LocalDate.of(1984,8, 5));
        user.setId(1);
        violations = validator.validate(user, CreateGroup.class);
        assertFalse(violations.isEmpty(), "Ошибка при создании пользователя, 'id' при создании должен быть пустым");

        user = new User("kt-62a@mail.ru","kt-62a", "Vasiliy", LocalDate.of(1984,8, 5));
        violations = validator.validate(user, UpdateGroup.class);
        assertFalse(violations.isEmpty(), "Ошибка при изменении пользователя, 'id' при изменении не должен быть" +
                "пустым");

        user = new User("kt-62amail.ru","kt-62a", "Vasiliy", LocalDate.of(1984,8, 5));
        user.setId(1);
        violations = validator.validate(user, CreateGroup.class);
        assertFalse(violations.isEmpty(), "Ошибка при создании пользователя, 'email' указан неправильно");

        user = new User("kt-62a@mail.ru","  ", "Vasiliy", LocalDate.of(1984,8, 5));
        violations = validator.validate(user, CreateGroup.class);
        assertFalse(violations.isEmpty(), "Ошибка при создании пользователя, 'login' указан неправильно");

        user = new User("kt-62a@mail.ru","kt-62a", "", LocalDate.of(1984,8, 5));
        assertEquals(user.getLogin(), user.getName(), "Ошибка при создании пользователя, 'name' указан неправильно");

        ObjectValidationException exception = assertThrows(
                ObjectValidationException.class,
                () -> {
                    new User("kt-62a@mail.ru","kt62a", "Vasiliy", LocalDate.of(1984,8,
                            5));
                    throw new ObjectValidationException("ок");
                });
        assertEquals("ок", exception.getMessage(), "Ошибка при создании пользователя, 'login' содержит пробел");
    }

    private String getMessageError(Set<ConstraintViolation<User>> violations) {
        return violations.stream().findFirst().map(constraintViolation -> constraintViolation.getPropertyPath() + " " +
                constraintViolation.getMessage()).orElse("ok");
    }
}