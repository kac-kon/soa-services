package pl.edu.agh.soa.lab;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import pl.edu.agh.soa.model.Student;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.util.Collections;
import java.util.List;

public class Connector {
    private static ResteasyClient client = new ResteasyClientBuilder().build();
    private static String url = "http://localhost:8080/rest-api/StudentData";

    private static List<Student> getAllStudents(){
        List<Student> result = null;
        ResteasyWebTarget target = client.target(url);
        try {
            Response response = target.request().get();
            if (response.getStatus() != 200) {
                System.out.println(response.getStatus());
            } else {
                result = response.readEntity(new GenericType<List<Student>>() {});
            }
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Student getStudentByIndex(Integer index){
        Student result = null;
        ResteasyWebTarget target = client.target(url + "/" + index.toString());
        Response response = target.request().get();
        if (response.getStatus() != 200) {
            System.out.println(response.getStatus());
        } else {
            result = response.readEntity(new GenericType<Student>() {});
        }
        response.close();
        return result;
    }

    private static void addStudent(Student student){
        ResteasyWebTarget target = client.target(url);
        Response response = target.request().post(Entity.entity(student, "application/json"));
        if (response.getStatus() != 201) {
            System.out.println(response.getStatus());
        }
    }


    public static void main(String[] args){
        List<Student> list = getAllStudents();
        Student st = getStudentByIndex(123);
        Student s = new Student("111", 111, Collections.singletonList("sdsa"));
//        System.out.println(st);
        addStudent(s);
        System.out.println(getStudentByIndex(111));
    }
}
