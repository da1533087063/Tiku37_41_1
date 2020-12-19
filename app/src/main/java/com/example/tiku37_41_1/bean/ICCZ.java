package com.example.tiku37_41_1.bean;

public class ICCZ {
   /* "id": 1,
            "number": 1,
            "owner": "张三",
            "balance": 57346,
            "plate": "鲁A10001",
            "brand": "奔驰",
            "user": "user1"*/
   private int id,number;
   private String owner;
   private int balance;
   private String plate,brand;

    public ICCZ(int id, int number, String owner, int balance, String plate, String brand) {
        this.id = id;
        this.number = number;
        this.owner = owner;
        this.balance = balance;
        this.plate = plate;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
