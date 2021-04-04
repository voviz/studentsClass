package GroupOfStudents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (!listOfStudents.contains(student))
            return this.listOfStudents.add(student);
        return false;
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
        if (isNotCorrect(subject)) return false;
        for (Student student : listOfStudents) {
            if (!student.getSubjects().containsKey(subject))
                student.putSubject(subject);
        }
        return true;
    }

    public boolean removeSubject(String subject) {
        boolean flag = false;
        if (isNotCorrect(subject)) return false;
        for (Student student : listOfStudents) {
            if (student.getSubjects().containsKey(subject))
                flag = true;
            student.deleteSubject(subject);
        }
        return flag;
    }
    private boolean isMarkOk(Integer mark) {
        return mark > 1 && mark < 10;
    }

    public boolean addMark(String studentName, String subject, Integer mark) {
        Student student = getStudent(studentName);
        if (isNotCorrect(studentName) || isNotCorrect(subject) || isNotCorrect(mark)) return false;
        if (student.getSubjects().containsKey(subject) && student.getSubjects().get(subject) == null) {
            student.putMark(subject, mark);
            return true;
        }
        return false;
    }

    private boolean isNotCorrect(Object obj) {
        if (obj instanceof String) return ((String) obj).isEmpty();
        else if (obj instanceof Integer) return !isMarkOk((Integer) obj);
        return obj == null;
    }

    public boolean changeMark(String studentName, String subject, Integer mark) {
        Student student = getStudent(studentName);
        if (isNotCorrect(studentName) || isNotCorrect(subject) || isNotCorrect(mark)) return false;
        if (student.getSubjects().containsKey(subject) &&
                student.getSubjects().get(subject) != null &&
                !student.getSubjects().get(subject).equals(mark)) {
            student.putMark(subject, mark);
            return true;
        }
        return false;
    }

    public boolean deleteMark(String studentName, String subject) {
        Student student = getStudent(studentName);
        if (isNotCorrect(studentName) || isNotCorrect(subject)) return false;
        if (student.getSubjects().containsKey(subject) && student.getSubjects().get(subject) != null) {
            student.putMark(subject, null);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupOfStudents that = (GroupOfStudents) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(listOfStudents, that.listOfStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, listOfStudents);
    }
}

