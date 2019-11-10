package java8.stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流
 *
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/index.html
 * https://www.runoob.com/java/java8-streams.html
 *
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 * 基本操作：
 * Intermediate：
 * map(mapToInt,flatMap等)、filter、distinct、sorted、peek、limit、skip、parallel、sequential、unordered
 *
 * Terminal：
 * forEach、forEachOrdered、toArray、reduce、collect、min、max、count、
 * anyMatch、allMatch、noneMatch、findFirst、findAny、iterator
 *
 * Short-circuiting：
 * anyMatch、allMatch、noneMatch、findFirst、findAny、limit
 */
public class StreamTest
{
    public static void main(String[] args)
    {
        System.out.println("使用 Java 7: ");

        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表: " + strings);
        long count = getCountEmptyStringUsingJava7(strings);

        System.out.println("空字符数量为: " + count);
        count = getCountLength3UsingJava7(strings);

        System.out.println("字符串长度为 3 的数量为: " + count);

        // 删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings, ", ");
        System.out.println("合并字符串: " + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

        System.out.println("列表: " + integers);
        System.out.println("列表中最大的数 : " + getMax(integers));
        System.out.println("列表中最小的数 : " + getMin(integers));
        System.out.println("所有数之和 : " + getSum(integers));
        System.out.println("平均数 : " + getAverage(integers));
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();

        for (int i = 0; i < 10; i++)
        {
            System.out.println(random.nextInt());
        }

        System.out.println("使用 Java 8: ");
        System.out.println("列表: " + strings);

        count = strings.stream().filter(String::isEmpty).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(String::isEmpty).count();
        System.out.println("空字符串的数量为: " + count);

        // 流的构造
        // 1. Individual values
        Stream<String> stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        // 数值流的构造
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        // 流转换为其它数据结构，一个stream只可以使用一次
        // 1. Array
        String[] strArray1 = stream.toArray(String[]::new);
//        // 2. Collection
//        List<String> list1 = stream.collect(Collectors.toList());
//        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
//        Set set1 = stream.collect(Collectors.toSet());
//        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
//        // 3. String
//        String str = stream.collect(Collectors.joining()).toString();

        // flatMap
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap(Collection::stream);
        System.out.println(outputStream.count());

        // reduce
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10，有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10，无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

        // 自己生成流generate,iterate
        Random seed = new Random();
        Supplier<Integer> sRandom = seed::nextInt;
        Stream.generate(sRandom).limit(10).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(10).forEach(System.out::println);

        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));

        // ......
        // 用Collectors来进行各种reduction操作，groupingBy,partitioningBy等
        // ......
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings)
    {
        int count = 0;
        for (String string : strings)
        {
            if (string.isEmpty())
            {
                count++;
            }
        }

        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings)
    {
        int count = 0;
        for (String string : strings)
        {
            if (string.length() == 3)
            {
                count++;
            }
        }

        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings)
    {
        List<String> filteredList = new ArrayList<>();
        for (String string : strings)
        {
            if (!string.isEmpty())
            {
                filteredList.add(string);
            }
        }

        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings)
        {
            if (!string.isEmpty())
            {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }

        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<Integer> getSquares(List<Integer> numbers)
    {
        List<Integer> squaresList = new ArrayList<>();
        for (Integer number : numbers)
        {
            Integer square = number * number;
            if (!squaresList.contains(square))
            {
                squaresList.add(square);
            }
        }

        return squaresList;
    }

    private static int getMax(List<Integer> numbers)
    {
        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++)
        {
            Integer number = numbers.get(i);
            if (number > max)
            {
                max = number;
            }
        }

        return max;
    }

    private static int getMin(List<Integer> numbers)
    {
        int min = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++)
        {
            Integer number = numbers.get(i);
            if (number < min)
            {
                min = number;
            }
        }

        return min;
    }

    private static int getSum(List numbers)
    {
        int sum = (int) (numbers.get(0));
        for (int i = 1; i < numbers.size(); i++)
        {
            sum += (int) numbers.get(i);
        }

        return sum;
    }

    private static int getAverage(List<Integer> numbers)
    {
        return getSum(numbers) / numbers.size();
    }
}
