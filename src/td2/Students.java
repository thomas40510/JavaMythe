package td2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Students {
    public static class Class{
        private final int id;
        private String name;
        private String teacher;
        private double mark;
        private double nbHours;

        public Class(int id, String name, String teacher, double nbHours) {
            this.id = id;
            this.name = name;
            this.teacher = teacher;
            this.nbHours = nbHours;
        }

        public Class(int id){
            this(id, "Unknown", "Unknown", 0);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public double getNbHours() {
            return nbHours;
        }

        public void setNbHours(double nbHours) {
            this.nbHours = nbHours;
        }
    }
    public static class Student{
        private final String lname;
        private final String fname;
        private final Date birthDate;
        private String[] parentsMail = new String[2];
        private String section;

        private List<Class> classes = new ArrayList<>();

        public Student(String lname, String fname, Date birthDate, String[] parentsMail, String section, Class[] classes) {
            this.lname = lname;
            this.fname = fname;
            this.birthDate = birthDate;
            this.parentsMail = parentsMail;
            this.section = section;
        }

        public Student(String lname, String fname, Date birthDate, String[] parentsMail, String section) {
            this(lname, fname, birthDate, parentsMail, section, new Class[0]);
        }

        public void addClasses(Class... classes){
            this.classes.addAll(Arrays.asList(classes));
        }

        public void addClass(Class c){
            this.classes.add(c);
        }

        public void deleteClass(int id){
            for (int i = 0; i < classes.size(); i++) {
                if(classes.get(i).getId() == id){
                    classes.remove(i);
                    return;
                }
            }
        }
        public double avgMark(){
            double sum = 0;
            for (Class aClass : classes) {
                sum += aClass.mark;
            }
            return sum / classes.size();
        }

        public String toString() {
            return "{Last name: " + lname + ", First name: " + fname + ", Birth date: " + birthDate + ", Parents mail: " + Arrays.toString(parentsMail) + ", Section: " + section + ", Classes: " + classes + "}\n";
        }

    }
    public static class School{
        private final List<Student> students = new ArrayList<>();

        public School(Student... students) {
            this.students.addAll(Arrays.asList(students));
        }

        public School(){
            this(new Student[0]);
        }

        public void addStudent(Student s){
            students.add(s);
        }

        public void delStudent(String lname, String fname){
            for (int i = 0; i < students.size(); i++) {
                if(students.get(i).lname.equals(lname) && students.get(i).fname.equals(fname)){
                    students.remove(i);
                    return;
                }
            }
        }

        public String toString() {
            return "School{\n" +
                    "students=" + students +
                    "\n }";
        }
    }

    public static void main(String[] args) {
        School s = new School();
        Student s1 = new Student("Doe", "John", new Date(2000, 1, 1), new String[]{"mail", "mail2"}, "A");
        Student s2 = new Student("Doe", "Jane", new Date(2000, 1, 1), new String[]{"mail", "mail2"}, "A");
        s.addStudent(s1);
        s.addStudent(s2);

        Class c = new Class(1, "Math", "Mr. Smith", 2);
        s1.addClass(c);
        s2.addClass(c);

        System.out.println(s);

    }
}
