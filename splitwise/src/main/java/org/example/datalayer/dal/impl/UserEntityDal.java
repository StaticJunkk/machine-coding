package org.example.datalayer.dal.impl;

import org.example.datalayer.dal.ifaces.IUserEntityDal;
import org.example.datalayer.dao.UserDao;
import org.example.datalayer.model.UserEntity;

import java.util.List;

public class UserEntityDal implements IUserEntityDal {
    private UserDao userDao = new UserDao();
    @Override
    public UserEntity getUserEntity(String id) {
        return userDao.getUser(id);
    }

    @Override
    public void addUserEntity(UserEntity entity) {
        userDao.addUser(entity);
    }

    @Override
    public List<UserEntity> listAll() {
        return userDao.getAll();
    }
}
