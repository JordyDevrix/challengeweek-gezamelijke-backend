package com.example.webshop.controller.admin;


import com.example.webshop.dao.CategoryDAO;
import com.example.webshop.dto.CategoryDTO;
import com.example.webshop.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200"})
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryDAO categoryDAO;

    public AdminCategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @PostMapping("/create/{webshopId}")
    public ResponseEntity<String> createCategory(@PathVariable long webshopId, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(this.categoryDAO.createCategory(categoryDTO, webshopId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String id, @RequestBody CategoryDTO categoryDTO) {
        this.categoryDAO.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("Category updated");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        this.categoryDAO.deleteCategory(id);
        return ResponseEntity.ok("Category deleted");
    }
}
