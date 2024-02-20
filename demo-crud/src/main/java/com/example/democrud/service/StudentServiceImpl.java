package com.example.democrud.service;

import com.example.democrud.entity.Student;
import com.example.democrud.exception.IdNotFoundException;
import com.example.democrud.repository.StudentRepository;
import com.example.democrud.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void exportExcel() throws IOException {
        String time = String.valueOf(System.currentTimeMillis());
        String fileName = "DANH_SACH_MA_DU_THUONG_" + time + ".xlsx";

        Workbook workbook = new XSSFWorkbook();
        CellStyle styleHeader = ExcelUtil.styleHeader(workbook);
        CellStyle styleTitle = ExcelUtil.styleTitle(workbook);
        CellStyle styleContent = ExcelUtil.styleContent(workbook);

        Sheet sheet = workbook.createSheet("List");

        Row rowTitle = sheet.createRow(1);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
        Cell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue("DANH SACH SINH VIÊN");
        cellTitle.setCellStyle(styleHeader);

        Row rowHeader = sheet.createRow(3);

        Cell cellHeader = rowHeader.createCell(0);
        cellHeader.setCellValue("ID");
        sheet.setColumnWidth(0, 2000);
        cellHeader.setCellStyle(styleTitle);

        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("NAME");
        sheet.setColumnWidth(1, 8000);
        cellHeader.setCellStyle(styleTitle);

        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue("GENDER");
        sheet.setColumnWidth(2, 8000);
        cellHeader.setCellStyle(styleTitle);

        cellHeader = rowHeader.createCell(3);
        cellHeader.setCellValue("ADDRESS");
        sheet.setColumnWidth(3, 14000);
        cellHeader.setCellStyle(styleTitle);

        cellHeader = rowHeader.createCell(4);
        cellHeader.setCellValue("AGE");
        sheet.setColumnWidth(4, 3000);
        cellHeader.setCellStyle(styleTitle);

        int index = 3;
        for (Student student : studentRepository.findAll()) {
            index++;
            Row row = sheet.createRow(index);
            Cell cell = row.createCell(0);
            cell.setCellValue(student.getId());
            cell = row.createCell(1);
            cell.setCellValue(student.getName());
            cell = row.createCell(2);
            cell.setCellValue(student.getGender());
            cell = row.createCell(3);
            cell.setCellValue(student.getAddress());
            cell = row.createCell(4);
            cell.setCellValue(student.getAge());

            cell.setCellStyle(styleContent);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }

    @Override
    public ResponseEntity<?> saveStudent(Student student) {
        if (studentRepository.existsStudentByName(student.getName())) {
            return ResponseEntity.ok("Tên không được trùng");
        }
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
