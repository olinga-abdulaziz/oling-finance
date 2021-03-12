package com.olingasoftware.projectapp;

public class Members {
    private String Names;
    private String IdNumber;
    private String Phone;
    private String GroupId;
    private String Email;
    private String AccounntNo;
    private String GroupName;
    private String Admin;

    public Members() {
    }

    public Members(String names, String idNumber, String phone, String groupId, String email, String accounntNo, String groupName, String admin) {
        Names = names;
        IdNumber = idNumber;
        Phone = phone;
        GroupId = groupId;
        Email = email;
        AccounntNo = accounntNo;
        GroupName = groupName;
        Admin = admin;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        Names = names;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAccounntNo() {
        return AccounntNo;
    }

    public void setAccounntNo(String accounntNo) {
        AccounntNo = accounntNo;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }
}
