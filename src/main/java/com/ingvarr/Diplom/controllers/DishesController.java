package com.ingvarr.Diplom.controllers;

import com.ingvarr.Diplom.entity.Dishes;
import com.ingvarr.Diplom.entity.Orders;
import com.ingvarr.Diplom.repositories.DishesRepository;
import com.ingvarr.Diplom.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DishesController {
    @Autowired
    protected DishesRepository dishesRepository;

    @Autowired
    protected OrdersRepository ordersRepository;


    @RequestMapping(name = "/")
    public String index(@ModelAttribute Dishes dish, Model model, Orders orders){
        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
        model.addAttribute("dishes", dishes);

        List<Orders> orderdishes = (List<Orders>) ordersRepository.findAll();//(List<Dishes>) ordersRepository.findAll();
        model.addAttribute("orderdishes",orderdishes);
        return "index.html";
    }

//    @RequestMapping(name = "/add_tbl/{tbl}")
//    public String setTbl(@PathVariable("tbl") Integer tbl, @ModelAttribute Dishes dish, Model model, Orders order){
//        order.setTable(tbl);
//        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
//        model.addAttribute("dishes", dishes);
//
//        List<Orders> orderdishes = (List<Orders>) ordersRepository.findAll();//(List<Dishes>) ordersRepository.findAll();
//        model.addAttribute("orderdishes",orderdishes);
//        return "index.html";
//    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin.html";
    }



    @RequestMapping(value = "/dish/create",method = RequestMethod.GET)
    public String showForm(@ModelAttribute Dishes dish, Model model){
        model.addAttribute("dishes",new Dishes());
        return "add_dish"; // .html?????
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
    public String addProductToCart(@PathVariable("id") Integer dishId, @ModelAttribute Orders order) {
        System.out.println("DishID "+ dishId);
        if (order.getPrice()==null) {
            order.setPrice(dishesRepository.findById(dishId).get().getPrice());
        }else {
            order.setPrice(dishesRepository.findById(dishId).get().getPrice() + order.getPrice());
        }
        order.setStatus("Создан");
        order.setTable(5);
        order.getDishes().add(dishesRepository.findById(dishId).get());
        ordersRepository.save(order);
        return "redirect:/index";
    }

//    @RequestMapping(value = "/path",method = RequestMethod.GET)
//    public @ResponseBody String getSomeData (@RequestParam("paramName") String someData){
//
//    }



}



//import com.ingvarr.Diplom.entity.Dishes;
//import com.ingvarr.Diplom.repositories.DishesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class DishesController {
//
//    @Autowired
//    private DishesRepository dishesRepository;
//
//
//    @RequestMapping(name = "/")
//    public String index(@ModelAttribute Dishes dish, Model model){
//
//        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
//        model.addAttribute("dishes", dishes);
//
//        return "index.html";
//    }
//
//    @RequestMapping("/admin")
//    public String admin(){
//        return "admin.html";
//    }
//
//
//
//    @RequestMapping(value = "/dish/create",method = RequestMethod.GET)
//    public String showForm(@ModelAttribute Dishes dish, Model model){
//        model.addAttribute("dishes",new Dishes());
//        return "add_dish"; // .html?????
//    }
//
//    @RequestMapping(value = "/dish/create",method = RequestMethod.POST)
//    public String submitForm(@ModelAttribute Dishes dish, Model model){
//        dishesRepository.save(dish);
//
//
//        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
//        model.addAttribute("dishes", dishes);
//        return "dish_list.html";
//    }
//
//    @RequestMapping("/dishes")
//    public String dishesList(){
//        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
//        return "dish_list.html";
//    }
//
//
//
//
////    @RequestMapping(value = "/path",method = RequestMethod.GET)
////    public @ResponseBody String getSomeData (@RequestParam("paramName") String someData){
////
////    }
//
//
//
//}
