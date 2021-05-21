package pl.edu.agh.soa.lab;

import javax.xml.ws.BindingProvider;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Connector {
    private static StudentData studentData;


    private static void printAll(){
        for (Student x: studentData.findAll().getStudent()){
            System.out.println(x);
        }
    }

    private static void addStudent(Student student){
        studentData.add(student.name, student.index, student.subjects);
    }

    private static void removeStudent(Integer index){
        studentData.delete(index);
    }

    private static void printByIndex(Integer index){
        System.out.println(studentData.find(index));
    }

    private static void getImage(String path1, String path2){
        String encoded = studentData.sendImage(path1);
        byte[] decoded = Base64.getDecoder().decode(encoded);
        try (OutputStream stream = new FileOutputStream(path2)) {
            stream.write(decoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        StudentDataService dataService = new StudentDataService();
        studentData = dataService.getStudentDataPort();

        Map<String, Object> requestContext = ((BindingProvider) studentData).getRequestContext();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, "supervisor");
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, "123");

        String path1 = "F:\\AGH\\Sem_6\\SOA\\test\\jp2.png";
        String path2 = "F:\\AGH\\Sem_6\\SOA\\test\\jp1.png";

//        Student s1 = new Student("Nowy Student", 123456, List.of("SOA", "IO"));
//        addStudent(s1);
//        getImage(path1, path2);
//        printAll();
        printByIndex(123456);
//        removeStudent(333333);
        printAll();

    }
}
