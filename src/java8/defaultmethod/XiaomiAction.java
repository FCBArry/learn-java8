package java8.defaultmethod;

/**
 * 行为接口
 */
public interface XiaomiAction
{
    /**
     * 开机
     */
    default void boot()
    {
        System.out.println("XiaomiAction boot");
    }

    /**
     * 关机
     */
    default void shutdown()
    {
        System.out.println("XiaomiAction shutdown");
    }
}
