package com.ingvarr.Diplom.controllers;

import com.ingvarr.Diplom.entity.Dishes;
import com.ingvarr.Diplom.entity.Orders;
import com.ingvarr.Diplom.repositories.DishesRepository;
import com.ingvarr.Diplom.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DishesController {
    @Autowired
    protected DishesRepository dishesRepository;

    @Autowired
    protected OrdersRepository ordersRepository;


    @RequestMapping(name = "/")
    public String index(@ModelAttribute Dishes dish, Model model,@RequestParam(value = "orderId", required = true)Integer orderId) {
        try{
        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
        model.addAttribute("dishes", dishes);
        model.addAttribute("orderId",orderId);
        model.addAttribute("totalPrice",ordersRepository.findById(orderId).get().getPrice());


        List<Dishes> ordereddishes = (List<Dishes>) ordersRepository.findById(orderId).get().getDishes();//(List<Dishes>) ordersRepository.findAll();
        model.addAttribute("orderdishes", ordereddishes);

        return "index.html";}
        catch (Exception e){
            System.out.println(e);
            return "404";
        }
    }


    @RequestMapping("/add_tbl")
    public String getCoursesByTittle(@RequestParam(value = "tbl", required = true)Integer tbl){
        Orders order = new Orders();
        order.setTable(tbl);
        order.setPrice(0);
        order.setStatus("Открыт");
        order.setTime(LocalDateTime.now());
        ordersRepository.save(order);
        Integer orderId = order.getId();

        return "redirect:/index?orderId=" +orderId;
    }

    @RequestMapping("/ordered")
    public String ordered(@ModelAttribute Dishes dish, Model model,@RequestParam(value = "orderId", required = true)Integer orderId){
        Orders order = ordersRepository.findById(orderId).get();
        order.setStatus("Заказано");
        List<Dishes> ordereddishes = (List<Dishes>) order.getDishes();
        model.addAttribute("orderdishes", ordereddishes);
        model.addAttribute("orderId",orderId);
        model.addAttribute("totalPrice",ordersRepository.findById(orderId).get().getPrice());
        ordersRepository.save(order);
        return "ordered";
    }

    @RequestMapping("/pay")
    public String pay(@RequestParam(value = "orderId", required = true)Integer orderId){
        Orders order = ordersRepository.findById(orderId).get();
        order.setStatus("Оплачено");
        ordersRepository.save(order);
        return "pay";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin.html";
    }



    @RequestMapping(value = "/dish/create",method = RequestMethod.GET)
    public String showForm(@ModelAttribute Dishes dish, Model model){
       try{ model.addAttribute("dishes",new Dishes());
        return "add_dish";} // .html?????
           catch(Exception e){
               System.out.println(e);
               return "error";
           }
    }
    @RequestMapping("/del_dish_list/{id}")
    public String addProductToCart(@PathVariable("id") Integer dishId) {
        dishesRepository.deleteById(dishId);
        return "redirect:/dishes";
    }

    @RequestMapping(value = "/dish/create",method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Dishes dish, Model model){
        dishesRepository.save(dish);


        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
        model.addAttribute("dishes", dishes);
        return "dish_list.html";
    }

    @RequestMapping("/dishes")
    public String dishesList(@ModelAttribute Dishes dish, Model model){
        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
        model.addAttribute("dishes", dishes);
        return "dish_list.html";
    }

    @RequestMapping("/add_dish/{id}")
    public String addProductToCart(@PathVariable("id") Integer dishId,@RequestParam(value = "orderId", required = true)Integer orderId) {
        Orders order =ordersRepository.findById(orderId).get();
        order.setPrice(dishesRepository.findById(dishId).get().getPrice() + order.getPrice());
        order.setStatus("Создан");
        order.getDishes().add(dishesRepository.findById(dishId).get());
        ordersRepository.save(order);

        return "redirect:/index?orderId="+orderId;
    }

    @RequestMapping("/del_dish/{id}")
    public String delProductFromCart(@PathVariable("id") Integer dishId,@RequestParam(value = "orderId", required = true)Integer orderId) {
        Orders order =ordersRepository.findById(orderId).get();
        order.setPrice(order.getPrice() - dishesRepository.findById(dishId).get().getPrice());
        order.setStatus("Обновлен");
        order.getDishes().remove(dishesRepository.findById(dishId).get());
        ordersRepository.save(order);
        return "redirect:/index?orderId="+orderId;
    }
}