package com.example.webshop.controller.admin;


import com.example.webshop.dao.UserDAO;
import com.example.webshop.models.CustomUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200"})
@RequestMapping("/admin/users")
public class AdminUserController {


    private final UserDAO userDAO;

    public AdminUserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CustomUser>> getUsers(){
        return ResponseEntity.ok(this.userDAO.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomUser> getUserById(@PathVariable long id, Principal principal){
        // check if user is requesting his own data or is admin
        if (Long.parseLong(principal.getName()) == id || principal.getName().equals("admin")) {
            return ResponseEntity.ok(this.userDAO.getUserById(id));
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CustomUser user) {
        return ResponseEntity.ok("created user");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<CustomUser> updateUser(@PathVariable long id, @RequestBody CustomUser user, Principal principal) {
        // check if user is requesting his own data or is admin
        if (Long.parseLong(principal.getName()) == id || principal.getName().equals("admin")) {
            return ResponseEntity.ok(this.userDAO.updateUser(id, user));
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok("deleted user");
    }

}
