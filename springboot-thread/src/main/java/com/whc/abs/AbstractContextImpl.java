package com.whc.abs;

/**
 * @author Administrator
 * @date 2019/4/11
 */
public class AbstractContextImpl extends AbstractContext {

    /**
     * 可以在实现类中覆盖抽象类已有的方法
     *
     * @return
     */
    @Override
    public Object getName() {
        return "guest";
    }

    /**
     * 接口中的方法需要实现
     *
     * @return
     */
    @Override
    public Object getAge() {
        return "25";
    }

    /**
     * 接口中的方法需要实现
     */
    @Override
    public void set() {

    }

    /**
     * 接口中的方法需要实现
     *
     * @return
     */
    @Override
    Object getEmail() {
        return null;
    }

    /**
     * 接口中的方法需要实现
     *
     * @return
     */
    @Override
    public Object getSrc() {
        return null;
    }

    /**
     * 接口中的方法需要实现
     *
     * @return
     */
    @Override
    public Object getTar() {
        return null;
    }

    public static void main(String[] args) {
        AbstractContext context = new AbstractContextImpl();
        System.out.println(context.name);//访问的是AbstractContext的name成员，它覆盖了接口类的静态成员name
        System.out.println(context.age);//访问的是接口定义静态变量age
        System.out.println(context.getAccount());//访问的是抽象类自定义实现的方法
        System.out.println(context.getName());//访问的是本实现类的方法，因为实现类对抽象类的方法进行了覆盖
        System.out.println(context.getAge());//访问的是本实现类的方法，实现了Context接口的方法
    }

}
