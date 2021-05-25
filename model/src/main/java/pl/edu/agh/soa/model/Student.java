package pl.edu.agh.soa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"name", "index", "subjects"})
public class Student {
    String name;
    Integer index;
    List<String> subjects;

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }

    public List<String> getSubjects() {
        return subjects;
    }


    public Student(String name, Integer index, List<String> subjects) {
        this.name = name;
        this.index = index;
        this.subjects = subjects;
    }

    public Student() {
    }

    public List<Student> generate(){
        List<Student> list = new ArrayList<>();
        list.add(new Student("Adam", 101, List.of("SOA", "SP")));
        list.add(new Student("Bartek", 102, List.of("SOA", "SP")));
        list.add(new Student("Cezary", 103, List.of("SOA", "SP")));
        list.add(new Student("Dominik", 104, List.of("SOA", "SP")));
        list.add(new Student("Edward", 105, List.of("SOA", "SP")));

        return list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subjects")
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String toString(){
        return this.getName() +
                "\n" +
                this.getIndex() +
                "\n[" +
                this.getSubjects() +
                "]\n";
    }




}
