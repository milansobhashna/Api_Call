package com.da.myapplication

data class CategoryList(
    val `data`: ArrayList<Category>,
    val message: String,
    val last_page:String,
    val sucess: Int
)

data class Category(
    val id: String,
    val name: String,
    val product_link: String
)