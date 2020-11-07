package com.h.stu2select.sevice;

import com.h.stu2select.dao.UserDao;
import com.h.stu2select.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    UserDao userDao;

    public Page<User> getAllUser(PageRequest pageRequest) {
        return userDao.findAll(pageRequest);
    }

    public void setUser(User user) {
        userDao.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userDao.findById(id);
    }

    public boolean deleteUserById(Integer id) {
        if (!userDao.existsById(id)) return false;
        else {
            userDao.deleteById(id);
            return true;
        }
    }

    public Integer isCorrectLogin(User user) {
        if (userDao.exists(Example.of(user))){
            Optional<User> user1 = userDao.findById(user.getId());
            if (user1.isPresent())
                return user1.get().getUser_id();
        }
        return -1;
    }
}
