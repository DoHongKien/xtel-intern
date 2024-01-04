package com.exception.handling;

import static org.assertj.core.api.Assertions.assertThat;

import com.exception.handling.entity.Role;
import com.exception.handling.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        roles.forEach(System.out::println);
    }

    @Test
    public void findRoleById() {
        // Admin có id là 1 nên khi chạy hàm này sẽ test thành công
        Role role = roleRepository.findById(1L).get();
        assertThat(role.getRole()).isEqualToIgnoringCase("ADMIN");
    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setRole("EDITOR");
        Role saveRole = roleRepository.save(role);

        assertThat(saveRole.getId()).isGreaterThan(0);
    }

    @Test
    public void addAllRole() {
        Role shipper = new Role();
        shipper.setRole("SHIPPER");

        Role designer = new Role();
        designer.setRole("DESIGNER");

        List<Role> roles = roleRepository.saveAll(Arrays.asList(shipper, designer));
        assertThat(roles.size()).isEqualTo(2);

    }
}
