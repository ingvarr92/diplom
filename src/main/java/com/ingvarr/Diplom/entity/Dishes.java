package com.ingvarr.Diplom.entity;


import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dish_entity")
public class Dishes {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id",length = 8,nullable = false)
    private Integer id;

    @Column(name = "title",length = 55, nullable = false)
    private String title;

    @Column(name = "description",length = 200, nullable = false)
    private String description;


    @Column(name = "available",nullable = true)
    private Boolean available;

    @Column(name = "price",length = 9,nullable = false)
    private Integer price;

    @ManyToMany
    @JoinTable(name = "Orders_Dishes",
            joinColumns = {@JoinColumn(name = "dishes_id")},
            inverseJoinColumns = {@JoinColumn(name = "orders_id")}
    )
    Set<Orders> orders = new HashSet<>();

    public Dishes() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
