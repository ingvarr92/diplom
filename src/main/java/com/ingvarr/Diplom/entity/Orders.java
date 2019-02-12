package com.ingvarr.Diplom.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.EAN;

import javax.persistence.*;
import java.util.*;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "orders_dishes",
            joinColumns = { @JoinColumn(name = "orders_id") },
            inverseJoinColumns = { @JoinColumn(name = "dishes_id") })
    private List<Dishes> dishes = new ArrayList<>();
    //private Set<Dishes> dishes = new HashSet<>();

//    @ManyToMany(mappedBy = "orders")
//

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
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
