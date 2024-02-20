package com.example.modelmapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    private Integer id;

    private String country;

    private String province;

    private String district;

    private String ward;
}
