package java8.methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 * 方法引用是为了进一步简化lambda表达式
 *
 * 构造器引用：Class::new，或者更一般的Class<T>::new
 *
 * 静态方法引用：Class::static_method
 *
 * 特定类的任意对象的方法引用：Class::method
 *
 * 特定对象的方法引用：instance::method
 *
 * 超类上的实例方法引用：supper::method
 *
 * 数组构造方法引用：TypeName[]::new
 *
 */
public class MethodReferenceTest
{
    public static void main(String[] args)
    {
        // Supplier
        Supplier<Player> supplier1 = Player::new;
        Supplier<Player> supplier2 = () -> new Player("666");

        // 构造器引用
        List<Player> players = new ArrayList<>();
        players.add(supplier1.get());
        for (int i = 1; i < 2; i++)
        {
            players.add(new Player(String.valueOf(i)));
        }
        players.add(supplier2.get());

        // 超类上的实例方法引用
        players.forEach(Living::printAge);

        // 静态方法引用
        players.forEach(Player::modifyName);

        // 特定类的任意对象的方法引用
        players.forEach(Player::printMyName);

        // 特定对象的方法引用
        Player p = players.get(0);
        players.forEach(p::printOtherName);

        // 数组构造方法引用
        Function<Integer, int[]> function = int[]::new;
        System.out.println(Arrays.toString(function.apply(6)));
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i < 5; i++)
        {
            integers.add(i);
        }
        List<int[]> result = integers.stream().map(function).collect(Collectors.toList());
        System.out.println(result.size());
    }
}
class Living
{
    public int age = 17;

    public void printAge()
    {
        System.out.println(17);
    }
}

class Player extends Living
{
    private String name;

    public Player()
    {
        name = "0";
    }

    public Player(String name)
    {
        this.name = name;
    }

    public static void modifyName(Player player)
    {
        player.name = "shabi gitlab";
    }

    public void printMyName()
    {
        System.out.println("my name：" + name);
    }

    public void printOtherName(Player player)
    {
        System.out.println("my name：" + name + " other name：" + player.name);
    }
}