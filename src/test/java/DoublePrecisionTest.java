import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see <a href="https://pvs-studio.com/en/blog/posts/0445/">Double Precision Patriot Missiles</a>
 */

public class DoublePrecisionTest {

    @Test
    public void sum1000_0dot1() {

        double acumulador = 0.0;
        for (int i = 0; i < 1000; i++) {
            acumulador+=0.1;
        }

        Assertions.assertNotEquals(100.0d,acumulador);
        Assertions.assertEquals(99.9999999999986, acumulador);

        Assertions.assertEquals(100.0, 1000.0d*0.1d);
    }

    @Test
    public void sum0dot2plus0dot1() {
        double a = 0.2;
        double b = 0.1;
        double r = a + b;

        Assertions.assertNotEquals(0.3d, r);
        Assertions.assertEquals(0.30000000000000004, r);

    }


}
