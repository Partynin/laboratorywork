package partynin.labratorywork5;

import java.io.Serializable;
import java.util.*;

/**
 * Добавить новый класс Collegeboy, использующий для хранения предметов и оценок коллекцию HashMap,
 * причем в параметризованной форме. Фамилия хранится в поле типа String. В классе должны следующие быть методы:
 * метод для получения фамилии,
 * метод для модификации фамилии,
 * метод, возвращающий список всех предметов,
 * метод, возвращающий список всех оценок,
 * метод, возвращающий оценку по заданному предмету,
 * метод добавления предмета и оценки в коллекцию,
 * метод для получения размера коллекции,
 * метод, выводящий на экран список всех предметов,
 * метод, выводящий на экран список всех оценок.
 * Последние два метода должны использовать for-each.
 */

public class Collegeboy implements Serializable, Cloneable {
    private HashMap<String, Integer> registersMap;
    private String lastName;

    public Collegeboy(String lastName) {
        this.lastName = lastName;
        registersMap = new HashMap<>();
    }

    //  метод для получения фамилии
    public String getLastName() {
        return lastName;
    }

    // метод для модификации фамилии
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // метод, возвращающий список всех предметов
    public List<String> getListSubjects() {
        List<String> listSubjects = new ArrayList<>();

        Iterator<Map.Entry<String, Integer>> iterator = registersMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();
            String subject = pair.getKey();
            listSubjects.add(subject);
        }

        return listSubjects;
    }

    // метод, возвращающий список всех оценок
    public List<Integer> getListMarks() {
        List<Integer> listMarks = new ArrayList<>();

        Iterator<Map.Entry<String, Integer>> iterator = registersMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();
            Integer mark = pair.getValue();
            listMarks.add(mark);
        }

        return listMarks;
    }

    // метод, возвращающий оценку по заданному предмету
    public int getMarkForElement(String subject) {

        int mark = registersMap.get(subject);

        return mark;
    }

    // метод добавления предмета и оценки в коллекцию
    public void setSubjectAndMark(String subject, int mark) {
        registersMap.put(subject, mark);
    }

    // метод для получения размера коллекции
    public int getLength() {
        int sizeMap = registersMap.size();

        return sizeMap;
    }

    // метод, выводящий на экран список всех предметов
    public void printSubjects() {
        for (Map.Entry<String, Integer> pair : registersMap.entrySet()) {
            String subject = pair.getKey();
            System.out.print(subject + " ");
        }
    }

    // метод, выводящий на экран список всех оценок
    public void printMarks() {
        for (Map.Entry<String, Integer> pair : registersMap.entrySet()) {
            Integer mark = pair.getValue();
            System.out.print(mark + " ");
        }
    }
}
