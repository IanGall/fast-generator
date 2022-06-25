package net.maku.generator.service;

import net.maku.generator.config.template.GeneratorConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaseClassServiceTest {

    @Value("${java.io.tmpdir}")
    private String tempDir;

    @Autowired
    GeneratorConfig generatorConfig;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void page() {
        System.out.println(tempDir);
    }

    @Test
    void name() {
        System.out.println(generatorConfig.getGeneratorConfig());
    }
}