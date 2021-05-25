package pl.edu.agh.soa.rest_connector;


import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import pl.edu.agh.soa.model.Student;

import javax.swing.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Connector {
    private static ResteasyClient client = new ResteasyClientBuilder().build();
    private static String url = "http://localhost:8080/rest-api/StudentData/";

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

    private static void delStudent(Integer index){
        ResteasyWebTarget target = client.target(url + "/" + index);
        Response response = target.request().delete();
        if (response.getStatus() != 200) {
            System.out.println(response.getStatus());
        }
    }

    private static void updateStudent(Integer index, Student student){
        ResteasyWebTarget target = client.target(url + "/" + index);
        Response response = target.request().put(Entity.entity(student, "application/json"));
        if (response.getStatus() != 202) {
            System.out.println(response.getStatus());
        }
    }

    private static void displayImage() {
        byte[] result = null;
        ResteasyWebTarget target = client.target(url + "/image");
        Response response = target.request().get();
        if (response.getStatus() == 200) {
            result = response.readEntity(byte[].class);
        }
        response.close();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(505, 372);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(result));
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args){
//        List<Student> list = getAllStudents();
//        Student st = getStudentByIndex(123);
//        Student s = new Student("111", 111, Collections.singletonList("sdsa"));
//        System.out.println(st);
//        addStudent(s);
//        System.out.println(getStudentByIndex(111));
//        Student s = new Student("111", 111, Collections.singletonList("sdsa"));
//        addStudent(s);
//        Student sa = new Student("James", 123, Collections.singletonList("sdsa"));
//        addStudent(sa);
        Student sb = new Student("Bond", 518, List.of("sdsa"));
        Student s1 = new Student("Aa", 111, List.of("SOA", "SS"));
        Student s2 = new Student("Bb", 111, List.of("SOA", "SS"));
        addStudent(s1);
        System.out.println(getStudentByIndex(111));
        updateStudent(111, s2);
        System.out.println(getStudentByIndex(111));
        displayImage();
//        System.out.println(getAllStudents());
//        System.out.println(getStudentByIndex(518));
//        delStudent(518);
//        System.out.println(getStudentByIndex(518));
    }
}
