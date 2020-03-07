//import java.util.Collection;
import java.util.ArrayList;
import java.time.LocalTime; // import the LocalDateTime class

public class BookAndCancelAppointments {
    public ManageAppointments manageAppointments;

    public ArrayList<Student> students;
    
    public BookAndCancelAppointments(){
        students = new ArrayList<Student>();
    }
    
    public String bookAppointmentBySubjectAndTime(String email,
                                                       String subject, String time){
        //s: StudentData with s.email = email ∧ (s,a) ∈ Books 
        Student student = new Student("Error","Error","Error",0);//needed to compile assuming student exist
        for (Student s : students){
            if (s.email.compareTo(email) == 0){
                student = s;
                break;
            }
        }
        //Enough_credit == s.numCredits >= 1
        if (student.numCredits >= 1){
            //Not_already_an_appt == (s, a) ∉ Books ∨ (s, a) ∈ Books ∧ a.time ≠ t
            boolean not_already_an_appt = true;
            for(Appointment a: student.appointments){
                if(a.time.toString().compareTo(time) == 0){
                    not_already_an_appt = false;
                    break;
                }
            }
    
            if (not_already_an_appt){
                //tutor_with_subject/time_available == (t,a) ∈ oversees ∧ a.time = t ∧ (∀s:Students 
                //• (s,a) ∉ Books) ∧ (t, sub) ∈ signs_up_for ∧ subj = sub.name
                boolean tutor_with_subject_time_available = false;
                Appointment appointment = null;
                for (Appointment a: manageAppointments.appointments){
                    if (a.tutor != null && a.time.toString().compareTo(time) == 0 && a.student == null){
                        for (Subject sub: a.tutor.subjects){
                            if (sub.name.compareTo(subject) == 0){
                                tutor_with_subject_time_available = true;
                                appointment = a;
                                break;
                            }
                        }
                    }
                }
                if (tutor_with_subject_time_available && appointment != null){
                    student.numCredits--;
                    appointment.student = student;
                    return "Student:appointment_booked\nTutor:appointment_scheduled";
                }
                //⌐tutor_with_subject/time_available
                else{
                    return "Student:no_tutor_available\nTutor:";
                }

            }
            //⌐Not_already_an_appt
            else{
                return "Student:already_an_appointment_booked\nTutor:";
            }
        }
        //⌐Enough_credit 
        else{
            return "Student:not_enough_credit\nTutor:";
        }
    }
    
    public String BookAppointmentByID(String email, int id){
        return "";
    }


}
