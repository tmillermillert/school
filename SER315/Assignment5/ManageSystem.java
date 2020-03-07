//import java.util.Collection;
import java.util.ArrayList;

public class ManageSystem {
	public ManageAppointments manageAppointments;

	public ArrayList<Tutor> tutors;

	public ArrayList<Subject> subjects;

	public Manager manager;
    
    public ManageSystem(){
        tutors = new ArrayList<Tutor>();
        subjects = new ArrayList<Subject>();
    }
}
