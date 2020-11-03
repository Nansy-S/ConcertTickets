package com.prokopovich.sitairis.concerttickets.repository;

import com.prokopovich.sitairis.concerttickets.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAppRepository extends CrudRepository<User, Long> {
    List<User> findAll(Sort sort);
    Optional<User> findAllByUserId(int id);
    List<User> findAllByUserType(String type);
    User findByLogin(String login);
}