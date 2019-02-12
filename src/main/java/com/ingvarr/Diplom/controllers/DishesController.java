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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
public class DishesController {
    @Autowired
    protected DishesRepository dishesRepository;

    @Autowired
    protected OrdersRepository ordersRepository;

    Orders order = new Orders();


    @RequestMapping(name = "/")
    public String index(@ModelAttribute Dishes dish, Model model,@RequestParam(value = "orderId", required = true)Integer orderId) {
        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
        model.addAttribute("dishes", dishes);
        model.addAttribute("orderId",orderId);


            List<Dishes> ordereddishes = (List<Dishes>) ordersRepository.findById(orderId).get().getDishes();//(List<Dishes>) ordersRepository.findAll();
            model.addAttribute("orderdishes", ordereddishes);

        //System.out.println(order.getId());
            return "index.html";
    }


    //http://localhost:8080/add_tbl?tbl=1
    @RequestMapping("/add_tbl")
    public String getCoursesByTittle(@RequestParam(value = "tbl", required = true)Integer tbl){
        Orders order = new Orders();
        order.setTable(tbl);
        order.setPrice(0);
        order.setStatus("Открыт");
        ordersRepository.save(order);
        Integer orderId = order.getId();

        return "redirect:/index?orderId=" +orderId;

    }

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
    public String addProductToCart(@PathVariable("id") Integer dishId,@RequestParam(value = "orderId", required = true)Integer orderId) {
        Orders order =ordersRepository.findById(orderId).get();
        order.setPrice(dishesRepository.findById(dishId).get().getPrice() + order.getPrice());
        order.setStatus("Создан");
        order.setTable(5);
        order.getDishes().add(dishesRepository.findById(dishId).get());
        ordersRepository.save(order);

        return "redirect:/index?orderId="+orderId;
    }
}