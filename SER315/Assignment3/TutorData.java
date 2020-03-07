import java.util.ArrayList;
import java.util.Date;
//import java.util.Collection;

public class TutorData extends User {

	private ArrayList<Subject> subjects;

	private ArrayList<Appointment> appointments;
    
    public TutorData(String email, String name, String password){
        super(email, name, password);
        subjects = new ArrayList<Subject>();
        appointments = new ArrayList<Appointment>();
    }
    public Appointment get_appt_by_time(Date time){
    	for (Appointment app : appointments){
    		if(app.get_time().equals(time)){
    			return app;
    		}
    	}
    	return null;
    }
    public void add_appointment(Appointment a){
        appointments.add(a);
    }

    public ArrayList<Appointment> get_appointments(){
        return appointments;
    }

    public ArrayList<Subject> get_subjects(){
        return subjects;
    }
    public void add_subject(Subject subject){
        subjects.add(subject);
    }

}
