package org.pro.springorder.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
//@CrossOrigin(origins = "*")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @GetMapping("/api/v1/customers")
    @ResponseBody
    public List<Customer> findCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers")
    public String viewCustomersPage(Model model) {
        var allCustomers = customerService.getAllCustomers();
        model.addAttribute("serverTime", LocalDateTime.now());
        model.addAttribute("customers", allCustomers);
        return "views/customers";
    }

    @GetMapping("/customers/{customerId}")
//  @CrossOrigin(origins = "*")
    public ResponseEntity<Customer> findCustomer(@PathVariable("customerId") UUID customerId) {
        var customer = customerService.getCustomer(customerId);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/customers/{customerId}")
    public CustomerDto saveCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customer) {
        logger.info("Got customer save request {}", customer);
        return customer;
    }

    @GetMapping("/customers/new")
    public String viewNewCustomerPage() {
        return "views/new-customers";
    }

    @PostMapping("/customers/new")
    public String addNewCustomer(CreateCustomerRequest createCustomerRequest) {
        customerService.createCustomer(createCustomerRequest.email(), createCustomerRequest.name());
        return "redirect:/customers";
    }
}
