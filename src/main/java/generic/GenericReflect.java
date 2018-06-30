package generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Vector;

/*
 * 如何通过反射获取泛型的实际类型参数
 */
public class GenericReflect {

    public static void main(String[] args) throws NoSuchMethodException {

        Method applyMethod = GenericReflect.class.getMethod("applyVector", Vector.class);
        Type[] types = applyMethod.getGenericParameterTypes();

        ParameterizedType pType = (ParameterizedType) types[0];
        System.out.println(pType.getRawType());
        System.out.println(pType.getActualTypeArguments()[0]);
    }

    public static void applyVector(Vector<Date> vector) {}
    // 这里定义为private方法出错！
}
