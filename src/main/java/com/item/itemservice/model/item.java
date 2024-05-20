package com.item.itemservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String img;
    @Column(name = "`desc`")
    private String desc;
    private String category;
    private BigDecimal price;
    private String sex;

    public item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }
}