package com.example.springbatchschedule.batch;

import com.example.springbatchschedule.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class StudentProcessor implements ItemProcessor<Student, Student> {

    // Xử lý các dữ liệu lấy được trước khi thực thi tiếp
    @Override
    public Student process(Student item) {
        log.info("Student process {}", item);
        return item;
    }
}
