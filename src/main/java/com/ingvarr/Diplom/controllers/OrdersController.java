package com.ingvarr.Diplom.controllers;

import com.ingvarr.Diplom.entity.Orders;
import com.ingvarr.Diplom.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @RequestMapping("/orders")
    public Iterable<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    //@RequestMapping(value = "orders/create", method = RequestMethod.GET)




}
