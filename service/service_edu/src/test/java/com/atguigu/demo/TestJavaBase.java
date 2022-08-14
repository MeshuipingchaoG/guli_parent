package com.atguigu.demo;

import org.junit.Test;


public class TestJavaBase {

    @Test
    public void test() {

        String s1 = "ABCD";

        String s2 = "1234";

        System.out.println(s1 + s2);
    }

    @Test
    public void Demo() {

        String s = new String("hello");

        if (s == "hello") {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    @Test
    public void test1() {
        int a[] = {4, 0, 2, 3, 1}, i, j, t;
        for(i = 1;i < 5;i++) {
            t = a[i];
            j = i - 1;
            while(j >= 0 && t > a[j]){
                a[j + 1] = a[j];
                --j;
            }
            a[j + 1] = t;
        }
    }


    @Test
    public void test2() {
        for (int i = 10; i < 20; i++) {
            System.out.println(i + " ");
        }
    }

    @Test
    public void test3() {
        System.out.println(100%3);
        System.out.println(100%3.0);
    }

    @Test
    public void test4() {
        int i = 5;
        int j = 10;
        System.out.println(i + ~j);
    }

    @Test
    public void test5() {
        int count = 0;
        int num = 0;
        for (int i = 0; i <= 100; i++) {
            num = num + i;
            count = count ++;
        }
        System.out.println(num);
        System.out.println(num * count);
    }

}
