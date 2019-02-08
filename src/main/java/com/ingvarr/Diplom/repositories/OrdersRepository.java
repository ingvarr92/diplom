package com.ingvarr.Diplom.repositories;

import com.ingvarr.Diplom.entity.Orders;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Integer> {
}
