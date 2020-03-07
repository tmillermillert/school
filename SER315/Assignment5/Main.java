import java.time.LocalDateTime; // import the LocalDateTime class
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //LocalDate myObj = LocalDate.now(); // Create a date object
        //System.out.println(myObj); // Display the current date
        
         Manager manager = new Manager("mgr@asu.edu", "Sally Manager", "mgrPass");
        
        BookAndCancelAppointments bookAndCancelAppointments = new BookAndCancelAppointments();
    

        ManageAppointments manageAppointments = new ManageAppointments();
        bookAndCancelAppointments.manageAppointments = manageAppointments;
        manageAppointments.bookAndCancelAppointments = bookAndCancelAppointments;

        ManageSystem manageSystem = new ManageSystem();
        manageAppointments.manageSystem = manageSystem;
        manageSystem.manageAppointments = manageAppointments;
        manageSystem.manager = manager;
        
        // Create Subjects
        Subject sub0 = new Subject("Intro to Programming");
        Subject sub1 = new Subject("Operating Systems and Networks");
        Subject sub2 = new Subject("Web and Mobile Applications");
        Subject sub3 = new Subject("Computer Architecture");
            
        // Add subjects to Manager (chosen to only put this uni directional in the implementation since the subject does not need to know the manager, but could be added)
        manageSystem.subjects.add(sub0);
        manageSystem.subjects.add(sub1);
        manageSystem.subjects.add(sub2);
        manageSystem.subjects.add(sub3);
            
        // Create Tutors
        Tutor t0 = new Tutor("progtutor@asu.edu", "John Tutor", "tutpass");
        Tutor t1 = new Tutor("optutor@asu.edu", "Tutor May", "password");
        Tutor t2 = new Tutor("webtutor@asu.edu", "Dave Tutor", "tutpassword");
        Tutor t3 = new Tutor("comptutor@asu.edu", "Jane Tutor", "passtutor");
            
        // Add subjects to Manager (needed based on diagram - only implemented uni directional since both directions are not needed)
        manageSystem.tutors.add(t0);
        manageSystem.tutors.add(t1);
        manageSystem.tutors.add(t2);
        manageSystem.tutors.add(t3);
            
        // Add Subjects to Tutors
        t0.subjects.add(sub0);
        t0.subjects.add(sub1);
        t1.subjects.add(sub1);
        t2.subjects.add(sub2);
        t2.subjects.add(sub3);
        t3.subjects.add(sub3);
        t3.subjects.add(sub0);
            
        // Create Appointments and add to Tutors
        Appointment appt0 = new Appointment(LocalTime.of(10, 0), false, 0, t0, null);
        Appointment appt1 = new Appointment(LocalTime.of(10,30), false, 1, t0, null);
        Appointment appt2 = new Appointment(LocalTime.of(2,30), false, 2, t1, null);
        Appointment appt3 = new Appointment(LocalTime.of(4,0), false, 3, t2, null);
        Appointment appt4 = new Appointment(LocalTime.of(4,30), false, 4, t2, null);
        Appointment appt5 = new Appointment(LocalTime.of(3,30), false, 5, t2, null);
        Appointment appt6 = new Appointment(LocalTime.of(10, 0), false, 6, t2, null);
            
        // Add appointments to appointment list
        manageAppointments.appointments.add(appt0);
        manageAppointments.appointments.add(appt1);
        manageAppointments.appointments.add(appt2);
        manageAppointments.appointments.add(appt3);
        manageAppointments.appointments.add(appt4);
        manageAppointments.appointments.add(appt5);
        manageAppointments.appointments.add(appt6);
            
            
        // Create Students
        Student s0 = new Student("student@asu.edu", "John Student", "studpass", 5);
        Student s1 = new Student("studentjane@asu.edu", "Jane Student", "pass", 2);
        Student s2 = new Student("studentjack@asu.edu", "Jack Student", "passwo", 1);
        Student s3 = new Student("studentbob@asu.edu", "Bob Student", "studentpass", 0);
            
        // Add students to list
        bookAndCancelAppointments.students.add(s0);
        bookAndCancelAppointments.students.add(s1);
        bookAndCancelAppointments.students.add(s2);
        bookAndCancelAppointments.students.add(s3);
            
        // Add Appointments to Students
        appt0.student = s1;
        //appt0.booked = true;
        s1.appointments.add(appt0);
            
        // Test Book Appointment method
            
        // Not enough credits case
        System.out.println(bookAndCancelAppointments.bookAppointmentBySubjectAndTime("studentbob@asu.edu", "Computer Architecture", LocalTime.of(4, 0).toString()));
            
        // Already an appointment booked
        System.out.println(bookAndCancelAppointments.bookAppointmentBySubjectAndTime("studentjane@asu.edu", "Web and Mobile Applications", LocalTime.of(10, 0).toString()));
            
        // No available tutor case
        System.out.println(bookAndCancelAppointments.bookAppointmentBySubjectAndTime("student@asu.edu", "Intro to Programming", LocalTime.of(3, 0).toString()));
        
        // Appointment can be booked case
        int s2Credits = s2.numCredits;
        assert(appt4.student == null);
        System.out.println(bookAndCancelAppointments.bookAppointmentBySubjectAndTime("studentjack@asu.edu", "Web and Mobile Applications", LocalTime.of(4, 30).toString()));
        assert(s2Credits - 1 == s2.numCredits);
        assert(appt4.student == s2);
        // test book by id
        // Not enough credits case
        //System.out.println(bookAndCancelAppointments.BookAppointmentByID("studentbob@asu.edu", 0));
        
        // Already an appointment booked
        //System.out.println(bookAndCancelAppointments.BookAppointmentByID("studentjane@asu.edu", 6));
        
        // No available tutor case
        //System.out.println(bookAndCancelAppointments.BookAppointmentByID("student@asu.edu", 0));
        
        // Appointment can be booked case
        //System.out.println(bookAndCancelAppointments.BookAppointmentByID("studentjack@asu.edu", 1));
    }
    
}
