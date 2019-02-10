package com.ingvarr.Diplom.controllers;


import com.ingvarr.Diplom.entity.Orders;
import com.ingvarr.Diplom.repositories.DishesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerOrderController {


    @RequestMapping("/add_dish/{id}")
    public String addProductToCart(@PathVariable("id") Integer dishId) {
        Orders order = new Orders();
//        order.getDishes().add(DishesController.dishesRepository.findById(dishId).get());
//        DishesController.dishesRepository.findById(dishId);
//        OrdersController.ordersRepository.save(order);

        return "index.html";
    }
}
