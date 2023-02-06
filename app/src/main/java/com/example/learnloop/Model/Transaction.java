package com.example.learnloop.Model;

public class Transaction {

    private int id;
    private int transactionPic;

    private String transactionFlow;
    private String Title;
    private String Amount;
    private String Purpose;
    private String desc;
    private String location;
    private String lon;
    private String lat;

    public Transaction(int id, int transactionPic, String transactionFlow, String title, String amount, String purpose, String desc, String location, String lon, String lat) {
        this.id = id;
        this.transactionPic = transactionPic;
        this.transactionFlow = transactionFlow;
        this.Title = title;
        this.Amount = amount;
        this.Purpose = purpose;
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
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
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
