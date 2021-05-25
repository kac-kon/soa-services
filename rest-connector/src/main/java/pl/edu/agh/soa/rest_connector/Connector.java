package pl.edu.agh.soa.rest_connector;


import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import pl.edu.agh.soa.model.Student;

import javax.swing.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.awt.*;
import java.util.List;

public class Connector {
    private Client cl;
    private ResteasyClient client;
    private static String url = "http://localhost:8080/rest-api/StudentData/";

    Connector() {
        this.cl = new Client();
        this.client = cl.getClient();
    }

    Connector(String username, String password){
        this.cl = new Client(username, password);
        client = cl.getClient();
    }

    private List<Student> getAllStudents(){
        List<Student> result = null;
        ResteasyWebTarget target = this.client.target(url);
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

    private Student getStudentByIndex(Integer index){
        Student result = null;
        ResteasyWebTarget target = this.client.target(url + "/" + index.toString());
        Response response = target.request().get();
        if (response.getStatus() != 200) {
            System.out.println(response.getStatus());
        } else {
            result = response.readEntity(new GenericType<Student>() {});
        }
        response.close();
        return result;
    }

    private void addStudent(Student student){
        ResteasyWebTarget target = this.client.target(url);
        Response response = target
                .request()
                .header("Authorization", this.cl.getToken())
                .post(Entity.entity(student, "application/json"));
        if (response.getStatus() != 201) {
        System.out.println(response.getStatus());
        }
    }

    private void delStudent(Integer index){
        ResteasyWebTarget target = this.client.target(url + "/" + index);
        Response response = target
                .request()
                .header("Authorization", this.cl.getToken())
                .delete();
        if (response.getStatus() != 200) {
            System.out.println(response.getStatus());
        }
    }

    private void updateStudent(Integer index, Student student){
        ResteasyWebTarget target = this.client.target(url + "/" + index);
        Response response = target
                .request()
                .header("Authorization", this.cl.getToken())
                .put(Entity.entity(student, "application/json"));
        if (response.getStatus() != 202) {
            System.out.println(response.getStatus());
        }
    }

    private void displayImage() {
        byte[] result = null;
        ResteasyWebTarget target = this.client.target(url + "/image");
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
        Connector connector = new Connector("123", "123");
        Student sb = new Student("Bond", 518, List.of("sdsa"));
        Student s1 = new Student("Aa", 111, List.of("SOA", "SS"));
        Student s2 = new Student("Bb", 111, List.of("SOA", "SS"));
        connector.delStudent(111);
        connector.delStudent(101);
        connector.addStudent(s1);
        System.out.println(connector.getStudentByIndex(111));
        connector.updateStudent(111, s2);
        System.out.println(connector.getStudentByIndex(111));
        System.out.println(connector.getAllStudents());
        connector.displayImage();
    }
}
