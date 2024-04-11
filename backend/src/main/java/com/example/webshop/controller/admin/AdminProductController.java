//package com.example.webshop.controller.admin;
//
//
//import com.example.webshop.dao.ProductDAO;
//import com.example.webshop.dto.ProductDTO;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200", "http://localhost:30017", "http://s1149771.student.inf-hsleiden.nl:30017"})
//@RequestMapping("/admin/products")
//public class AdminProductController {
//
//    private final ProductDAO productDAO;
//
//    public AdminProductController(ProductDAO productDAO) {
//        this.productDAO = productDAO;
//    }
//
//    // get all products - Not needed = Public
//    // create product
//    // update product
//    // delete product - Done
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createProduct(ProductDTO productDTO) {
//        this.productDAO.createProduct(productDTO);
//        return ResponseEntity.ok("Product created");
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable long id) {
//        this.productDAO.updateProduct(productDTO, id);
//        return ResponseEntity.ok("Product updated");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
//        this.productDAO.deleteById(id);
//        return ResponseEntity.ok("Product deleted");
//    }
//}
