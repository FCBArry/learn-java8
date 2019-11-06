package java8.defaultmethod;

public class HuaweiPhone implements IPhoneAction, XiaomiAction
{
    @Override
    public void show()
    {

    }

    /**
     * 当实现多个接口时，需要重写相同的default方法
     */
    @Override
    public void boot()
    {
        System.out.println("HuaweiPhone boot");
//        // or
//        IPhoneAction.super.boot();
//        // or
//        XiaomiAction.super.boot();
    }

    @Override
    public void shutdown()
    {

    }

    public static void main(String[] args)
    {
        HuaweiPhone huaweiPhone = new HuaweiPhone();
        huaweiPhone.boot();
        IPhoneAction.run();
    }
}
