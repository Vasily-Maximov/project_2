package ru.yandex.practicum.filmorate.repository.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class InMemoryFriendRepository {

    private final UserRepository userRepository;

    @Autowired
    public InMemoryFriendRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addToFriends(User user, User friend) {
        user.getFriends().add(friend.getId());
        friend.getFriends().add(user.getId());
    }

    public void delToFriends(User user, User friend) {
        user.getFriends().remove(friend.getId());
        friend.getFriends().remove(user.getId());
    }

    public Collection<User> getFriendsByUser(User user) {
        return user.getFriends().stream().map(userRepository::findById).collect(Collectors.toList());
    }

    public Collection<User> getOtherFriendsById(User user, User otherUser) {
        return getFriendsByUser(user).stream().filter(getFriendsByUser(otherUser)::contains).collect(Collectors.toList());
    }
}