package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserAppService extends UserDetailsService {
    List<User> getAllUser(Sort sort);
    void addNewUser(User user);
    void deleteUser(User user);
    void editUser(User user);
    Optional<User> getByUserId(int id);
    List<User> getByUserType(String type);
    User getByLogin(String login);
}
