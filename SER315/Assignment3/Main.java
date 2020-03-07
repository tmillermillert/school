import java.time.LocalDateTime; // import the LocalDateTime class
import java.util.ArrayList;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        //LocalDate myObj = LocalDate.now(); // Create a date object
        //System.out.println(myObj); // Display the current date
        
        Manager manager = new Manager("mgr@asu.edu", "Sally Manager", "mgrPass");
        
        BookAndCancelAppointment bookAndCancelAppointment = new BookAndCancelAppointment();
    

        ManageAppointments ma = new ManageAppointments();
        bookAndCancelAppointment.set_ma(ma);
        ma.set_bookAndCancelAppointment(bookAndCancelAppointment);

        ManageSystem ms = new ManageSystem();
        ma.set_ms(ms);
        ms.set_ma(ma);
        ms.set_manager(manager);
        
        // Create Subjects
        Subject sub0 = new Subject("Intro to Programming");
        Subject sub1 = new Subject("Operating Systems and Networks");
        Subject sub2 = new Subject("Web and Mobile Applications");
        Subject sub3 = new Subject("Computer Architecture");
            
        // Add subjects to Manager (chosen to only put this uni directional in the implementation since the subject does not need to know the manager, but could be added)
        ms.add_subject(sub0);
        ms.add_subject(sub1);
        ms.add_subject(sub2);
        ms.add_subject(sub3);
            
        // Create Tutors
        TutorData t0 = new TutorData("progtutor@asu.edu", "John Tutor", "tutpass");
        TutorData t1 = new TutorData("optutor@asu.edu", "Tutor May", "password");
        TutorData t2 = new TutorData("webtutor@asu.edu", "Dave Tutor", "tutpassword");
        TutorData t3 = new TutorData("comptutor@asu.edu", "Jane Tutor", "passtutor");
            
        // Add subjects to Manager (needed based on diagram - only implemented uni directional since both directions are not needed)
        ms.add_tutor(t0);
        ms.add_tutor(t1);
        ms.add_tutor(t2);
        ms.add_tutor(t3);
            
        // Add Subjects to Tutors
        t0.add_subject(sub0);
        t0.add_subject(sub1);
        t1.add_subject(sub1);
        t2.add_subject(sub2);
        t2.add_subject(sub3);
        t3.add_subject(sub3);
        t3.add_subject(sub0);
            
        // Create Appointments and add to Tutors
        Date date0 = new Date();
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        Date date5 = new Date();
        for(int i = 0; i < 100000; i++){
            int x = 0;
        }
        Date date6 = new Date();
        /*
        String response0 = ma.create_appointment(date0, t0.get_email());
        String response1 = ma.create_appointment(date1, t0.get_email());
        String response2 = ma.create_appointment(date2, t1.get_email());
        String response3 = ma.create_appointment(date3, t2.get_email());
        String response4 = ma.create_appointment(date4, t2.get_email());
        String response5 = ma.create_appointment(date5, t2.get_email());
        String response6 = ma.create_appointment(date6, t2.get_email());
        */
        /* 
        Appointment appt0 = new Appointment(date0, 0, t0);
        Appointment appt1 = new Appointment(date1, 1, t0);
        Appointment appt2 = new Appointment(date2, 2, t1);
        Appointment appt3 = new Appointment(date3, 3, t2);
        Appointment appt4 = new Appointment(date4, 4, t2);
        Appointment appt5 = new Appointment(date5, 5, t2);
        Appointment appt6 = new Appointment(date6, 6, t2);
        */
        /*            
        // Add appointments to appointment list
        ma.add_appointment(appt0);
        ma.add_appointment(appt1);
        ma.add_appointment(appt2);
        ma.add_appointment(appt3);
        ma.add_appointment(appt4);
        ma.add_appointment(appt5);
        ma.add_appointment(appt6);
        */
           
        /*
        // Create Students
        StudentData s0 = new StudentData("student@asu.edu", "John Student", "studpass", 5);
        StudentData s1 = new StudentData("studentjane@asu.edu", "Jane Student", "pass", 2);
        StudentData s2 = new StudentData("studentjack@asu.edu", "Jack Student", "passwo", 1);
        StudentData s3 = new StudentData("studentbob@asu.edu", "Bob Student", "studentpass", 0);
        */
        /*    
        // Add students to list
        bookAndCancelAppointment.add_student(s0);
        bookAndCancelAppointment.add_student(s1);
        bookAndCancelAppointment.add_student(s2);
        bookAndCancelAppointment.add_student(s3);
        */
        //test create appointment
        //pass
        System.out.println(ma.create_appointment(date0, t0.get_email()));
        //fail
        System.out.println(ma.create_appointment(date0, t0.get_email()));
        //pass
        System.out.println(ma.create_appointment(date0, t1.get_email()));
        //pass
        System.out.println(ma.create_appointment(date6, t0.get_email()));
        // Add Appointments to Students
        //appt0.student = s1;
        //appt0.booked = true;
        //s1.appointments.add(appt0);
        /*    
        // Test Book Appointment method
            
        // Not enough credits case
        System.out.println(bookAndCancelAppointment.bookAppointmentBySubjectAndTime("studentbob@asu.edu", "Computer Architecture", date0));
            
        // Already an appointment booked
        System.out.println(bookAndCancelAppointment.bookAppointmentBySubjectAndTime("studentjane@asu.edu", "Web and Mobile Applications", date1));
            
        // No available tutor case
        System.out.println(bookAndCancelAppointment.bookAppointmentBySubjectAndTime("student@asu.edu", "Intro to Programming", date2));
        
        // Appointment can be booked case
        int s2Credits = s2.numCredits;
        assert(appt4.student == null);
        System.out.println(bookAndCancelAppointment.bookAppointmentBySubjectAndTime("studentjack@asu.edu", "Web and Mobile Applications", date3));
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
        */
    }
    
}
