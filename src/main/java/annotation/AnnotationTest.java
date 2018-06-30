package annotation;

import java.util.Arrays;

/*
 * 测试AnnotationUse是否使用了注解MyAnnotation
 */
public class AnnotationTest {

    public static void main(String[] args) {

        /*
         * MyAnnotation是一个类，这个类的实例对象是通过反射得到的，这个类是如何被创建的?
         * 一旦某个类上使用了@MyAnnotation，那么这个MyAnnotation类的实例对象就被创建了.
         *
         * 假设很多人考驾照，教练会在每个学员上贴绿牌子、黄牌子。绿牌子表示礼送的比较多的，黄牌子表示送礼少的，不贴牌子表示不送礼的.
         * 教练在考核时，看到有牌子的就表示送过礼的，优先让有牌子的先过。此时这个牌子就是一个注解.
         * 一个牌子就是一个注解的实例对象，把牌子拿掉（去掉注解）注解对象就不存在了.
         *
         */
        if (AnnotationUse.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation myAnnotation = AnnotationUse.class.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation); // 打印MyAnnotation对象
            System.out.println(myAnnotation.annotationType());

            // 用反射方法获取到注解对象的实例后，通过该对象调用属性对应的方法
            System.out.println(myAnnotation.entryStatus());
            System.out.println(Arrays.toString(myAnnotation.expectedStatus()));
        }
    }
}
