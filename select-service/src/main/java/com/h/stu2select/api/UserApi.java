package com.h.stu2select.api;


import com.h.stu2select.entity.User;
import com.h.stu2select.sevice.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RequestMapping("api/user")
// RestController 返回任意類型結果（怎麼是繁體還改不回來啊啊啊啊
@RestController
public class UserApi {
    @Resource
    UserService userService;

    @DeleteMapping("{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        return userService.deleteUserById(id) ? "删除成功" : "删除失败";
    }

    @PostMapping("login")
    public String isCorrectLogin(@RequestBody User user) {
        return userService.isCorrectLogin(user).toString();
    }

    @PostMapping
    public String saveUser(@RequestBody User user) {
        userService.setUser(user);
        return "插入成功";
    }

    // 從路徑獲取參數值
    @GetMapping("{page}/{size}")
    public Page<User> getAllUser(@PathVariable("page") int page,
                                 @PathVariable("size") int size) {
        // PageRequest spring自帶分頁,page從0開始
        return userService.getAllUser(PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
}
