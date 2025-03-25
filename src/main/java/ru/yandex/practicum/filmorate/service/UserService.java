package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.imp.InMemoryFriendRepository;
import ru.yandex.practicum.filmorate.repository.imp.InMemoryUserRepository;
import java.util.Collection;

@Service
public class UserService extends AbstractService<User> {

    private final InMemoryFriendRepository friendRepository;

    @Autowired
    public UserService(InMemoryUserRepository userRepository, InMemoryFriendRepository friendRepository) {
        super(userRepository);
        this.friendRepository = friendRepository;
    }

    public void addToFriends(int idUser, int idFriend) {
        User user = super.findById(idUser);
        User friend = super.findById(idFriend);
        friendRepository.addToFriends(user, friend);
    }

    public void delToFriends(int idUser, int idFriend) {
        User user = super.findById(idUser);
        User friend = super.findById(idFriend);
        friendRepository.delToFriends(user, friend);
    }

    public Collection<User> getFriendsByUser(int idUser) {
        User user = super.findById(idUser);
        return friendRepository.getFriendsByUser(user);
    }

    public Collection<User> getOtherFriendsById(Integer userId, Integer otherId) {
        User user = super.findById(userId);
        User otherUser = super.findById(otherId);
        return friendRepository.getOtherFriendsById(user, otherUser);
    }
}