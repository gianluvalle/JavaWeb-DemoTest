package it.unikey.primaconnessionedbpostgres;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Testcontainers
class PrimaConnessioneDbPostgresApplicationTests {

    @Container
    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");

   /* // > 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }*/

    @Test
    void contextLoads() {
        System.out.println("Context loads!");
    }

   /* @Test
    void testContainer() {
        assertTrue(container.isRunning(), "Docker container should be running");
    }*/

}
