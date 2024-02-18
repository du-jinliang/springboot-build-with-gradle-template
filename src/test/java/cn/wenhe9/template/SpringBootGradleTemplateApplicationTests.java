package cn.wenhe9.template;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootGradleTemplateApplicationTests {

    @Test
    void contextLoads() {
        assertEquals(2, 1 + 1);
    }

}
