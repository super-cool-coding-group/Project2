package src;

/**
 * This is the Calculator class. This class hosts the main evaluation methods for this project.
 *
 * To understand the concepts this class aims to integrate, there are a few definitions to list:
 * Infix: A way of writing an mathematical expression. In an infix format, each operator is in *between* the operands which it
 * applies the operations on. This is the general way most math classes display their expressions. Ex: 1 + 2, a - b, c / d.
 * Postfix: A way of writing a mathematical expression where each operator is *after* the operands which it applies operations onto
 * So, if you were to write "a+b", an infix expression, its postfix counterpart would be "ab+".
 *
 * For more complex expressions like "a*b/(c-a)+d*e", the postfix counterpart would be "ab*ca-/de*+"
 *
 * In this class, we have a convertToPostfix method to take an infix string and convert it to a postfix string
 * We also have an evaluatePostfix method which takes a postfix string (which is composed of numbers and operators, not variables)
 * and applies all the necessary operations on it to evaluate the expression.
 *
 * The methods also use a helper method getOperatorPrecedence to apply the necessary PEMDAS order to the operations.
 *
 * Finally, there is a main method that tests the basic functionality of our two methods.
 *
 * Each method is documented separately.
 *
 * @author Pierlorenzo Peruzzo
 * @author George Matta
 * @version 1.1
 */
public class Calculator {

    /**
     * A main method which just calls each of our methods once to see their functionality.
     * The CalculatorTest class calls more extensive tests.
     *
     * We call convertToPostfix with the expression a*b/(c-a)+d*e. The postfix representation is ab*ca-/de*+.
     * For evaluatePostfix, we substitute the values a=2 b=3 c=4 d=5 e=6 in the above equation. The answer is 33.
     *
     * @param args The default main method args string array parameter
     */
    public static void main(String[] args){
        String inf = "a*b/(c-a)+d*e";
        System.out.println(Calculator.convertToPostfix(inf));

        int res = Calculator.evaluatePostfix("23*42-/56*+");
        System.out.println(res);
    }

