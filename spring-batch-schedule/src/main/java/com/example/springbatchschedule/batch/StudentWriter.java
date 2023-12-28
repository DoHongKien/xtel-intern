package com.example.springbatchschedule.batch;

import com.example.springbatchschedule.entity.Student;
import com.example.springbatchschedule.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentWriter implements ItemWriter<Student> {

    @Autowired
    private StudentRepository studentRepository;

    // Dùng hàm saveAll lưu dữ liệu lấy được vào database
    @Override
    public void write(Chunk<? extends Student> chunk) {
        log.info("Student write {}", chunk.getItems().size());
        studentRepository.saveAll(chunk.getItems());
    }
}
