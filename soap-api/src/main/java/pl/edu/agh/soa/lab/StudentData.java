package pl.edu.agh.soa.lab;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.jboss.ws.api.annotation.WebContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Stateless
@WebService
@WebContext(authMethod = "BASIC")
@DeclareRoles({"guest", "other", "supervisor"})
public class StudentData {
    List<Student> students = new ArrayList<>();

    @WebMethod
    @RolesAllowed({"other", "supervisor"})
    public Student Find(@WebParam(name = "index") Integer number){

        for (Student x: students){
            if (number.equals(x.index)){
                return x;
            }
        }
        return null;
    }

    @WebMethod
    @XmlElement(name = "student")
    @XmlElementWrapper(name = "students")
    @RolesAllowed({"other", "supervisor"})
    public List<Student> FindAll(){
        return students;
    }


    @WebMethod
    @RolesAllowed("supervisor")
    public void Add(@WebParam(name = "name") String name,
                    @WebParam(name = "index") Integer index,
                    @WebParam(name = "subjects") List<String> subjects){
        students.add(new Student(name, index, subjects));
    }

    @WebMethod
    @RolesAllowed("supervisor")
    public void Delete(@WebParam(name = "index") Integer number){
        students.removeIf(x -> number.equals(x.index));
    }

    @WebMethod
    @WebResult
    @RolesAllowed({"other", "supervisor"})
    public String SendImage(@WebParam(name="path") String path) {

        File file = new File(path);
        String encodedFile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            System.out.println((int)file.length());
            System.out.println(file.length());
            fileInputStreamReader.read(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            encodedFile = "File not found";
        } catch (IOException e) {
            encodedFile = "Transfer error";
        }
        return encodedFile;
    }
}
