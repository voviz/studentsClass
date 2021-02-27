package classstudent;

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
        return subjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        if (((Student) obj).name.isEmpty() || this.name.isEmpty()) return false;
        if (!((Student) obj).name.equals(this.name)) return false;
        if (((Student) obj).subjects == null && this.subjects == null) return true;
        else if (
                ((Student) obj).subjects == null && this.subjects != null ||
                        ((Student) obj).subjects != null && this.subjects == null
        ) return false;
        else return ((Student) obj).subjects.equals(this.subjects);
    }

    @Override
    public int hashCode() {
        return subjects.hashCode() + name.hashCode();
    }
}
