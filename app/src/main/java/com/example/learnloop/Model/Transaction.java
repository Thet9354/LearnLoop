package com.example.learnloop.Model;

public class Transaction {

    private int id;
    private int transactionPic;

    private String transactionFlow;
    private String title;
    private String amount;
    private String purpose;
    private String desc;
    private String location;
    private String lon;
    private String lat;

    public Transaction(int id, int transactionPic, String transactionFlow, String title, String amount, String purpose, String desc, String location, String lon, String lat) {
        this.id = id;
        this.transactionPic = transactionPic;
        this.transactionFlow = transactionFlow;
        this.title = title;
        this.amount = amount;
        this.purpose = purpose;
        this.desc = desc;
        this.location = location;
        this.lon = lon;
        this.lat = lat;
    }

    public Transaction() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionPic() {
        return transactionPic;
    }

    public void setTransactionPic(int transactionPic) {
        this.transactionPic = transactionPic;
    }

    public String getTransactionFlow() {
        return transactionFlow;
    }

    public void setTransactionFlow(String transactionFlow) {
        this.transactionFlow = transactionFlow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }


}
