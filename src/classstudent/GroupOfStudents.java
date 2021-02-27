package classstudent;

import java.util.ArrayList;
import java.util.List;

public class GroupOfStudents {
    private final Integer number;
    private final List<Student> listOfStudents = new ArrayList<>();

    public GroupOfStudents(Integer number,Student... listOfStud) {
        if (number == null) throw new IllegalArgumentException();
        for (Student student : listOfStud) {
            if (student == null) throw new IllegalArgumentException("You can't add null students");
            listOfStudents.add(student);
        }
        this.number = number;
    }

    public boolean addStudent(Student student) {
        return this.listOfStudents.add(student);
    }

    private Student getStudent(String studentName) {
        for (Student student : listOfStudents) {
            if (student.getName().equals(studentName)) {
                return student;
            }
        }
        return null;
    }
    public boolean removeStudent(String studentName){
        return this.listOfStudents.remove(getStudent(studentName));
    }

    public boolean addSubject(String subject) {
        if (subject == null || subject.isEmpty()) return false;
        for (Student student : listOfStudents) {
            if (!student.getSubjects().containsKey(subject))
                student.getSubjects().put(subject, null);
        }
        return true;
    }

    public boolean removeSubject(String subject) {
        boolean flag = false;
        if (subject.isEmpty()) return false;
        for (Student student : listOfStudents) {
            if (student.getSubjects().containsKey(subject))
                flag = true;
            student.getSubjects().remove(subject);
        }
        return flag;
    }

    public boolean addMark(String studentName, String subject, Integer mark) {
        Student student = getStudent(studentName);
        if (student == null || subject.isEmpty() || mark == null) return false;
        if (student.getSubjects().containsKey(subject) && student.getSubjects().get(subject) == null) {
            student.getSubjects().put(subject, mark);
            return true;
        }
        return false;
    }

    public boolean changeMark(String studentName, String subject, Integer mark) {
        Student student = getStudent(studentName);
        if (student == null || subject.isEmpty() || mark == null) return false;
        if (student.getSubjects().containsKey(subject) &&
                student.getSubjects().get(subject) != null &&
                !student.getSubjects().get(subject).equals(mark)) {
            student.getSubjects().put(subject, mark);
            return true;
        }
        return false;
    }

    public boolean deleteMark(String studentName, String subject) {
        Student student = getStudent(studentName);
        if (student == null || subject.isEmpty()) return false;
        if (student.getSubjects().containsKey(subject) && student.getSubjects().get(subject) != null) {
            student.getSubjects().put(subject, null);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GroupOfStudents)) return false;
        if (((GroupOfStudents) obj).listOfStudents.size() != this.listOfStudents.size()) return false;
        if (!((GroupOfStudents) obj).number.equals(this.number)) return false;
        for (Student student : listOfStudents) {
            if (!((GroupOfStudents) obj).listOfStudents.contains(student)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return listOfStudents.hashCode() + number.hashCode();
    }
}

