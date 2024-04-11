package com.example.webshop.controller.priv;

import com.example.webshop.dao.OrderDAO;
import com.example.webshop.dao.UserRepository;
import com.example.webshop.dto.OrderDTO;
import com.example.webshop.models.CustomUser;
import com.example.webshop.models.Order;
import com.example.webshop.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200", "http://localhost:30017"})
@RequestMapping("/private/orders")
public class PrivateOrderController {
    private final OrderDAO orderDAO;
    private final UserService userService;
    private UserRepository userRepository;

    public PrivateOrderController(OrderDAO orderDAO, UserService userService, UserRepository userRepository) {
        this.orderDAO = orderDAO;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // get all orders related to user
    @GetMapping("/all")
    public List<Order> getAllOrders(Principal principal) {
        String email = principal.getName();
        CustomUser user = this.userService.loadUserByEmail(email);
        return this.orderDAO.getAllOrdersByUser(user.getId());
    }

    // get order by id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = this.orderDAO.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // create order
    @PostMapping("/create")
    public void createOrder(Principal principal, @RequestBody OrderDTO orderDTO) {

        String email = principal.getName();
        CustomUser user = this.userService.loadUserByEmail(email);

        this.orderDAO.createOrder(user, orderDTO.getProducts(), orderDTO.getStatus());
    }

    // pay open order
    @PostMapping("/pay/{id}")
    public void payOpenOrder(Principal principal, @PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        String email = principal.getName();
        CustomUser user = this.userService.loadUserByEmail(email);
        String status = orderDTO.getStatus();
        this.orderDAO.payOpenOrder(user, id, status);
    }

}
