//package com.example.webshop.controller.admin;
//
//
//import com.example.webshop.dao.AdminDAO;
//import com.example.webshop.models.CustomUser;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200"})
//@RequestMapping("/admin/users")
//public class AdminUserController {
//
//
//    private final AdminDAO adminDAO;
//
//    public AdminUserController(AdminDAO adminDAO) {
//        this.adminDAO = adminDAO;
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<CustomUser>> getUsers(){
//        return ResponseEntity.ok(this.adminDAO.getUsers());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomUser> getUserById(@PathVariable long id){
//        return ResponseEntity.ok(this.adminDAO.getUserById(id));
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createUser() {
//        return ResponseEntity.ok("created user");
//    }
//
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable long id) {
//        return ResponseEntity.ok("updated user");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable long id) {
//        return ResponseEntity.ok("deleted user");
//    }
//
//}
