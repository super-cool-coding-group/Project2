package src;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This is the CalculatorTest class which tests various input options for the convertToPostfix and evaluatePostfix methods
 * in the Calculator class
 *
 * @author Angelica Arteaga
 * @author George Matta
 * @version 1.0
 */
public class CalculatorTest {

    // Test a variety of infix expressions for convertToPostfix (a variety as in, many different operations)
    @Test
    public void testConvertToPostfix(){
        // Call converToPostfix with all numbers
        // Call convertToPostfix with all variables
        // Call converToPostfix with a mix of numbers and variables
        String test1 = Calculator.convertToPostfix("a*b/(c-a)+d*e");
        String expected1 = "ab*ca-/de*+";
        assertEquals(expected1, test1);

        String test2 = Calculator.convertToPostfix("(a+b)*(c-d)/e^f");
        String expected2 = "ab+cd-*ef^/";
        assertEquals(expected2, test2);

        String test3 = Calculator.convertToPostfix("a^b/c+d-e");
        String expected3 = "ab^c/d+e-";
        assertEquals(expected3, test3);

        String test4 = Calculator.convertToPostfix("a^((a+c)/e)");
        String expected4 = "aac+e/^";
        assertEquals(expected4, test4);
                
        // Call method with no arguments
        Exception exceptionTest = assertThrows(IllegalArgumentException.class, () -> {
            String test5 = Calculator.convertToPostfix("");
        });
        Assertions.assertEquals("The expression provided was either null or empty", exceptionTest.getMessage());
    }
    
    // Test a variety of postfix expressions with evaluatePostfix (a variety as in, many different operations and numbers)
    @Test
    public void testEvaluatePostfix(){
        int test1 = Calculator.evaluatePostfix("23*42-/56*+");
        int expected1 = 33;
        assertEquals(expected1, test1);

        int test2 = Calculator.evaluatePostfix("23+45-*67^/");
        int expected2 = 0;
        assertEquals(expected2, test2);

        int test3 = Calculator.evaluatePostfix("23^4/5+6-");
        int expected3 = 1;
        assertEquals(expected3, test3);

        // Call evaluatePostfix with an expression that would result in a 0
        int test5 = Calculator.evaluatePostfix("63*2+5/4-");
        int expected5 = 0;
        assertEquals(expected5, test5);

        // Call evaluatePostfix with an expression that would result in a negative number
        int test6 = Calculator.evaluatePostfix("23^4+6/5-");
        int expected6 = -3;
        assertEquals(expected6, test6);

        // Call evaluatePostfix with an expression that would result in a positive number
        int test7 = Calculator.evaluatePostfix("224+6/^");
        int expected7 = 2;
        assertEquals(expected7, test7);

        // Call method with no arguments
        Exception exceptionTest = assertThrows(IllegalArgumentException.class, () -> {
            int test8 = Calculator.evaluatePostfix("");
        });
        Assertions.assertEquals("The expression provided was either null or empty", exceptionTest.getMessage());
    }

}
