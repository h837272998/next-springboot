package com.hjh.myspringboot.myspringboot.exception;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-17 15:27
 */
public class UserNotExistException extends RuntimeException {


    private long id;

    public UserNotExistException(long id){
        super("the user is not exist...");
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
