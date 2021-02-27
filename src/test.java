import classstudent.GroupOfStudents;
import classstudent.Student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;


class GroupTest {
    Map<String, Integer> subjectsAndMarks1 = new HashMap<>();
    {
        subjectsAndMarks1.put("Математика", null);
        subjectsAndMarks1.put("История", 5);
        subjectsAndMarks1.put("Русский язык", 5);
        subjectsAndMarks1.put("Рисование", null);
    }
    Map<String, Integer> subjectsAndMarks2 = new HashMap<>();
    {
        subjectsAndMarks2.put("Математика", 2);
        subjectsAndMarks2.put("История", 2);
        subjectsAndMarks2.put("Русский язык", 2);
        subjectsAndMarks2.put("Рисование", 2);
    }
    Map<String, Integer> subjectsAndMarks3 = new HashMap<>();
    {
        subjectsAndMarks3.put("Математика", null);
        subjectsAndMarks3.put("История", 2);
        subjectsAndMarks3.put("Русский язык", 2);
        subjectsAndMarks3.put("Рисование", 2);
    }
    GroupOfStudents group1 = new GroupOfStudents(1,
            new Student("Захаров Владимир Александрович", subjectsAndMarks1),
            new Student("Терентьев Михаил Павлович", subjectsAndMarks2),
            new Student("Ибраева Виолета Альбертовна", subjectsAndMarks3)
    );


    @Test
    void constructorTest() {
        Map<String, Integer> nullSubjectsAndMarks = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> new Student("", nullSubjectsAndMarks));
        nullSubjectsAndMarks.put("", 5);
        assertThrows(IllegalArgumentException.class, () -> new Student("123", nullSubjectsAndMarks));
        nullSubjectsAndMarks.remove("");
        nullSubjectsAndMarks.put(null, 5);
        assertThrows(IllegalArgumentException.class, () -> new Student("123", nullSubjectsAndMarks));
    }

    @Test
    void addStudent() {
        assertTrue(group1.addStudent(new Student("Менделеев Дмитрий Иванович", subjectsAndMarks2)));
    }

    @Test
    void removeStudent() {
        assertTrue(group1.removeStudent("Захаров Владимир Александрович"));
        assertFalse(group1.removeStudent("Менделеев Дмитрий Иванович"));
        assertFalse(group1.removeStudent("Захаров Владимир"));
    }

    @Test
    void addSubject() {
        assertTrue(group1.addSubject("Программирование"));
        assertFalse(group1.addSubject(""));
        assertFalse(group1.addSubject(null));
    }

    @Test
    void removeSubject() {
        assertTrue(group1.removeSubject("Рисование"));
        assertFalse(group1.removeSubject("Рисование"));
        assertFalse(group1.removeSubject(""));
        assertFalse(group1.removeSubject("Программирование"));
        assertFalse(group1.removeSubject("Рисавание"));
    }

    @Test
    void addMark() {
        assertTrue(group1.addMark("Захаров Владимир Александрович", "Математика", 5));
        assertFalse(group1.addMark("Терентьев Михаил Павлович", "Математика", 5));
        assertFalse(group1.addMark("Захаров Владимир Александрович", "Математика", 5));
        assertTrue(group1.addMark("Ибраева Виолета Альбертовна", "Математика", 5));
        assertFalse(group1.addMark("Захаров Владимир Александрович", "Рисование", null));
        assertFalse(group1.addMark("Захаров Владимир Александрович", "", 5));
        assertFalse(group1.addMark("", "Математика", 5));
    }

    @Test
    void changeMark() {
        assertTrue(group1.changeMark("Захаров Владимир Александрович", "Русский язык", 4));
        assertTrue(group1.changeMark("Захаров Владимир Александрович", "Русский язык", 5));
        assertFalse(group1.changeMark("Захаров Владимир Александрович", "Русский язык", 5));
        assertFalse(group1.changeMark("Захаров Владимир Александрович", "Математика", 4));
        assertFalse(group1.changeMark("Захаров Владимир Александрович", "", 5));
        assertFalse(group1.changeMark("", "Русский язык", 5));
        assertFalse(group1.changeMark("Захаров Владимир Александрович", "Русский язык", null));
        assertTrue(group1.changeMark("Ибраева Виолета Альбертовна", "Русский язык", 4));
    }

    @Test
    void deleteMark() {
        assertTrue(group1.deleteMark("Захаров Владимир Александрович", "Русский язык"));
        assertFalse(group1.deleteMark("Захаров Владимир Александрович", "Русский язык"));
        assertFalse(group1.deleteMark("Захаров Владимир Александрович", ""));
        assertFalse(group1.deleteMark("", "Русский язык"));
        assertFalse(group1.deleteMark("Захаров Владимир Александрович", "Математика"));
    }

    @Test
    void equals() {
        GroupOfStudents group2 = new GroupOfStudents(1,
                new Student("Захаров Владимир Александрович", subjectsAndMarks1),
                new Student("Терентьев Михаил Павлович", subjectsAndMarks2),
                new Student("Ибраева Виолета Альбертовна", subjectsAndMarks3)
        );
        assertEquals(group1, group2);
        group2.removeStudent("Терентьев Михаил Павлович");
        assertNotEquals(group1, group2);
        Student student1 = new Student("Павлик", subjectsAndMarks1);
        Student student2 = new Student("Павлик", subjectsAndMarks2);
        Map<String, Integer> subjectsAndMarks4 = new HashMap<>();
        {
            subjectsAndMarks4.put("Математика", null);
            subjectsAndMarks4.put("История", 5);
            subjectsAndMarks4.put("Русский язык", 5);
            subjectsAndMarks4.put("Рисование", null);
        }
        Student student3 = new Student("неПавлик", subjectsAndMarks4);
        Map<String, Integer> subjectsAndMarks5 = new HashMap<>();
        {
            subjectsAndMarks5.put("Математика", null);
            subjectsAndMarks5.put("История", 5);
            subjectsAndMarks5.put("Русский язык", 5);
            subjectsAndMarks5.put("Рисование", null);
        }
        Student student5 = new Student("Павлик", subjectsAndMarks5);
        assertNotEquals(student1, student2);
        assertNotEquals(student1, student3);
        assertEquals(student1, student5);
        assertEquals(student1.hashCode(), student5.hashCode());
        assertNotEquals(student1.hashCode(), student2.hashCode());
    }
}
