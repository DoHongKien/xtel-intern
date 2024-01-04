package com.exception.handling;

import com.exception.handling.entity.Role;
import com.exception.handling.entity.User;
import com.exception.handling.repository.RoleRepository;
import com.exception.handling.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addUserWithRoleAdmin() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role roleAdmin = entityManager.find(Role.class, 1);

        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setName("ADMIN");
        user.setDob(new Date());
        user.setAge(20);
        user.addRole(roleAdmin);

        User saveUser = userRepository.save(user);

        assertThat(saveUser.getId()).isGreaterThan(0);
    }

    @Test
    public void addUserWithTwoRole() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role editor = entityManager.find(Role.class, 3);
        Role shipper = entityManager.find(Role.class, 4);

        User user = new User();
        user.setUsername("guest");
        user.setPassword(passwordEncoder.encode("guest"));
        user.setName("GUEST");
        user.setDob(new Date());
        user.setAge(25);
        user.addRole(editor);
        user.addRole(shipper);

        User saveUsers = userRepository.save(user);
        assertThat(saveUsers.getId()).isGreaterThan(0);
    }
}
