package com.olingasoftware.projectapp;

public class TransHolder {
    private String accName;
    private String depositAmount;
    private String withdrawlAmount;
    private String accPhone;
    private String time;

    public TransHolder() {
    }

    public TransHolder(String accName, String depositAmount, String withdrawlAmount, String accPhone, String time) {
        this.accName = accName;
        this.depositAmount = depositAmount;
        this.withdrawlAmount = withdrawlAmount;
        this.accPhone = accPhone;
        this.time = time;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getWithdrawlAmount() {
        return withdrawlAmount;
    }

    public void setWithdrawlAmount(String withdrawlAmount) {
        this.withdrawlAmount = withdrawlAmount;
    }

    public String getAccPhone() {
        return accPhone;
    }

    public void setAccPhone(String accPhone) {
        this.accPhone = accPhone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
