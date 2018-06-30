package generic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/*
 * 自定义泛型方法
 */
public class GenericMethod {

    public static void main(String[] args) {
        swap(new String[]{"abc", "123", "xdp"}, 1, 2);// 调用自定义泛型方法，传入的实际参数必须是引用类型的数组

        printArray(new Integer[]{1, 2, 3}); // 可以传入Integer类型的数组，因为Integer类型的数组是属于引用类型的
        // printArray(new int[]{1, 2, 3});// 不能传入非引用类型的数组作为泛型方法的实际参数
    }

    /*
     * 定义打印任意引用数组类型的方法
     */
    private static <T> void printArray(T[] a) {
        System.out.println(Arrays.toString(a));
    }

    /*
     * 定义一个泛型方法来交换数组中指定索引位置的两个元素 这里传入的数组可以是任意类型的数组
     * 传入泛型方法的实际参数类型必须是引用类型的数组，不能是基本类型的数组
     */
    private static <T> void swap(T[] a, int i, int j) {
        printArray(a);
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        printArray(a);
    }

    /*
     * 定义有extends限定符，并且具有多个上边界的泛型方法，各个边界使用&符号分隔
     */
    public <T extends Serializable & Cloneable> void method() {

    }

    /*
     * 定义一个泛型方法，自动将Object类型对象转换为其他类型
     */
    public static <T> T autoConvert(Object object) {
        return (T) object;
    }

    /*
     * 定义一个泛型方法，可以将任意类型的数组中的所有元素填充为相应类型的某个对象
     */
    public static <T> void fillArray(T[] array, T obj) {
        for (int i = 0; i < array.length; i++) {
            array[i] = obj;
        }
        printArray(array);
    }

    /*
     * 采用自定泛型方法的方式打印出任意参数化类型的集合中的所有内容
     */
    public static <T> void printCollection(Collection<T> c) {
        System.out.println(c.size());
        for (Object object : c) {
            System.out.println(object);
        }
    }

    /*
     * 定义一个泛型方法，把任意参数类型的集合中的数据安全地复制到相应类型的数组中
     */
    public static <T> void collectionCopyToArray(Collection<T> c, T[] array) {
        Iterator<T> iterator = c.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            array[index] = iterator.next();
            index++;
        }
        printArray(array);
    }

    /*
     * 定义一个泛型方法，把任意参数类型的一个数组中的数据安全地复制到相应类型的另一个数组中去
     */
    public static <T> void copyArray(T[] source, T[] target) {
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
        printArray(target);
    }
}
