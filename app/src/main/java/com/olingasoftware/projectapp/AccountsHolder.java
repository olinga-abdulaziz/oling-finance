package com.olingasoftware.projectapp;

public class AccountsHolder {
    private String names;
    private String phone;

    public AccountsHolder() {
    }

    public AccountsHolder(String names, String phone) {
        this.names = names;
        this.phone = phone;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
