#String

1）基本

java.lang.String类的实例用于封装一个字符序列，字符串对象为“不变对象”，一旦对字符串进行修改操作，会创建新的对象。Java中允许我们将一个字面量赋值给字符串引用类型变量，处于性能的考虑，Java对字面量产生的字符串进行了缓存，将他们缓存在字符串的常量池中，对于重复出现的字面量赋值，JVM会先查找常量池中是否存在这个字符串，悠久直接引用，减少字符串对象的创建，节省内存资源。

String s = new String("abc");//创建了2个对象，内容都是"abc"：一个在字符串常量池，一个在堆里。s只是对象的引用，下同String s1 = "abc";//s1指向字符串常量池里内容为"abc"String s2 = new String("abc");//创建了1个对象，在堆里，内容指向堆里的"abc"String s3 = "abc";//s3指向了字符串常量池里内容为"abc"，跟s1一样都指向池里的相同内容System.out.println(s == s1);//falseSystem.out.println(s == s2);//falseSystem.out.println(s1 == s2);//falseSystem.out.println(s1 == s3);//trueSystem.out.println();String o = "o";String k = "k";String ok = "ok";System.out.println(ok == "o" + k);//false, "o"+k会在堆里新生成一个对象System.out.println(ok == "o" + "k");//true, 都是在常量池里，相加也是返回池里的"ok"

2）String的封装类

StringBuilder和StringBuffer都是String的封装类，对于字符串的操作建议用这个

StringUtils.countMatch(String str, String subStr);//统计subStr在str里出现的次数

StringBuilder：线程非安全的

StringBuffer：线程安全的

String test = "Today" + " is " + " sunny";String test2 = "Today is sunny";String t1 = "Today";String t2 = " is";String t3 = " sunny";System.out.println(test == test2);//falseSystem.out.println(test == (t1+t2+t3));//false

```note
ps: 1、字符串进行了缓存，在字符串常量池
    2、创建对象实在堆里
```
