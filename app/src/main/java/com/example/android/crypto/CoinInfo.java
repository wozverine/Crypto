package com.example.android.crypto;

public class CoinInfo {
    private String icon;
    private String abb;
    private String desc;
    private String color;
    private String name;
    private String price;

    public CoinInfo(String icon,String abb,String desc,String color,String name,String price){
        this.icon=icon;
        this.abb=abb;
        this.desc=desc;
        this.color=color;
        this.name=name;
        this.price=price;
    }
    public CoinInfo(){
        this.abb="";
        this.icon="";
        this.desc="";
        this.color="";
        this.name="";
        this.price=";";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAbb() {
        return abb;
    }

    public void setAbb(String abb) {
        this.abb = abb;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
