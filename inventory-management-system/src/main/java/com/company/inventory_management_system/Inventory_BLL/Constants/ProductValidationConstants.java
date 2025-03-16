package com.company.inventory_management_system.Inventory_BLL.Constants;

public class ProductValidationConstants {
    public static final String PRODUCT_NAME_REQUIRED = "Product name is required.";
    public static final String PRODUCT_NAME_MIN_LENGTH = "Product name must have at least 3 characters.";
    public static final String PRODUCT_PRICE_REQUIRED = "Product price is required.";
    public static final String PRODUCT_PRICE_MIN_VALUE = "Product price must be greater than or equal to 1.";
    public static final String PRODUCT_QUANTITY_MIN_VALUE = "Product quantity must be 0 or greater.";
    public static final String PRODUCT_CATEGORY_ID_REQUIRED = "Category ID is required.";
    public static final String PRODUCT_CATEGORY_ID_POSITIVE = "Category ID must be a positive number.";
}