    /**
     * Converts an expression from an infix form to a postfix form using a stack
     *
     * @param infix A string of an infix expression
     * @return The postfix string we created
     * @throws IllegalArgumentException If the expression passed was null or empty
     */
    public static String convertToPostfix(String infix){

        // Check that we got an expression
        if(infix == null || infix.length() <= 0){
            throw new IllegalArgumentException("The expression provided was either null or empty");
        }

        // Initialize an empty LinkedStack to store the operators in the infix string
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        // Initialize an empty postfix string, we will use this to return the final value
        String postfix = "";

        // We loop through all of the characters in the infix string
        char currChar;
        for (int i = 0; i < infix.length(); ++i){
            // Get the current character at the looping index (i)
            currChar = infix.charAt(i);
            // If we are dealing with a balck character, we skip processing and go to the next iteration
            if (currChar == ' ')
                continue;

            // We switch the character c to understand how we should process it
            switch (currChar) {
                case '^':
                    // Add '^' in the stack
                    operatorStack.push(currChar);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    // We pop the operators from the stack and we append them to the postifx output string.
                    // We do this until: 1) the stack is empty or 2) the top entry has a lower precendence than the
                    // new operator onto the stack
                    while (!operatorStack.isEmpty() &&
                    (getOperatorPrecedence(currChar) <= getOperatorPrecedence(operatorStack.peek()))) {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    // Finally, we push the new operator in the stack
                    operatorStack.push(currChar);
                    break;
                case '(':
                    // We push the '(' in the stack
                    operatorStack.push(currChar);
                    break;
                case ')':
                    // We pop the operators from the stack and we append them to the oputput until we pop and '('
                    // We also discard both parenthesis
                    char topOperator = operatorStack.pop();
                    while (topOperator != '('){
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                    break;
                default:
                    // In the default case we check to see if out character c is a variable (either a letter or a digit)
                    if (Character.isLetterOrDigit(currChar)){
                        // If yes, we append c to the postfix string
                        postfix += currChar;
                        break;
                    }
                    break;
            }
        }

        // Finally, we remove all of the operators in the stack and we add them to the postfix string
        while (!operatorStack.isEmpty()){
            char topOperator = operatorStack.pop();
            postfix += topOperator;
        }

        // Return the final converted postifx string
        return postfix;
    }

    /**
     * Evaluates an expression given in postfix form.
     *
     * We use a Stack to solve the equation. The general algorithm is as follows:
     * We iterate through each character in the postfix expression.
     * If it is a digit, we add it to our stack.
     * If it is an operator, we apply the operation on the top two items in our stack and add the result to our stack
     * At the end of the exprssion, the top value in our stack is the result of the postfix expression.
     *
     * @param postfix The postfix form expression string we are evaluating
     * @return An int denoting the result of the postfix form expression
     * @throws IllegalArgumentException If the expression passed was null or empty
     */
    public static int evaluatePostfix(String postfix){

        // Check that we got an expression
        if(postfix == null || postfix.length() <= 0){
            throw new IllegalArgumentException("The expression provided was either null or empty");
        }

        // Create a stack to evaluate the postfix expression
        ResizeableArrayStack<Integer> valueStack = new ResizeableArrayStack<Integer>();

        // We create empty an empty variable for our current character
        char currChar;
        // We also create empty int variables for our two operands
        int operandOne;
        int operandTwo;
        // And an empty int variable for our result
        int result;
        // These variables will be filled as we progress through the expression
        // We continuously loop through the postfix exp and assign a new current character
        for(int i = 0; i < postfix.length(); i++){
            // Assign the currChar variable to the character we are looking at
            currChar = postfix.charAt(i);
            // Sanitize all our inputs
            // Make sure the character is not empty
            if(currChar == ' '){
                continue;
            }
            // Check if the postfix was even valid to begin with
            if(!Character.isDigit(currChar) && getOperatorPrecedence(currChar) == -1){
                throw new IllegalArgumentException(
                    "The Postfix expression passed is composed of a non-digit or a non-operator character '" +
                    currChar + "' at index " + i +"."
                );
            }
            // Assertion: currChar is a digit or an operator

            // If we are at a digit, we push it into the stack
            if(Character.isDigit(currChar)){
                valueStack.push(Character.getNumericValue(currChar));
                continue;
            }
            // Assertion: currChar is an operator

            // Otherwise, we are at an operator. In this case, we apply the necessary operation
            // The top two items in our stack are the second and first operands (LIFO)
            operandTwo = valueStack.pop();
            operandOne = valueStack.pop();
            // We check the current character to see which operator it is
            switch(currChar){
                // For each operator, we apply the operation, push the result into the stack, and break out
                case '+': // Addition
                    result = operandOne + operandTwo;
                    valueStack.push(result);
                    break;
                case '-': // Subtraction
                    result = operandOne - operandTwo;
                    valueStack.push(result);
                    break;
                case '*': // Multiplication
                    result = operandOne * operandTwo;
                    valueStack.push(result);
                    break;
                case '/': // Division
                    result = operandOne / operandTwo;
                    valueStack.push(result);
                    break;
                case '^': // Exponentiation
                    result = (int) Math.pow(operandOne, operandTwo);
                    valueStack.push(result);
                    break;
                // We don't care about order of presedence when calculating a postfix expression, because the postfix
                // expression should be a valid representation of the expression we are evaluating
                default: // Just in case a special character makes its way through our sanitization
                    break;
            }
        }

        // We return the top value in our stack, which should be the final result
        return valueStack.peek();
    }

    /**
     * Retrieve the operational precedence (a number denoting the order in which operations occur) given a character
     *
     * @param check The operator we are checking
     * @return An int denoting the operational precedence of this operator. We return -1 if the operator is invalid
     */
    private static int getOperatorPrecedence(char check) {
        // We switch a character and return an index based on its operational precedence
        // The higher the int, the higher the precedence
        switch (check) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
}

