package com.sripar.bankingnotifications;

/**
 * Created by ss on 2/28/16.
 */
public class BankNotification {
    private int id;
    private String title;
    private String description;

    public BankNotification() {
        this(0, "", "");
    }

    public BankNotification(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return Integer.toString(id) + " | " + title + " | " + description;
    }
}
