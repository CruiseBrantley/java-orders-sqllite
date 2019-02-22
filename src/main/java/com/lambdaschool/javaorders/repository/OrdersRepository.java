package com.lambdaschool.javaorders.repository;

import com.lambdaschool.javaorders.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long>
{
}
