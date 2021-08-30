package kz.accounting.model;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Что бы для всех тестов с аннотацией @SpringBootTest не поднимать отдельный контекст
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {

}
