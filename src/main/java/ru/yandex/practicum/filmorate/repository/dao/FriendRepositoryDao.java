package ru.yandex.practicum.filmorate.repository.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class FriendRepositoryDao {

    private final JdbcTemplate jdbcTemplate;

    private final UserRepositoryDao userRepositoryDao;

    public void addToFriends(User user, User friend) {
        String sqlQuery = "INSERT INTO friend(person_one_id, person_two_id, as_friend) " +
                "VALUES(?, ?, ?)";
        jdbcTemplate.update(sqlQuery, user.getId(), friend.getId(), true);
        jdbcTemplate.update(sqlQuery, friend.getId(), user.getId(), true);
    }

    public void delToFriends(User user, User friend) {
        String sqlQuery = "DELETE FROM friend WHERE person_one_id = ? AND person_two_id = ?";
        jdbcTemplate.update(sqlQuery, user.getId(), friend.getId());
        jdbcTemplate.update(sqlQuery, friend.getId(), user.getId());
    }

    public Collection<User> getFriendsByUser(User user) {
        String sqlQuery = "SELECT person_two_id AS person_id " +
                "FROM friend " +
                "WHERE person_one_id = ? AND as_friend = ?";
        return jdbcTemplate.query(sqlQuery, this::mapRow, user.getId(), true);
    }

    public Collection<User> getOtherFriendsById(User user, User otherUser) {
        Collection<User> userFriends = getFriendsByUser(user);
        Collection<User> otherUserFriends = getFriendsByUser(otherUser);
        return userFriends.stream().filter(otherUserFriends::contains).collect(Collectors.toList());

        /*return getFriendsByUser(user).stream().filter(FriendRepositoryDao.this.getFriendsByUser(otherUser)::contains)
                .collect(Collectors.toList());*/
    }

    private User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return userRepositoryDao.findById(rs.getInt("person_id"));
    }
}