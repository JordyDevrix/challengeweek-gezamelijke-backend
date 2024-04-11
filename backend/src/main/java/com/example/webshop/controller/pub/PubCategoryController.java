package com.example.webshop.controller.pub;

import com.example.webshop.dao.CategoryDAO;
import com.example.webshop.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200", "http://localhost:30017"})
@RequestMapping("/pub/categories")
public class PubCategoryController {

    private final CategoryDAO categoryDAO;

    public PubCategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(this.categoryDAO.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id){
        return ResponseEntity.ok(this.categoryDAO.getCategoryById(id));
    }
}