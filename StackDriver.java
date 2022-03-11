/**
 * This is the StackDriver class. We use this class to demo some possible usage of Stacks, such as
 * the conversion of a infix string to a postfix string.
 * 
 * @author Pierlorenzo Peruzzo
 * @version 2.0
 */
public class StackDriver {

    public static void main(String[] args) {
        String test = "a*b/(c-a)+d*e";
        System.out.println(convertToPostFix(test));
    }

    /**
     *  Converts a infix string to a postfix string using a LinkedStack
     * 
     * @param infix a string with a infix expression
     * @return the postfix string
     */
    public static String convertToPostFix(String infix) {
        // Initialize an empty LinkedStack to store the operators in the infix string
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        // Initialize an empty postfix string, we will use this to return the final value
        String postfix = "";

        // We loop through all of the characters in the infix string
        for (int i = 0; i < infix.length(); ++i)
        {
            // Get the current character at the looping index (i)
            char c = infix.charAt(i);
            // If we are dealing with a balck character, we skip processing and go to the next iteration
            if (c == ' ')
                continue;

            // We switch the character c to understand how we should process it
            switch (c) {
                case '^':
                {
                    // Add '^' in the stack
                    operatorStack.push(c);
                    break;
                }
                case '+':
                case '-':
                case '*':
                case '/': {

                    // We pop the operators from the stack and we append them to the postifx output string.
                    // We do this until: 1) the stack is empty or 2) the top entry has a lower precendence than the
                    // new operator onto the stack
                    while (!operatorStack.isEmpty() && 
                    (getOperatorPrecedence(c) <= getOperatorPrecedence(operatorStack.peek()))) {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    // Finally, we push the new operator in the stack
                    operatorStack.push(c);
                    break;
                }
                case '(': {
                    // We push the '(' in the stack
                    operatorStack.push(c);
                    break;
                }
                case ')':
                {
                    // We pop the operators from the stack and we append them to the oputput until we pop and '('
                    // We also discard both parenthesis
                    var topOperator = operatorStack.pop();
                    while (topOperator != '('){
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                    break;
                }
                default: {
                    // In the default case we check to see if out character c is a variable (either a letter or a digit)
                    if (Character.isLetterOrDigit(c))
                    {
                        // If yes, we append c to the postfix string
                        postfix += c;
                        break;
                    }
                    break;
                }
            }            
        }

        // Finally we remove all of the operators in the stack and we add them to the postfix string
        while (!operatorStack.isEmpty())
        {
            char topOperator = operatorStack.pop();
            postfix += topOperator;
        }

        // Return the final converted postifx string
        return postfix;
    }

    /**
     *  Assign a number precedence for each operators
     * 
     * @param c the operator to be checked
     * @return an integer representing the operational precedence of this operator, -1 if the operator is invalid
     */
    public static int getOperatorPrecedence(char c) {
        // We switch a character and return an index based on its operational precedence
        // The higher the int, the higher the precedence
        switch (c) {
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
