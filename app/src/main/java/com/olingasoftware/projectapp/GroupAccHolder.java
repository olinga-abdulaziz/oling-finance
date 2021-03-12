package com.olingasoftware.projectapp;

public class GroupAccHolder {
    private Integer AmountDeposit;
    private Integer AmountWithdraw;
    private String sourceIncome;
    private String time;
    private String userAdmin;

    public GroupAccHolder() {
    }

    public GroupAccHolder(Integer amountDeposit, Integer amountWithdraw, String sourceIncome, String time, String userAdmin) {
        AmountDeposit = amountDeposit;
        AmountWithdraw = amountWithdraw;
        this.sourceIncome = sourceIncome;
        this.time = time;
        this.userAdmin = userAdmin;
    }

    public Integer getAmountDeposit() {
        return AmountDeposit;
    }

    public void setAmountDeposit(Integer amountDeposit) {
        AmountDeposit = amountDeposit;
    }

    public Integer getAmountWithdraw() {
        return AmountWithdraw;
    }

    public void setAmountWithdraw(Integer amountWithdraw) {
        AmountWithdraw = amountWithdraw;
    }

    public String getSourceIncome() {
        return sourceIncome;
    }

    public void setSourceIncome(String sourceIncome) {
        this.sourceIncome = sourceIncome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }
}
