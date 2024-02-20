package com.example.modelmapper.service.impl;

import com.example.modelmapper.entity.Address;
import com.example.modelmapper.model.AddressDto;
import com.example.modelmapper.repository.AddressRepository;
import com.example.modelmapper.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Address> getAllAddress(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return addressRepository.findAll(pageable);
    }

    @Override
    public Address saveAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        return addressRepository.save(address);
    }
}
