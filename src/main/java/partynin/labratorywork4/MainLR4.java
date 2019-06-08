package partynin.labratorywork4;

import partynin.labratorywork2.DuplicateSubjectException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainLR4 {

    public static void main(String[] args) throws DuplicateSubjectException, CloneNotSupportedException {


//
//        Student partynin = new Student("Partynin", 2);
//        partynin.setMarksElement(5,0);
//        partynin.setMarksElement(5,1);
//        partynin.setSubjectsElement("OOP", 0);
//        partynin.setSubjectsElement("Database", 1);
//        System.out.println("Original " + partynin.getLastName());
//        System.out.println(partynin);
//        System.out.println("Hashcode: " + partynin.hashCode());
//
//        Student partyninClone = (Student) partynin.clone();
//        System.out.println("---------------------------------------------------");
//        System.out.println("partyninClone = partynin is " + partyninClone.equals(partynin));
//        System.out.println("---------------------------------------------------");
//        System.out.println("Clone " + partynin.getLastName());
//        System.out.println(partyninClone);
//        System.out.println("Hashcode: " + partyninClone.hashCode());
//
//        partyninClone.setMarksElement(4,0);
//
//        System.out.println("---------------------------------------------------");
//        System.out.println("partyninClone = partynin is " + partyninClone.equals(partynin));
//        System.out.println("---------------------------------------------------");
//
//        System.out.println();

        Schoolboy partynin = new Schoolboy("Partynin", 2);
        partynin.setMarksElement(5,0);
        partynin.setMarksElement(5,1);
        partynin.setSubjectsElement("OOP", 0);
        partynin.setSubjectsElement("Database", 1);
        System.out.println("Original " + partynin.getLastName());
        System.out.println(partynin);
        System.out.println("Hashcode: " + partynin.hashCode());

        Schoolboy partyninClone = (Schoolboy) partynin.clone();
        System.out.println("---------------------------------------------------");
        System.out.println("partyninClone = partynin is " + partyninClone.equals(partynin));
        System.out.println("---------------------------------------------------");
        System.out.println("Clone " + partynin.getLastName());
        System.out.println(partyninClone);
        System.out.println("Hashcode: " + partyninClone.hashCode());

        partyninClone.setMarksElement(4,0);

        System.out.println("---------------------------------------------------");
        System.out.println("partyninClone = partynin is " + partyninClone.equals(partynin));
        System.out.println("---------------------------------------------------");
    }
}
