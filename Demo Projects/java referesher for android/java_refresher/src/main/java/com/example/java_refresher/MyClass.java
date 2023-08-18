package com.example.java_refresher;

import java.util.ArrayList;
import java.util.Locale;
class ClassEx
{
    public void method1()
    {
        System.out.println("I am method1");
    }
}

public class MyClass {
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        String name="My name is Ritesh Jaiswal";
        float f=65.3f;
        double d=98.4;
        System.out.println(name.toLowerCase());
        System.out.println(name);
        ClassEx t1 = new ClassEx();
        t1.method1();
        ArrayList p=new ArrayList();
        p.add(3);
        p.add("485");
        for(Object o : p)
        {
            System.out.println("Object is :"+ o);
        }


    }
}