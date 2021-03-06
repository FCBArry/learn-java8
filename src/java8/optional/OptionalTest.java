package java8.optional;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Optional类的引入很好的解决空指针异常
 * @see java.util.Optional
 *
 * 可以与函数表达式联合使用
 */
public class OptionalTest
{
    public static void main(String args[])
    {
        OptionalTest optionalTest = new OptionalTest();
        Integer value1 = null;
        Integer value2 = 10;

        // Optional.ofNullable - 允许传递为null参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是null，抛出异常NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(optionalTest.sum(a,b));

        Consumer<Integer> consumer = x -> System.out.println(Math.sqrt(x));
        b.ifPresent(consumer);
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b)
    {
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(0);

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}
