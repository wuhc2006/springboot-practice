package com.whc.cal.test1;

/**
 * @author Administrator
 * @date 2019/8/28 19:32
 */
public class LongAtomTest implements Runnable{
    private static long field = 0;
    private volatile long value;

    private long getValue(){
        return value;
    }

    private void setValue(long value){
        this.value = value;
    }

    public LongAtomTest(long value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongAtomTest{" +
                "value=" + value +
                '}';
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10000000){
            LongAtomTest.field = this.getValue();
            i++;
            long temp = LongAtomTest.field;
            if (temp != 1L && temp != -1L){
                System.out.println("出现错误结果");
                System.exit(0);
            }
        }
        System.out.println("运行正确!值为：" + LongAtomTest.field + "线程为:" + Thread.currentThread().getName() + "对象为：" +this.toString());
    }

    public static void main(String[] args) throws InterruptedException {
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch + "-bit");
        LongAtomTest t1 = new LongAtomTest(1);
        LongAtomTest t2 = new LongAtomTest(-1);
        Thread thread1 = new Thread(t1);
        thread1.setName("t1");
        Thread thread2 = new Thread(t2);
        thread2.setName("t2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
