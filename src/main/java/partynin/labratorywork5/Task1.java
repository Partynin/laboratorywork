package partynin.labratorywork5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Task1 {
    public static void main(String[] args) {

        try {
            Class<?> classStudent = Class.forName("partynin.labratorywork4.Student");

            Constructor<?> constructor = classStudent.getConstructor(String.class, int.class);
            Object instance = constructor.newInstance("Partynin", 2);

            Method m = classStudent.getMethod("setMarksElement", int.class, int.class);
            Object res = m.invoke(instance, 5, 1);

            System.out.println(instance);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
