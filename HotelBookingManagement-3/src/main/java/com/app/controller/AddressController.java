package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.AddressPage;
import com.app.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    
    @GetMapping("/confirm-address")
    public String showConfirmAddressForm(Model model) {
        model.addAttribute("addressPage", new AddressPage());
        return "confirm-address"; 
    }
    
    @PostMapping("/confirm-address")
    public String submitConfirmAddressForm(@RequestBody AddressPage addressPage,Model model) {
        addressService.saveAddress(addressPage);
        return "Address saved successfully"; 
    }
        
    @DeleteMapping("/delete-address/{id}")
    public String deleteAddress(@PathVariable Long id, Model model) {
        addressService.deleteAddressById(id);

        model.addAttribute("message", "Address deleted successfully!");
        return "Delete Successfully"; 
    }

    @PutMapping("/update-address/{id}")
    public ResponseEntity<AddressPage> updateAddress(@PathVariable Long id, @RequestBody AddressPage addressPage) {
        // Call the service method to update the address by ID
        AddressPage updatedAddress = addressService.updateAddress(id, addressPage);

        if (updatedAddress != null) {
            return ResponseEntity.ok(updatedAddress);  // Return the updated address with a 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 if the address is not found
        }
    }
}


