package com.example.demo.com.codegym.controller;

import com.example.demo.com.codegym.model.Customer;
import com.example.demo.com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/create-customer")
    public ModelAndView showcreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "da tao thanh cong khach hang");
        return modelAndView;
    }

    @GetMapping("customers")
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customerOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("/customer/error.404");
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "da sua thanh cong");
        return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customerOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("customer/error.404");
        }
    }
    @PostMapping("/delete-customer")
    public String  deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:customers";
    }

}


