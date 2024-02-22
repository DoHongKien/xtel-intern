package com.example.modelmapper.controller;

import com.example.modelmapper.entity.Address;
import com.example.modelmapper.model.AddressDto;
import com.example.modelmapper.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/address")
@RequiredArgsConstructor
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @GetMapping
    public Page<Address> getAllAddress(@RequestParam Integer page, @RequestParam Integer size) {
        logger.error("error getAllAddress");
        log.warn("warning getAllAddress");
        log.info("info getAllAddress");
        return addressService.getAllAddress(page, size);
    }

    @PostMapping
    public Address saveAddress(@RequestBody AddressDto addressDto) {
        return addressService.saveAddress(addressDto);
    }
}
