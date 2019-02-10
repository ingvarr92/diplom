package com.ingvarr.Diplom.controllers;

import com.ingvarr.Diplom.entity.Dishes;
import com.ingvarr.Diplom.repositories.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DishesController {

    @Autowired
    private DishesRepository dishesRepository;


    @RequestMapping(name = "/")
    public String index(@ModelAttribute Dishes dish, Model model){

        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
        model.addAttribute("dishes", dishes);

        return "index.html";
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
    public String dishesList(){
        List<Dishes> dishes = (List<Dishes>) dishesRepository.findAll();
        return "dish_list.html";
    }




//    @RequestMapping(value = "/path",method = RequestMethod.GET)
//    public @ResponseBody String getSomeData (@RequestParam("paramName") String someData){
//
//    }



}
