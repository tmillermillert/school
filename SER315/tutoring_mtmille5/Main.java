import java.time.LocalDateTime;
import java.util.ArrayList;




public class Main {
    private static ArrayList<Student> students = new ArrayList<Student>();
    private static Manager manager = new Manager();
    
    public static void main(String[] args) {
        int id = 0;
        
    
        CreditStore creditStore = new CreditStore();

        manager.tutors = new ArrayList<Tutor>();
        manager.subjects = new ArrayList<Subject>();
    
        Student student_1 = new Student();
        student_1.email = "email_1";
        student_1.name = "name_1";
        student_1.password = "password_1";
        student_1.creditStore = creditStore;
        student_1.credits = -1;
        student_1.appointments = new ArrayList<Appointment>();
    
        Student student0 = new Student();
        student0.email = "email0";
        student0.name = "name0";
        student0.password = "password0";
        student0.creditStore = creditStore;
        student0.credits = 0;
        student0.appointments = new ArrayList<Appointment>();
    
        Student student1 = new Student();
        student1.email = "email1";
        student1.name = "name1";
        student1.password = "password1";
        student1.creditStore = creditStore;
        student1.credits = 1;
        student1.appointments = new ArrayList<Appointment>();
    
        Student student2 = new Student();
        student2.email = "email2";
        student2.name = "name2";
        student2.password = "password2";
        student2.creditStore = creditStore;
        student2.credits = 2;
        student2.appointments = new ArrayList<Appointment>();
    
        Student student3 = new Student();
        student3.email = "email3";
        student3.name = "name3";
        student3.password = "password3";
        student3.creditStore = creditStore;
        student3.credits = 3;
        student3.appointments = new ArrayList<Appointment>();
    
        students.add(student_1);
        students.add(student0);
        students.add(student1);
        students.add(student2);
        students.add(student3);
    
        Subject subject1 = new Subject();
        subject1.name = "subject1";
        Subject subject2 = new Subject();
        subject2.name = "subject2";
        Subject subject3 = new Subject();
        subject3.name = "subject3";
        Subject subject4 = new Subject();
        subject4.name = "subject4";
        Subject subject5 = new Subject();
        subject5.name = "subject5";
        Subject subject6 = new Subject();
        subject6.name = "subject6";
    
        manager.subjects.add(subject1);
        manager.subjects.add(subject2);
        manager.subjects.add(subject3);
        manager.subjects.add(subject4);
        manager.subjects.add(subject5);
        manager.subjects.add(subject6);
    
    
        Tutor tutor1 = new Tutor();
        tutor1.name = "tutor1";
        tutor1.email = "email1";
        tutor1.password = "password1";
        tutor1.subjects = new ArrayList<Subject>();
        tutor1.appointments = new ArrayList<Appointment>();
        
        Tutor tutor2 = new Tutor();
        tutor2.name = "tutor2";
        tutor2.email = "email2";
        tutor2.password = "password2";
        tutor2.subjects = new ArrayList<Subject>();
        tutor2.appointments = new ArrayList<Appointment>();
        
    
        LocalDateTime now = LocalDateTime.now();
        Appointment appointment1 = new Appointment();
        appointment1.isBooked = false;
        appointment1.id += id;
        appointment1.dateTime = now;
        appointment1.tutor= tutor1;
        appointment1.subject = subject1;
        tutor1.appointments.add(appointment1);
        
        LocalDateTime now2 = LocalDateTime.now();
        Appointment appointment2 = new Appointment();
        appointment2.isBooked = true;
        appointment2.id += id;
        appointment2.dateTime = now2;
        appointment2.tutor= tutor1;
        appointment2.subject = subject1;
        tutor1.appointments.add(appointment2);
        appointment2.student = student2;
        student2.appointments.add(appointment2);
        
        Appointment appointment3 = new Appointment();
        appointment3.isBooked = false;
        appointment3.id += id;
        appointment3.dateTime = now2;
        appointment3.subject = subject1;
        appointment3.tutor= tutor2;
        tutor2.appointments.add(appointment3);

    
 
    
        manager.tutors.add(tutor1);
        manager.tutors.add(tutor2);
        //valid
        String result = bookAppointment(student1.email, subject1.name, now.toString());
        System.out.println(result);
        //invalid email
        result = bookAppointment("not valid email", subject1.name, now.toString());
        System.out.println(result);
        //invalid time for tutor
        result = bookAppointment(student1.email, subject1.name, "not valid time");
        System.out.println(result);
        //invalid subject
        result = bookAppointment(student1.email, "not valid subject",      LocalDateTime.now().toString());
        System.out.println(result);
        //no credits
        result = bookAppointment(student0.email, subject1.name,      LocalDateTime.now().toString());
        System.out.println(result);
        //student not available
        result = bookAppointment(student2.email, subject1.name, now2.toString());
        System.out.println(result);
        
    }
    
    public static String bookAppointment(String email, String subject, String dateTime){
        //check if valid email
        boolean isValidEmail = false;
        for (Student s: students){
            if (email.compareTo(s.email) == 0){
                isValidEmail = true;
                if (s.credits <= 0){
                    return "Insufficient Credits!";
                }
                break;
            }
        }
        if (isValidEmail == false){
            return "Invalid Email!";
        }
        boolean isValidSubject = false;
        for (Subject sub: manager.subjects){
            if (subject.compareTo(sub.name) == 0){
                isValidSubject = true;
                break;
            }
        }
        if (isValidSubject == false){
            return "Invalid Subject!";
        }
        boolean isStudentAvailable = false;
        for (Student s: students){
            if (s.email == email){
                for (Appointment a : s.appointments){
                    if(a.dateTime.toString().compareTo(dateTime) == 0){
                        return "Student is already scheduled for tutoring at the specified time!";
                    }
                }
            }
        }
        boolean isTutorAvailable = false;
        for (Tutor tutor: manager.tutors){
            for (Appointment appointment: tutor.appointments){
                if(appointment.dateTime.toString().compareTo(dateTime) == 0 && appointment.subject.name.compareTo(subject) == 0
                   && appointment.isBooked == false){
                    isTutorAvailable = true;
                    return "Success";
                }
            }
        }
        return "No Tutor is available for this time!";
    }
	

}
