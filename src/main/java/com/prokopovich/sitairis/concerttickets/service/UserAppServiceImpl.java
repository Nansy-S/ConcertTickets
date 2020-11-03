package com.prokopovich.sitairis.concerttickets.service;

import com.prokopovich.sitairis.concerttickets.entity.User;
import com.prokopovich.sitairis.concerttickets.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserAppServiceImpl implements UserAppService {
    private final UserAppRepository userRepository;

    @Autowired
    public UserAppServiceImpl(UserAppRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(Sort sort) {
        return userRepository.findAll(sort);
    }

    public void addNewUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public void editUser(User user){
        userRepository.save(user);
    }

    public Optional<User> getByUserId(int id) {
        return userRepository.findAllByUserId(id);
    }

    public List<User> getByUserType(String type){
        return userRepository.findAllByUserType(type);
    }

    public User getByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return user;
    }
}
