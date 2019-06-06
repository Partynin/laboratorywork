package partynin.labratorywork5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainForLR5 {

    //partynin.labratorywork4.Student setMarksElement 5 1
    public static void main(String[] args) {

        if (args.length == 4) try {
                Class<?> classStudent = Class.forName(args[0]);

                Constructor<?> constructorStudent = classStudent.getConstructor(String.class, int.class);
                Object studentObject = constructorStudent.newInstance("Partynin", 2);

                Method studentMethod = classStudent.getMethod(args[1], int.class, int.class);
                Object res = studentMethod.invoke(studentObject, Integer.parseInt(args[2]), Integer.parseInt(args[3]));

            System.out.println(studentObject);

            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден");
            } catch (NoSuchMethodException e) {
                System.out.println("Метод не найден");
            } catch (IllegalAccessException e) {
                System.out.println("Метод недоступен");
            } catch (InvocationTargetException e) {
                System.out.println("При вызове возникло исключение");
            } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}