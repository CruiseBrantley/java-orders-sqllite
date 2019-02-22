package com.lambdaschool.javaorders.repository;

import com.lambdaschool.javaorders.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long>
{
}
