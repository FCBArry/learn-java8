package java8.defaultmethod;

/**
 * 行为接口
 * 可以有多个默认方法
 */
public interface IPhoneAction
{
    void show();

    /**
     * 开机
     */
    default void boot()
    {
        System.out.println("IPhoneAction boot");
    }

    /**
     * 关机
     */
    default void shutdown()
    {
        System.out.println("IPhoneAction shutdown");
    }

    /**
     * 静态默认方法
     */
    static void run()
    {
        System.out.println("IPhoneAction run");
    }
}
