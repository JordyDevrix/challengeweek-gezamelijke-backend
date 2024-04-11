//package com.example.webshop.controller.admin;
//
//
//import com.example.webshop.dao.CategoryDAO;
//import com.example.webshop.dto.CategoryDTO;
//import com.example.webshop.models.Category;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200", "http://localhost:30017", "http://s1149771.student.inf-hsleiden.nl:30017"})
//@RequestMapping("/admin/categories")
//public class AdminCategoryController {
//    private final CategoryDAO categoryDAO;
//
//    public AdminCategoryController(CategoryDAO categoryDAO) {
//        this.categoryDAO = categoryDAO;
//    }
//
//    @GetMapping("/all")
//    public List<Category> getAllCategories() {
//        return this.categoryDAO.getAllCategories();
//    }
//
//    @GetMapping("/{id}")
//    public Category getCategoryById(@PathVariable long id) {
//        return this.categoryDAO.getCategoryById(id);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
//        this.categoryDAO.createCategory(categoryDTO);
//        return ResponseEntity.ok("Category created");
//    }
//
//    @PostMapping("/update/{id}")
//    public ResponseEntity<String> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
//        this.categoryDAO.updateCategory(id, categoryDTO);
//        return ResponseEntity.ok("Category updated");
//    }
//
//    @PostMapping("/delete/{id}")
//    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
//        this.categoryDAO.deleteCategory(id);
//        return ResponseEntity.ok("Category deleted");
//    }
//
//}
