package java8.string;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * string join
 * @see StringJoiner
 * @see String
 */
public class StringTest
{
    public static void main(String[] args)
    {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add("a");
        joiner.add("b");
        joiner.add("c");
        String joined = joiner.toString();
        System.out.println(joined);

        // add() calls can be chained
        joined = new StringJoiner(",")
                .add("d")
                .add("e")
                .add("f")
                .toString();
        System.out.println(joined);

        // join(CharSequence delimiter, CharSequence... elements)
        joined = String.join("/", "2019", "11", "06" );
        System.out.println(joined);

        // join(CharSequence delimiter, Iterable<? extends CharSequence> elements)
        List<String> list = Arrays.asList("g", "h", "i");
        joined = String.join(",", list);
        System.out.println(joined);

        // stream join
        List<Name> nameList = Arrays.asList(
                new Name("John", "Smith"),
                new Name("Anna", "Martinez"),
                new Name("Paul", "Watson ")
        );
        String joinedFirstNames = nameList.stream()
                .map(Name::getFirstName)
                .collect(Collectors.joining(","));
        System.out.println(joinedFirstNames);
    }
}

class Name
{
    private String firstName;

    private String lastName;

    public Name(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }
}