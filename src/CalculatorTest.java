package src;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
        // Call converToPostfix with a mix of numbers and variables
        String test1 = Calculator.convertToPostfix("a*b/(c-a)+d*e");
        String expected1 = "ab*ca-/de*+";
        assertEquals(expected1, test1);

        String test2 = Calculator.convertToPostfix("(a+b)*(c-d)/e^f");
        String expected2 = "ab+cd-*ef^/";
        assertEquals(expected2, test2);

        String test3 = Calculator.convertToPostfix("a^((a+c)/e)");
        String expected3 = "aac+e/^";
        assertEquals(expected3, test3);
    }

    // Call convertToPostfix with all operators
    @Test
    public void testConvertToPostfix_onlyOperators(){
        String test = Calculator.convertToPostfix("+-^-*/+(+)^");
        String expected = "+^-*/-+^+";
        assertEquals(expected, test);
    }

    // Call convertToPostfix with all variables
    @Test
    public void testConvertToPostfix_onlyVariables(){
        String test = Calculator.convertToPostfix("abcdef");
        String expected = "abcdef";
        assertEquals(expected, test);
    }

    // Call convertToPostfix with no arguments
    @Test
    public void testConvertToPostfix_Exception(){
        Exception exceptionTest = assertThrows(IllegalArgumentException.class, () -> {
            String test = Calculator.convertToPostfix("");
        });
        Assertions.assertEquals("The expression provided was either null or empty", exceptionTest.getMessage());
    }


    // Test a variety of postfix expressions with evaluatePostfix (a variety as in, many different operations and numbers)
    @Test
    public void testEvaluatePostfix(){
        // Call evaluatePostfix with a mix of numbers and variables
        int test1 = Calculator.evaluatePostfix("23*42-/56*+");
        int expected1 = 33;
        assertEquals(expected1, test1);

        int test2 = Calculator.evaluatePostfix("23+45-*67^/");
        int expected2 = 0;
        assertEquals(expected2, test2);

        int test3 = Calculator.evaluatePostfix("23^4/5+6-");
        int expected3 = 1;
        assertEquals(expected3, test3);
    }

    // Call evaluatePostfix with an expression that would result in a 0
    @Test
    public void testEvaluatePostfix_Zero(){
        int test = Calculator.evaluatePostfix("63*2+5/4-");
        int expected = 0;
        assertEquals(expected, test);
    }
    
    // Call evaluatePostfix with an expression that would result in a negative number
    @Test
    public void testEvaluatePostfix_Negative(){
        int test = Calculator.evaluatePostfix("23^4+6/5-");
        int expected = -3;
        assertEquals(expected, test);
    }

    // Call evaluatePostfix with an expression that would result in a positive number
    @Test
    public void testEvaluatePostfix_Positive(){
        int test = Calculator.evaluatePostfix("224+6/^");
        int expected = 2;
        assertEquals(expected, test);
    }

    // Call method with no arguments
    @Test
    public void testEvaluatePostfix_Exception(){
        // Call method with no arguments
        Exception exceptionTest = assertThrows(IllegalArgumentException.class, () -> {
            int test = Calculator.evaluatePostfix("");
        });
        Assertions.assertEquals("The expression provided was either null or empty", exceptionTest.getMessage());
    }

}
