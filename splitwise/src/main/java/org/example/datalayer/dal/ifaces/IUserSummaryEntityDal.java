package org.example.datalayer.dal.ifaces;

import org.example.datalayer.model.UserSummaryEntity;

import java.util.List;

public interface IUserSummaryEntityDal {

    List<UserSummaryEntity> getSummaryForUser(String userId);

    void upsertSummary(UserSummaryEntity userSummaryEntity);
}
