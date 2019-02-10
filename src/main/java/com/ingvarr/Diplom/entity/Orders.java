package com.ingvarr.Diplom.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.EAN;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders_entity")
public class Orders {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id",length = 8,nullable = false)
    private Integer id;

    @Column(name = "tableTest", length = 3, nullable = false)
    private Integer table;

    @Column(name = "price", length = 6, nullable = false)
    private Integer price;

    @Column(name = "status", length = 20, nullable = false)
    private String status;


    @ManyToMany(mappedBy = "orders")
    private Set<Dishes> dishes = new HashSet<>();

    public Set<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dishes> dishes) {
        this.dishes = dishes;
    }

    public Orders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", table=" + table +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
