package generic;

import java.util.Set;

/*
 * 定义泛型类，T代表实际操作的类型。指明了操作类型后，定义的CRUD方法都是针对于指定的类型
 */
public class GenericDAO<T> {

    private T field; // 泛型类型的成员变量

    public <T> void add(T x) {}

    public <T> T findById(int id) { return null; }

    public void delete(T x) {}

    public void delete(int id) {}

    public void update(T x) {}

    // public static void update2(T obj) { } // 定义会报错，静态方法不允许使用泛型参数
    public static<T> void update2(T obj) {} // 这样定义就可以，此时的静态方法所针对的类型和GenericDAO<T>中指定的类型是不同的类型

    public Set<T> findByCondition(String where) { return null; }

    public static void main(String[] args) {
        GenericDAO<FieldType> dao = new GenericDAO<FieldType>(); // 这里指定泛型类的操作类型是ReflectionField
        dao.add(new FieldType(1, 3));
        FieldType rf = dao.findById(1);
        System.out.println(rf);
    }
}
