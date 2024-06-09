package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.AbstractRepository;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService<User> {

    @Autowired
    public UserService(AbstractRepository<User> abstractRepository) {
        super(abstractRepository);
    }

    public void addToFriends(int idUser, int idFriend) {
        User user = super.findById(idUser);
        User friend = super.findById(idFriend);
        user.getFriends().add(idFriend);
        friend.getFriends().add(idUser);
    }

    public void delToFriends(int idUser, int idFriend) {
        User user = super.findById(idUser);
        User friend = super.findById(idFriend);
        user.getFriends().remove(idFriend);
        friend.getFriends().remove(idUser);
    }

    public Collection<User> getFriendsByUser(int idUser) {
        return super.findById(idUser).getFriends().stream().map(super::findById).collect(Collectors.toList());
    }

    public Collection<User> getOtherFriendsById(Integer userId, Integer otherId) {
        return getFriendsByUser(userId).stream().filter(getFriendsByUser(otherId)::contains).collect(Collectors.toList());
    }
}