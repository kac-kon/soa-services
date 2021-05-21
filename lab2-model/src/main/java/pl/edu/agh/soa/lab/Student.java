package pl.edu.agh.soa.lab;

import java.util.List;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String toString(){
        return this.getName() +
                "\n" +
                this.getIndex() +
                "\n" +
                this.getSubjects() +
                "\n";
    }




}
