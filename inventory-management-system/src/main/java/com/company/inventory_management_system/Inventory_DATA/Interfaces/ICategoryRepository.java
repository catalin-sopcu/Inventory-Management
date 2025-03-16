package com.company.inventory_management_system.Inventory_DATA.Interfaces;

import com.company.inventory_management_system.Inventory_DATA.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
