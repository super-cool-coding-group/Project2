public class CalculatorTest {
    public static void main(String[] args){
        String inf = "a*b/(c-a)+d*e";
        System.out.println(Calculator.convertToPostfix(inf));

        int res = Calculator.evaluatePostfix("23*42-/56*+");
        System.out.println(res);
    }
}
