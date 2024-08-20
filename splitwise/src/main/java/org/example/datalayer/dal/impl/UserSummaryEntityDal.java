package org.example.datalayer.dal.impl;

import org.example.datalayer.dal.ifaces.IExpenseItemEntityDal;
import org.example.datalayer.dal.ifaces.IUserSummaryEntityDal;
import org.example.datalayer.dao.ExpenseItemDao;
import org.example.datalayer.dao.UserSummaryDao;
import org.example.datalayer.model.ExpenseItemEntity;
import org.example.datalayer.model.UserSummaryEntity;

import java.util.ArrayList;
import java.util.List;

public class UserSummaryEntityDal implements IUserSummaryEntityDal {
    private UserSummaryDao dao = new UserSummaryDao();

    @Override
    public List<UserSummaryEntity> getSummaryForUser(String userId) {
        List<UserSummaryEntity> response;
        response =  dao.getUserSummary(userId);
        return response;
    }

    @Override
    public void upsertSummary(UserSummaryEntity userSummaryEntity) {
        dao.addSummary(userSummaryEntity);
    }
}
