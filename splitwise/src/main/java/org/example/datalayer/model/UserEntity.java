package org.example.datalayer.model;

public class UserEntity {
    private String id;
    private String name;
    private String emailId;
    private String phoneNumber;

    public UserEntity(String id, String name, String emailId, String phoneNumber) {
        this.emailId = emailId;
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return this.id;
    }

    public void setUserId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}
