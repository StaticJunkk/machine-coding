package org.example.datalayer.dao;


import org.example.datalayer.model.UserSummaryEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserSummaryDao {
    private final HashMap<String, UserSummaryEntity> userSummaryEntityMap;
    private final HashMap<String, UserSummaryEntity> userSummaryEntityMapOnUserAndTxnId;
    private final HashMap<String, List<UserSummaryEntity>> userSummaryEntityMapOnUserId;

    public UserSummaryDao() {

        this.userSummaryEntityMap = new HashMap<>();
        this.userSummaryEntityMapOnUserAndTxnId = new HashMap<>();
        this.userSummaryEntityMapOnUserId = new HashMap<>();
    }

    public void addSummary(UserSummaryEntity userSummaryEntity) {
        if (!Objects.equals(userSummaryEntity.getUserId(), userSummaryEntity.getTransactedWith())) {
            UserSummaryEntity existingSummary = this.userSummaryEntityMapOnUserAndTxnId.get(userSummaryEntity.getUserId()+"_"+userSummaryEntity.getTransactedWith());
            if (!Objects.isNull(existingSummary)){
                userSummaryEntity.setAmount(userSummaryEntity.getAmount()+existingSummary.getAmount());
            }
            this.userSummaryEntityMap.put(userSummaryEntity.getId(), userSummaryEntity);
            this.userSummaryEntityMapOnUserAndTxnId.put(userSummaryEntity.getUserId()+"_"+userSummaryEntity.getTransactedWith(), userSummaryEntity);
            List<UserSummaryEntity> userSummaryEntities = userSummaryEntityMapOnUserId.get(userSummaryEntity.getUserId());
            if (!Objects.isNull(userSummaryEntities)) {
                Optional<UserSummaryEntity> existingItem = userSummaryEntities.stream()
                        .filter(item -> item.getTransactedWith().equals(userSummaryEntity.getTransactedWith()))
                        .findFirst();
                if (existingItem.isPresent()) {
                    UserSummaryEntity itemToUpdate = existingItem.get();
                    itemToUpdate.setAmount(userSummaryEntity.getAmount());
                } else {
                    // If the item does not exist, add it to the list
                    List<UserSummaryEntity> newUserSummaryEntities =
                            new java.util.ArrayList<>(List.copyOf(userSummaryEntities));
                    newUserSummaryEntities.add(userSummaryEntity);
                    userSummaryEntityMapOnUserId.put(userSummaryEntity.getUserId(), newUserSummaryEntities);
                }
            } else {
                userSummaryEntityMapOnUserId.put(userSummaryEntity.getUserId(), List.of(userSummaryEntity));
            }
        }
    }

    public List<UserSummaryEntity> getUserSummary(String id) {
        return Objects.isNull(this.userSummaryEntityMapOnUserId.get(id))? Collections.EMPTY_LIST : this.userSummaryEntityMapOnUserId.get(id);
    }

}
