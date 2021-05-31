package annotation;

/**
 * 注解测试
 * 当给一个类或者方法添加注解时，可以通过反射获取该类上的注解对象，进而获取该注解信息
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Person person = Person.builder().build();
        Class clazz = person.getClass();
        //判断person对象上是否有info注解
        if (clazz.isAnnotationPresent(Info.class)) {
            System.out.println("Person类上配置了Info注解！");
            //获取该对象上Info类型的注解
            Info infoAnno = (Info) clazz.getAnnotation(Info.class);
            System.out.println("person.name :" + infoAnno.value() + ",person.isDelete:" + infoAnno.isDelete());
        } else {
            System.out.println("Person类上没有配置Info注解！");
        }
    }
}
