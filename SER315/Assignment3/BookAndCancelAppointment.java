//import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

public class BookAndCancelAppointment {
    private ManageAppointments ma;

    private ArrayList<StudentData> students;
    
    public BookAndCancelAppointment(){
        students = new ArrayList<StudentData>();
    }

    public void set_ma(ManageAppointments ma){
        this.ma = ma;
    }

    public void add_student(StudentData student){
        students.add(student);
    }
    
    public String bookAppointmentBySubjectAndTime(String email,
                                                       String subject, Date time){
        //s: StudentData with s.email = email ∧ (s,a) ∈ Books 
        StudentData student = new StudentData("Error","Error","Error",0);//needed to compile assuming student exist
        for (StudentData s : students){
            if (s.get_email().compareTo(email) == 0){
                student = s;
                break;
            }
        }
        //Enough_credit == s.numCredits >= 1
        if (student.get_numCredits() >= 1){
            //Not_already_an_appt == (s, a) ∉ Books ∨ (s, a) ∈ Books ∧ a.time ≠ t
            boolean not_already_an_appt = true;
            for(Appointment a: student.get_appointments()){
                if(a.get_time().equals(time)){
                    not_already_an_appt = false;
                    break;
                }
            }
    
            if (not_already_an_appt){
                //tutor_with_subject/time_available == (t,a) ∈ oversees ∧ a.time = t ∧ (∀s:Students 
                //• (s,a) ∉ Books) ∧ (t, sub) ∈ signs_up_for ∧ subj = sub.name
                boolean tutor_with_subject_time_available = false;
                Appointment appointment = null;
                for (Appointment a: ma.get_appointments()){
                    if (a.get_tutor() != null && a.get_time().equals(time) && a.get_student() == null){
                        for (Subject sub: a.get_tutor().get_subjects()){
                            if (sub.get_name().compareTo(subject) == 0){
                                tutor_with_subject_time_available = true;
                                appointment = a;
                                break;
                            }
                        }
                    }
                }
                if (tutor_with_subject_time_available && appointment != null){
                    student.set_numCredits(student.get_numCredits()-1);
                    appointment.add_student(student);
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
