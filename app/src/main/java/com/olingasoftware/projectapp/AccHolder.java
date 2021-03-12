package com.olingasoftware.projectapp;

public class AccHolder {
    private String AccName;
    private String AccPhone;
    private String AccGroupId;
    private String DepositAmount;
    private String WithdrawalAmount;
    private String time;

    public AccHolder() {
    }

    public AccHolder(String accName, String accPhone, String accGroupId, String depositAmount, String withdrawalAmount, String time) {
        AccName = accName;
        AccPhone = accPhone;
        AccGroupId = accGroupId;
        DepositAmount = depositAmount;
        WithdrawalAmount = withdrawalAmount;
        this.time = time;

    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String accName) {
        AccName = accName;
    }

    public String getAccPhone() {
        return AccPhone;
    }

    public void setAccPhone(String accPhone) {
        AccPhone = accPhone;
    }

    public String getAccGroupId() {
        return AccGroupId;
    }

    public void setAccGroupId(String accGroupId) {
        AccGroupId = accGroupId;
    }

    public String getDepositAmount() {
        return DepositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        DepositAmount = depositAmount;
    }

    public String getWithdrawalAmount() {
        return WithdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        WithdrawalAmount = withdrawalAmount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
