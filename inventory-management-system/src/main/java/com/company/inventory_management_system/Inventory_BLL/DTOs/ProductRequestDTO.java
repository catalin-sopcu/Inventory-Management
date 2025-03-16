package com.company.inventory_management_system.Inventory_BLL.DTOs;

import com.company.inventory_management_system.Inventory_BLL.Constants.ProductValidationConstants;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank(message = ProductValidationConstants.PRODUCT_NAME_REQUIRED)
    @Size(min = 3, message = ProductValidationConstants.PRODUCT_NAME_MIN_LENGTH)
    private String name;

    @NotNull(message = ProductValidationConstants.PRODUCT_PRICE_REQUIRED)
    @Min(value = 1, message = ProductValidationConstants.PRODUCT_PRICE_MIN_VALUE)
    private Double price;

    @Min(value = 0, message = ProductValidationConstants.PRODUCT_QUANTITY_MIN_VALUE)
    private Integer quantity;

    @NotNull(message = ProductValidationConstants.PRODUCT_CATEGORY_ID_REQUIRED)
    @Positive(message = ProductValidationConstants.PRODUCT_CATEGORY_ID_POSITIVE)
    private Long categoryId;
}
