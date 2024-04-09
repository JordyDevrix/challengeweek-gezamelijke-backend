package com.example.webshop.dao;


import com.example.webshop.dto.WebshopDTO;
import com.example.webshop.models.Category;
import com.example.webshop.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDAO {

    private WebshopDAO webshopDAO;

    public CategoryDAO(WebshopDAO webshopDAO) {
        this.webshopDAO = webshopDAO;
    }

    public List<Category> getAllCategories(){
        List<WebshopDTO> webshopList = this.webshopDAO.getAllWebshops();
        List<Product> categoryList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        return null;
    }

    public Category getCategoryById(int id){
        return null;
    }


}
