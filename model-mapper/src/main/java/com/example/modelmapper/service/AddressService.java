package com.example.modelmapper.service;

import com.example.modelmapper.entity.Address;
import com.example.modelmapper.model.AddressDto;
import org.springframework.data.domain.Page;

public interface AddressService {

    Page<Address> getAllAddress(int page, int size);



    Address saveAddress(AddressDto addressDto);
}
