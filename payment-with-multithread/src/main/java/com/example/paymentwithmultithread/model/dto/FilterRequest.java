package com.example.paymentwithmultithread.model.dto;

import com.example.paymentwithmultithread.model.enum_filter.FilterType;
import com.example.paymentwithmultithread.model.enum_filter.Operator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterRequest {

    private String key;

    private Operator operator;

    private FilterType fieldType;

    private Object value;

    private Object valueTo;

    private List<Object> values;
}
