package com.company.inventory_management_system.Inventory_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.company.inventory_management_system.Inventory_BLL", "com.company.inventory_management_system.Inventory_API.Controllers"})
@EnableJpaRepositories(basePackages = "com.company.inventory_management_system.Inventory_DATA.Repositories")
@EntityScan(basePackages = "com.company.inventory_management_system.Inventory_DATA.Entities")
public class InventoryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
	}

}
