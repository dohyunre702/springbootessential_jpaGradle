import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll() {
        System.out.println("## beforeAll Annotation 호출 ##");
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("## afterAll Annotation 호출 ##");
        System.out.println();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("## beforeEach Annotation 호출 ##");
        System.out.println();
    }

    @AfterEach
    void afterEach() {
        System.out.println("## afterEach Annotation 호출 ##");
        System.out.println();
    }


    @Test
    void test1() {
        System.out.println("## test 1 시작 ##");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case 2")
    void test2() {
        System.out.println("## test 2 시작 ##");
        System.out.println();
    }

    @Test
    @Disabled
    void test3() {
        System.out.println("## test 3 시작 ##");
        System.out.println();
    }

}
