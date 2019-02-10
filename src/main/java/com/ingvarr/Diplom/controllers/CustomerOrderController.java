package com.ingvarr.Diplom.controllers;


import com.ingvarr.Diplom.entity.Dishes;
import com.ingvarr.Diplom.entity.Orders;
import com.ingvarr.Diplom.repositories.DishesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerOrderController {


//    @GetMapping("/index")
//    public ModelAndView shoppingCart() {
//        ModelAndView modelAndView = new ModelAndView("/index");
//        return modelAndView;
//    }



    @RequestMapping("/add_dish/{id}")
    public String addProductToCart(@PathVariable("id") Integer dishId, Model model) {
        Orders order = new Orders();
        order.getDishes().add(DishesController.dishesRepository.findById(dishId).get());
        DishesController.dishesRepository.findById(dishId);
        OrdersController.ordersRepository.save(order);

        List<Dishes> dishes = (List<Dishes>) DishesController.dishesRepository.findAll();
        model.addAttribute("dishes", dishes);

        return "index.html";
    }

//    @GetMapping("/shoppingCart/addProduct/{productId}")
//    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
//        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
//        return shoppingCart();
}
