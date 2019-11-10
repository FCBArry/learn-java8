package java8.sam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 函数式接口：是抽象方法只有一个的接口
 * @see FunctionalInterface
 * @see java.util.function
 *
 * 在java8中，满足下面任意一个条件的接口都是函数式接口：
 * 1、被@FunctionalInterface注释的接口，满足@FunctionalInterface注释的约束
 * 2、没有被@FunctionalInterface注释的接口，但是满足@FunctionalInterface注释的约束
 *
 * FunctionalInterface注释的约束：
 * 1、接口有且只能有个一个抽象方法，只有方法定义，没有方法体
 * 2、在接口中覆写Object类中的public方法，不算是函数式接口的方法
 *
 * java8之前就已经存在函数式接口，如Runnable，
 * 实现大多是用匿名内部类，java8之后也都加上了@FunctionalInterface来标识
 *
 * java8在util包中加入了大量的函数式接口，用来支持java的函数式编程
 *
 * 函数式接口实例的创建的方式：
 * 1、lambda表达式
 * 2、方法引用
 *
 * 例子：
 * Function<T,R> 接收一个T类型的参数，返回一个R类型的结果
 * Consumer<T> 接收一个T类型的参数，不返回值
 * Predicate<T> 接收一个T类型的参数，返回一个boolean类型的结果
 * Supplier<T> 返回一个T类型的结果
 */
public class SAMTest
{
    public static void main(String[] args)
    {
        // Supplier or MySupplier
        MySupplier<Integer> mySupplier = () -> new Integer(666);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 2; i++)
        {
            integers.add(i);
        }
        integers.add(mySupplier.get());

        // Consumer
        Consumer<String> consumer = str -> System.out.println(str);

        // Predicate
        Predicate<Integer> predicate = integer -> integer > 0;

        // Function
        Function<Integer, String> function = integer -> integer + "github";

        // test
        List<String> strings = integers.stream()
                .filter(predicate)
                .map(function)
                .collect(Collectors.toList());
        strings.forEach(consumer);
    }
}

/**
 * 自定义函数式接口
 */
@FunctionalInterface
interface MySupplier<T>
{
    T get();
}