package GroupOfStudents;

import java.util.*;

public class Student {

    private final String name;    // имя
    private final Map<String, Integer> subjects;


    public Student(String name, Map<String, Integer> subjectsAndMarks)  {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must be not empty");
        }
        for (String key : subjectsAndMarks.keySet()) {
            if (key == null || key.isEmpty()) throw new IllegalArgumentException("Subject is empty!");
        }
        subjects = subjectsAndMarks;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSubjects() {
        return new HashMap<>(this.subjects);
    }

    public void putSubject(String subject) {
        this.subjects.put(subject, null);
    }

    public void deleteSubject(String subject) {
        this.subjects.remove(subject);
    }

    public void putMark(String subject, Integer mark) {
        this.subjects.put(subject, mark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(subjects, student.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subjects);
    }
}
