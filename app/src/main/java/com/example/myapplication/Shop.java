package com.example.myapplication;

/**
 * Created by 蒋星 on 2017/10/10.
 * 新建Food类，用以保存一条食物项的数据
 */

public class Shop {
    private String shopname;  //食物名称
    private Double shopprice,shopgrade;  //食物价格、评分
    private int shopicon;  //食物图片
    public Shop(){
    }
    public Shop(String shopname, Double shopprice, Double shopgrade, int shopicon){
        this.shopname=shopname;
        this.shopprice=shopprice;
        this.shopgrade=shopgrade;
        this.shopicon=shopicon;
    }
    public String getShopname(){
        return shopname;
    }
    public Double getShopprice(){
        return shopprice;
    }
    public Double getShopgrade(){
        return shopgrade;
    }
    public int getShopicon(){
        return shopicon;
    }
}
