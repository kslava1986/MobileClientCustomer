package com.example.pp.models;

public class Shop {
    private int id;
    private String name;
    private String tel;
    Shop() {
    }
    Shop(int id,String name,String tel){
        this.id=id;
        this.name=name;
        this.tel=tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }
}
