package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController extends AbstractController<User> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addToFriends(@PathVariable("id") Integer idUser, @PathVariable("friendId") Integer idFriend) {
        log.info("Получен запрос на добавление в друзья к пользователю с idUser = {}, пользователя с idFriend = {}", idUser,
                idFriend);
        userService.addToFriends(idUser, idFriend);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void delToFriends(@PathVariable("id") Integer idUser, @PathVariable("friendId") Integer idFriend) {
        log.info("Получен запрос на взаимное удаление из друзей пользователя с idUser = {}, пользователя с idFriend = {}", idUser,
                idFriend);
        userService.delToFriends(idUser, idFriend);
    }

    @GetMapping("/{id}/friends")
    public Collection<User> getFriendsByUser(@PathVariable("id") Integer idUser) {
        log.info("Получен запрос на возврат списка друзей пользователя с id = {}", idUser);
        return userService.getFriendsByUser(idUser);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public Collection<User> getOtherFriendsById(@PathVariable("id") Integer idUser, @PathVariable Integer otherId) {
        log.info("Получен запрос на возврат списка общих друзей пользователей с id = {} и otherId = {}", idUser, otherId);
        return userService.getOtherFriendsById(idUser, otherId);
    }
}