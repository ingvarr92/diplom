package com.ingvarr.Diplom.controllers;



import com.ingvarr.Diplom.entity.Orders;
import com.ingvarr.Diplom.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @RequestMapping("/orders")
    public String getAllOrders(@ModelAttribute Orders orders, Model model){
        List<Orders> orderdishes = (List<Orders>) ordersRepository.findAll();//(List<Dishes>) ordersRepository.findAll();
        model.addAttribute("orders",orderdishes);
        return "orders";
    }

    //@RequestMapping(value = "orders/create", method = RequestMethod.GET)




}
