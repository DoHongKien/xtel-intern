package com.example.demo.service;

import com.example.demo.entity.Animal;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.util.OracleConnection;
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
    private final OracleConnection oracleConnection;

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
    public Animal callProcedureFindAnimalById(Integer id) {
        try {
            Connection connection = oracleConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("call FIND_ANIMAL_BY_ID(?,?)");
            callableStatement.setInt(1, id);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet resultSet = ((OracleCallableStatement) callableStatement).getCursor(2);

            if (resultSet.next()) {
                Integer animalId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double weight = resultSet.getDouble("weight");
                Double height = resultSet.getDouble("height");
                return new Animal(animalId, name, weight, height);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Animal> callProcedureAnimal() {
        List<Animal> animals = new ArrayList<>();

        try (Connection connection = oracleConnection.getConnection()) {

            // Tạo CallableStatement
            try (CallableStatement callableStatement = connection.prepareCall("call FIND_ALL_ANIMAL(?)")) {
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

        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = oracleConnection.getConnection();

            // Chuẩn bị câu lệnh gọi thủ tục
            String sql = "call INSERT_ANIMAL(?, ?, ?, ?)";
            CallableStatement callableStatement = connection.prepareCall(sql);

            // Đặt giá trị cho các tham số đầu vào
            callableStatement.setString(1, animal.getName());
            callableStatement.setDouble(2, animal.getWeight());
            callableStatement.setDouble(3, animal.getHeight());

            // Đặt tham số đầu ra
            callableStatement.registerOutParameter(4, OracleTypes.NUMERIC);

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

    @Override
    public String callProcedureDeleteAnimalById(Integer id) {
        try {
            Connection connection = oracleConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("call DELETE_ANIMAL_BY_ID(?,?)");
            callableStatement.setInt(1, id);
            callableStatement.registerOutParameter(2, OracleTypes.NUMBER);

            callableStatement.execute();
            int result = callableStatement.getInt(2);
            if(result == 1) {
                return "Xóa thành công!";
            } else {
                return "Xóa thất bại";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
