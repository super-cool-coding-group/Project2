public class StackDriver {

    public static void main(String[] args) {
    }

    // Converts an infix expression to an equivalent postfix expression.
    public static String convertToPostFix(String infix) {
        LinkedStack<Character> operatorStack = new LinkedStack<Character>(); // operatorStack = a new empty stack
        String postfix = ""; // postfix = a new empty string

        for (int i = 0; i < infix.length(); ++i) // while (infix has characters left to parse)
        {
            char nextCharacter = infix.charAt(i); // nextCharacter = next nonblank character of infix
            if (nextCharacter == ' ')
                continue;

            switch (nextCharacter) { // switch (nextCharacter)
                case '^': // case '^' :
                {
                    operatorStack.push(nextCharacter); // operatorStack.push(nextCharacter)
                    break;
                }
                case '+': // case '+' : case '-' : case '*' : case '/' :
                case '-':
                case '*':
                case '/': {
                    while (!operatorStack.isEmpty() && // while (!operatorStack.isEmpty() and
                            getOperatorPrecedence(nextCharacter) <= getOperatorPrecedence(operatorStack.peek())) {
                        postfix += operatorStack.peek();// Append operatorStack.peek() to postfix
                        operatorStack.pop(); // operatorStack.pop()
                    }
                    operatorStack.push(nextCharacter); // operatorStack.push(nextCharacter)
                    break;
                }
                case '(': {
                    operatorStack.push(nextCharacter);
                    break;
                }
                case ')': // Stack is not empty if infix expression is valid
                {
                    
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '('){
                        postfix += operatorStack.pop();
                        operatorStack.pop();
                    }
                    // topOperator = operatorStack.pop()
                    // while (topOperator != '(')
                    // {
                    // Append topOperator to postfix
                    // topOperator = operatorStack.pop()
                    // }
                    // break
                }
                default: {
                    if (Character.isLetterOrDigit(nextCharacter)) // case variable:
                    {
                        postfix += nextCharacter; // Append nextCharacter to postfix
                        break;
                    }
                    break;
                }
            }

        }

        while (!operatorStack.isEmpty()) // while (!operatorStack.isEmpty())
        {
            char topOperator = operatorStack.pop(); // topOperator = operatorStack.pop()
            postfix += topOperator; // Append topOperator to postfix
        }

        return postfix;
    }

    public static int getOperatorPrecedence(char ch) {
        switch (ch) {
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
