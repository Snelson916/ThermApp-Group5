import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SimpleCalculatorTest {
    @Test
    void twoPlusTwoShouldEqualFour() {
        var calculator = new SimpleCalculator();
        var result = calculator.add(2,2);
        var expected = 4;
        assertEquals(expected, result);
    }
}
