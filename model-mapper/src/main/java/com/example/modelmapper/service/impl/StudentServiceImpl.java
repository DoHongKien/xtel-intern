package com.example.modelmapper.service.impl;

import com.example.modelmapper.entity.Address;
import com.example.modelmapper.entity.Student;
import com.example.modelmapper.model.ShowStudentDto;
import com.example.modelmapper.model.StudentDto;
import com.example.modelmapper.repository.AddressRepository;
import com.example.modelmapper.repository.StudentRepository;
import com.example.modelmapper.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Student> getAllStudent(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    @Override
    public ShowStudentDto getStudent(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ArithmeticException(""));
        modelMapper.createTypeMap(Student.class, ShowStudentDto.class)
                .addMapping(address -> address.getAddress().getCountry(), ShowStudentDto::setCountry)
                .addMapping(address -> address.getAddress().getProvince(), ShowStudentDto::setProvince)
                .addMapping(address -> address.getAddress().getDistrict(), ShowStudentDto::setDistrict)
                .addMapping(address -> address.getAddress().getWard(), ShowStudentDto::setWard);
        return modelMapper.map(student, ShowStudentDto.class);
    }

    @Override
    public Student saveStudent(StudentDto studentDto) {
        Address address = addressRepository.findById(studentDto.getAddressId())
                .orElseThrow(() -> new ArithmeticException("Student"));
        Student student = modelMapper.map(studentDto, Student.class);
        student.setAddress(address);
        return studentRepository.save(student);
    }
}
