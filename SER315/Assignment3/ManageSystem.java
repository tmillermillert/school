//import java.util.Collection;
import java.util.ArrayList;

public class ManageSystem {
	public ManageAppointments ma;

	public ArrayList<TutorData> tutors;

	public ArrayList<Subject> subjects;

	public Manager manager;
    
    public ManageSystem(){
        tutors = new ArrayList<TutorData>();
        subjects = new ArrayList<Subject>();
    }

    public void set_ma(ManageAppointments ma){
        this.ma = ma;
    }

    public void set_manager(Manager manager){
        this.manager = manager;
    }

    public void add_subject(Subject subject){
        subjects.add(subject);
    }

    public void add_tutor(TutorData tutor){
        tutors.add(tutor);
    }

    public TutorData get_tutor(String email){
    	for (TutorData tutor : tutors){
    		if (tutor.get_email().compareTo(email) == 0){
    			return tutor;
    		}
    	}
    	return null;
    }
}
