package com.post.demo.response;

import com.post.demo.model.User;

import java.util.List;

public class UserResponse extends BaseResponse{
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
