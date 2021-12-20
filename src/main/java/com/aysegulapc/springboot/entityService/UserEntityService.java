package com.aysegulapc.springboot.entityService;

import com.aysegulapc.springboot.dao.UserDao;
import com.aysegulapc.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return (List<User>) userDao.findAll();
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);

        User user = null;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    public User save(User user) {
        user = userDao.save(user);
        return user;
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    public User findAllByPhone(Long phone) {
        return userDao.findAllByPhone(phone);
    }

    public User findAllByUserName(String userName) {
        return userDao.findAllByUserName(userName);
    }

    public void deleteUserByUserNameAndPhone(String userName, Long phone) {
        userDao.deleteUserByUserNameAndPhone(userName, phone);
    }

}
