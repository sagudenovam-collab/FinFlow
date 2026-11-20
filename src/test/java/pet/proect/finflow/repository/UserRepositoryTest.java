
package pet.proect.finflow.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import pet.proect.finflow.entity.User;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserByEmail() {

        // Arrange: создаём пользователя
        User user = new User();

        user.setEmail("test@gmail.com");
        user.setPasswordHash("hashed_password_123");
        user.setRole("USER");
        user.setCreatedAt(OffsetDateTime.now());

        // Act: сохраняем пользователя
        User savedUser = userRepository.saveAndFlush(user);

        // Act: ищем пользователя по email
        User foundUser = userRepository
                .findByEmail("test@gmail.com")
                .orElseThrow();

        // Assert: проверяем результат
        assertThat(savedUser.getId()).isNotNull();

        assertThat(foundUser.getEmail())
                .isEqualTo("test@gmail.com");

        assertThat(foundUser.getPasswordHash())
                .isEqualTo("hashed_password_123");

        assertThat(foundUser.getRole())
                .isEqualTo("USER");

        assertThat(foundUser.getCreatedAt())
                .isEqualTo(user.getCreatedAt());
    }
}
