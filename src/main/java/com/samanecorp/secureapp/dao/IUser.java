package com.samanecorp.secureapp.dao;

import com.samanecorp.secureapp.entities.UserEntity;

import java.util.List;

public interface IUser {
    public List<UserEntity> liste();
    public boolean addUser(UserEntity user);
    public boolean updateUser(UserEntity user);
    public boolean deleteUser(long id);
}
