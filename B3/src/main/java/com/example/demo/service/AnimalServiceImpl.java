package com.example.demo.service;

import com.example.demo.entity.Animal;
import com.example.demo.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public List<Animal> findAllAnimal() {
        List<Animal> animals = animalRepository.findAnimal();
        return animals;
    }

    @Override
    public Animal getAnimal(int id) {
        return animalRepository.findById(id).get();
    }

    @Override
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public List<Animal> callProcedureAnimal() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "kien03";
        String password = "2003";
        List<Animal> animals = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Tạo CallableStatement
            try (CallableStatement callableStatement = connection.prepareCall("{call findAllAnimal(?)}")) {
                // Đăng ký tham số đầu ra
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

                callableStatement.execute();

                ResultSet resultSet = ((OracleCallableStatement) callableStatement).getCursor(1);

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Double weight = resultSet.getDouble("weight");
                    Double height = resultSet.getDouble("height");
                    Animal animal = new Animal(id, name, weight, height);
                    animals.add(animal);
                }

                // Đóng ResultSet
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public String callProcedureInsert(Animal animal) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "kien03";
        String password = "2003";

        String name = animal.getName();
        double weight = animal.getWeight();
        double height = animal.getHeight();

        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, user, password);

            // Chuẩn bị câu lệnh gọi thủ tục
            String sql = "{call insertAnimal(?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(sql);

            // Đặt giá trị cho các tham số đầu vào
            callableStatement.setString(1, name);
            callableStatement.setDouble(2, weight);
            callableStatement.setDouble(3, height);

            // Đặt tham số đầu ra
            callableStatement.registerOutParameter(4, java.sql.Types.NUMERIC);

            // Thực thi thủ tục
            callableStatement.execute();

            // Lấy giá trị tham số đầu ra
            int success = callableStatement.getInt(4);

            // Đóng tài nguyên
            callableStatement.close();
            connection.close();

            // Kiểm tra giá trị trạng thái từ thủ tục
            if (success == 1) {
                return "Thực hiện thành công.";
            } else {
                return "Thực hiện thất bại.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Thực hiện thất bại.";
    }
}
