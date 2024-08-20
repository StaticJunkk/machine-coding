package org.example.service.impl;

import org.example.datalayer.dal.ifaces.IExpenseEntityDal;
import org.example.datalayer.dal.ifaces.IExpenseItemEntityDal;
import org.example.datalayer.dal.ifaces.IUserEntityDal;
import org.example.datalayer.dal.ifaces.IUserSummaryEntityDal;
import org.example.datalayer.dal.impl.ExpenseEntityDal;
import org.example.datalayer.dal.impl.ExpenseItemEntityDal;
import org.example.datalayer.dal.impl.UserEntityDal;
import org.example.datalayer.dal.impl.UserSummaryEntityDal;
import org.example.datalayer.model.ExpenseEntity;
import org.example.datalayer.model.ExpenseItemEntity;
import org.example.datalayer.model.UserEntity;
import org.example.datalayer.model.UserSummaryEntity;
import org.example.service.ifaces.IExpenseService;
import org.example.service.ifaces.ISplitService;
import org.example.service.model.BalanceDTO;
import org.example.service.model.ExpenseDTO;
import org.example.service.model.SplitType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExpenseService implements IExpenseService {
    private final IExpenseEntityDal expenseEntityDal;
    private final IUserEntityDal userEntityDal;
    private final IUserSummaryEntityDal userSummaryEntityDal;
    private final IExpenseItemEntityDal expenseItemEntityDal;
    private final HashMap<SplitType, ISplitService> splitServiceMap;

    public ExpenseService() {
        this.expenseEntityDal = new ExpenseEntityDal();
        this.userEntityDal = new UserEntityDal();
        this.userSummaryEntityDal = new UserSummaryEntityDal();
        this.expenseItemEntityDal = new ExpenseItemEntityDal();
        this.splitServiceMap = new HashMap<>();
        this.splitServiceMap.put(SplitType.EQUAL, new EqualSplitService());
        this.splitServiceMap.put(SplitType.EXACT, new ExactSplitService());
        this.splitServiceMap.put(SplitType.PERCENTAGE, new PercentageSplitService());
    }

    @Override
    public void addExpense(ExpenseDTO expenseDTO) {
        expenseDTO.setSplitBetween(splitServiceMap.get(expenseDTO.getSplitType())
                .split(expenseDTO.getSplitBetween(), expenseDTO.getAmount()));
        ExpenseEntity expenseEntity = getExpenseEntity(expenseDTO);
        expenseEntityDal.addExpense(expenseEntity);
        expenseDTO.getSplitBetween()
                .forEach((user, amount) -> expenseItemEntityDal.addExpense(getExpenseItemEntity(user,
                        amount,
                        expenseEntity.getId())));
        expenseDTO.getSplitBetween().forEach((user, amount) -> userSummaryEntityDal.upsertSummary(getUserSummaryEntity(
                expenseDTO.getPaidBy(), user, amount)));
        expenseDTO.getSplitBetween().forEach((user, amount) -> userSummaryEntityDal.upsertSummary(getUserSummaryEntity(
                user, expenseDTO.getPaidBy(), -1 * amount)));
    }

    @Override
    public List<String> getBalance(String userId) {
        UserEntity userEntity = userEntityDal.getUserEntity(userId);
        List<UserSummaryEntity> userSummaryEntity = userSummaryEntityDal.getSummaryForUser(userId);
        return getBalance(userEntity, userSummaryEntity);
    }

    @Override
    public List<String> getBalance() {
        List<String> response = new ArrayList<>();
        List<UserEntity> userEntity = userEntityDal.listAll();
        userEntity.stream()
                .forEach(item -> response.addAll(getBalance(item.getUserId())));
        return response;
    }

    @Override
    public void addUser(UserEntity userEntity) {
        userEntityDal.addUserEntity(userEntity);
    }

    private ExpenseEntity getExpenseEntity(ExpenseDTO expenseDTO) {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setAddedBy(expenseDTO.getAddedBy());
        expenseEntity.setId(expenseDTO.getId());
        expenseEntity.setAmount(expenseDTO.getAmount());
        expenseEntity.setPaidBy(expenseDTO.getPaidBy());
        expenseEntity.setName(expenseDTO.getDesc());
        return expenseEntity;
    }

    private ExpenseItemEntity getExpenseItemEntity(String userId, Double amount, String expenseId) {
        ExpenseItemEntity expenseEntity = new ExpenseItemEntity();
        expenseEntity.setId(String.valueOf(UUID.randomUUID()));
        expenseEntity.setExpenseId(expenseId);
        expenseEntity.setAmount(amount);
        expenseEntity.setOwedBy(userId);
        return expenseEntity;
    }

    private UserSummaryEntity getUserSummaryEntity(String paidBy, String owedBy, Double amount) {
        UserSummaryEntity userSummaryEntity = new UserSummaryEntity();
        userSummaryEntity.setId(String.valueOf(UUID.randomUUID()));
        userSummaryEntity.setAmount(amount);
        userSummaryEntity.setUserId(paidBy);
        userSummaryEntity.setTransactedWith(owedBy);
        return userSummaryEntity;
    }

    private List<String> getBalance(UserEntity user, List<UserSummaryEntity> userSummaryEntities) {
        List<String> balanceDTOs = userSummaryEntities.stream()
                .map(item -> new BalanceDTO(user.getName(),
                        item.getAmount(),
                        userEntityDal.getUserEntity(item.getTransactedWith()).getName()).getBalanceString())
                .collect(Collectors.toList());
        return balanceDTOs;
    }
}
