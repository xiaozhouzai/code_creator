package com.example.demo.common;

public class TestThread extends Thread{

    private String name;

    public TestThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for ( int i = 0; i < 50; i++ ) {
            System.out.println("hello " + getName());
        }

    }

    public static void main(String[] args) {
//        new TestThread("thread1").start();
//        new TestThread("thread2").start();
        String uri = "/schoolCode/14750/xboot/getInfo";
        String schoolCode = uri.substring(uri.indexOf("schoolCode/") + 11,uri.indexOf("schoolCode/") + 16);
        System.out.println(schoolCode);
        System.out.println(uri.indexOf("schoolCode/"));
    }
}
