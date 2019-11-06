package java8.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(RepeatedAnnoTests.class)
public @interface RepeatedAnnoTest
{
    String like() default "apple";
}

@Retention(RetentionPolicy.RUNTIME)
@interface RepeatedAnnoTests
{
    RepeatedAnnoTest[] value();
}

@RepeatedAnnoTest(like="orange")
@RepeatedAnnoTest(like="banana")
class AnnoTest
{

}