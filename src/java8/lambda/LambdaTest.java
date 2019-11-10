package java8.lambda;

/**
 * Lambda表达式，也可称为闭包
 * 使用Lambda表达式可以使代码变的更加简洁紧凑
 *
 * (parameters) -> expression
 * 或
 * (parameters) ->{ statements; }
 *
 * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值
 * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号
 * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号
 * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值
 *
 * Lambda表达式只能引用标记了final的外层局部变量
 * 不能在lambda内部修改定义在域外的局部变量
 * Lambda表达式的局部变量可以不用声明为final，但是必须不可被后面的代码修改（即隐性的具有final的语义）
 * 在Lambda表达式当中不允许声明一个与局部变量同名的参数或者局部变量
 */
public class LambdaTest
{
    public static void main(String args[])
    {
        LambdaTest tester = new LambdaTest();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 用括号
        String test = "test";
        GreetingService greetService2 = (message) -> {
            // error
            // test = "no test";
            System.out.println(test);
            System.out.println("Hello " + message);
        };
        // error
        // test = "no test";

        greetService1.sayMessage("Google");
        greetService2.sayMessage("Github");
    }

    private int operate(int a, int b, MathOperation mathOperation)
    {
        return mathOperation.operation(a, b);
    }
}

@FunctionalInterface
interface MathOperation
{
    int operation(int a, int b);
}

@FunctionalInterface
interface GreetingService
{
    void sayMessage(String message);
}