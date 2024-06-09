package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exeption.ObjectValidationException;
import ru.yandex.practicum.filmorate.validation.CreateGroup;
import ru.yandex.practicum.filmorate.validation.UpdateGroup;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=true)
@Slf4j
public class User extends AbstractModel {

    @Email(groups = {CreateGroup.class, UpdateGroup.class})
    @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
    private String email;
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private String login;
    @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
    private String name;
    @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
    @PastOrPresent(groups = {CreateGroup.class, UpdateGroup.class})
    private LocalDate birthday;
    private Set<Integer> friends;

    public User(String email, String login, String name, LocalDate birthday) {
        checkLogin(login);
        this.email = email;
        this.login = login.trim();
        this.name = checkName(name);
        this.birthday = birthday;
        this.friends = new HashSet<>();
    }

    public String checkName(String name) {
        if (name == null || name.isBlank()) {
            return this.login;
        } else {
            return name;
        }
    }

    public void checkLogin(String login) {
        if (login.trim().contains(" ")) {
            String messageError = String.format("Логин содержит пробелы %s", login);
            log.error(messageError);
            throw new ObjectValidationException(messageError);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", friends=" + friends +
                '}';
    }
}