package com.example.webshop.controller.admin;

import com.example.webshop.dao.AdminStatisticsDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200"})
@RequestMapping("/admin/statistics")
public class AdminStatisticsController {

    private AdminStatisticsDAO adminStatisticsDAO;

    public AdminStatisticsController(AdminStatisticsDAO adminStatisticsDAO) {
        this.adminStatisticsDAO = adminStatisticsDAO;
    }

    @GetMapping("/salesprice")
    public ResponseEntity<Double> getTotalSalesPrice() {
        return ResponseEntity.ok(this.adminStatisticsDAO.getTotalSalesPrice());
    }

    @GetMapping("/salesproducts")
    public ResponseEntity<Integer> getTotalSalesProducts() {
        return ResponseEntity.ok(this.adminStatisticsDAO.getTotalSalesProducts());
    }

    @GetMapping("/salesprice/{year}")
    public ResponseEntity<Double> getTotalSalesPriceYear(@PathVariable String year) {
        return ResponseEntity.ok(this.adminStatisticsDAO.getTotalSalesPriceYear(year));
    }

    @GetMapping("/salescustomers")
    public ResponseEntity<Integer> getTotalSalesCustomers() {
        return ResponseEntity.ok(this.adminStatisticsDAO.getTotalSalesCustomers());
    }

}
