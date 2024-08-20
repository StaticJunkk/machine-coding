package org.example.datalayer.dal.ifaces;

import org.example.datalayer.model.UserEntity;

import java.util.List;

public interface IUserEntityDal {

    UserEntity getUserEntity(String id);
    void addUserEntity(UserEntity entity);

    List<UserEntity> listAll();
}
