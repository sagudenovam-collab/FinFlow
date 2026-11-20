package pet.proect.finflow.repository;

import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import pet.proect.finflow.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pet.proect.finflow.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserByEmail() {

        // Arrange: создаём пользователя
        User user = new User();

        user.setEmail("test@gmail.com");
        user.setPasswordHash(123456L);
        user.setRole("USER");
        user.setCreatedAt(System.currentTimeMillis());

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
                .isEqualTo(123456L);

        assertThat(foundUser.getRole())
                .isEqualTo("USER");

        assertThat(foundUser.getCreatedAt())
                .isEqualTo(user.getCreatedAt());
    }
}
