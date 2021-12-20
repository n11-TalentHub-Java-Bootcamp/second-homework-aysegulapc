package com.aysegulapc.springboot.dao;

import com.aysegulapc.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Transactional
    User findAllByPhone(Long phone);

    User findAllByUserName(String userName);

    @Transactional
    void deleteUserByUserNameAndPhone(String userName, Long phone);

}
