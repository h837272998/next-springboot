package com.hjh.myspringboot.myspringboot.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hjh.myspringboot.myspringboot.entity.User;
import com.hjh.myspringboot.myspringboot.entity.View;
import com.hjh.myspringboot.myspringboot.exception.UserNotExistException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-16 21:32
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {



    @PostMapping
    public User add(@Valid @RequestBody User user){
        log.info("增加用户；"+user.toString());
        user.setId(1);
        return user;
    }


    //正则匹配 id只能为数字
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable long id){
        log.info("删除用户id："+id);
    }

    @PutMapping("/{id:\\d+}")
    public User update(@RequestBody User user){
        log.info("修改用户："+user);
        return user;
    }

    @GetMapping("/{id:\\d+}")
    public User get(@PathVariable long id){
        User user = new User();
        user.setId(1);
        user.setUsername("hjh");
        user.setPassword("123");
        return user;
    }

    @GetMapping
    @JsonView(View.Summary.class)
    public List<User> list(User user){
        log.info("查询用户名：" + user);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @PutMapping("/test/{id\\id+}")
    public User validateTest(@Valid @RequestBody User user, BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(
                    error->{
                        FieldError fieldError = (FieldError) error;
                        String message = fieldError.getField()+error.getDefaultMessage();
                        log.info(message);
                    }
            );
        }

        log.info("修改用户："+user);
        return user;
    }

}
