package com.company.inventory_management_system.Inventory_DATA.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.inventory_management_system.Inventory_DATA.Entities.Product;
import java.util.*;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByQuantityLessThanEqual(int threshold);
}
