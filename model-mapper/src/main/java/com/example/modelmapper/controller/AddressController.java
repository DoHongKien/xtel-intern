package com.example.modelmapper.controller;

import com.example.modelmapper.entity.Address;
import com.example.modelmapper.model.AddressDto;
import com.example.modelmapper.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public Page<Address> getAllAddress(@RequestParam Integer page, @RequestParam Integer size) {
        return addressService.getAllAddress(page, size);
    }

    @PostMapping
    public Address saveAddress(@RequestBody AddressDto addressDto) {
        return addressService.saveAddress(addressDto);
    }
}
