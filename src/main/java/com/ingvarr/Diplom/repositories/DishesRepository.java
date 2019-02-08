package com.ingvarr.Diplom.repositories;

import com.ingvarr.Diplom.entity.Dishes;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DishesRepository extends PagingAndSortingRepository <Dishes, Integer> {

}
