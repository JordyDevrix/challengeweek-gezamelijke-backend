package com.example.webshop.controller.admin;


import com.example.webshop.dao.UserDAO;
import com.example.webshop.models.CustomUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com",
        "http://localhost:4200", "http://localhost:30017", "http://s1149771.student.inf-hsleiden.nl:30017"})
@RequestMapping("/admin/users")
public class AdminUserController {


    private final UserDAO userDAO;

    public AdminUserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomUser>> getUsers(){
        return ResponseEntity.ok(this.userDAO.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomUser> getUserById(@PathVariable long id){
        if (this.userDAO.getUserById(id) != null) {
            return ResponseEntity.ok(this.userDAO.getUserById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping("/create")
    public ResponseEntity<CustomUser> createUser(@RequestBody CustomUser user) {
        return ResponseEntity.ok(this.userDAO.createUser(user));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<CustomUser> updateUser(@PathVariable long id, @RequestBody CustomUser user) {
        return ResponseEntity.ok(this.userDAO.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        if (this.userDAO.deleteUser(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
