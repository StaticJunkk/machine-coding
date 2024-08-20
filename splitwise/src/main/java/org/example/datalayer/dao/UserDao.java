package org.example.datalayer.dao;


import org.example.datalayer.model.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
    private final HashMap<String, UserEntity> userEntityMap;

    public UserDao() {
        this.userEntityMap = new HashMap<>();
    }

    public void addUser(UserEntity userEntity) {
        this.userEntityMap.put(userEntity.getUserId(), userEntity);
    }

    public UserEntity getUser(String id) {
        return this.userEntityMap.get(id);
    }

    public List<UserEntity> getAll() {

        return (List<UserEntity>) new ArrayList<>(this.userEntityMap.values());
    }

}
