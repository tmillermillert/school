//import java.util.Collection;
import java.util.ArrayList;

public class StudentData extends User {

	public int numCredits;

	private ArrayList<Appointment> appointments;

    public StudentData(String email, String name, String password, int credits) {
        super(email, name, password);
        this.numCredits = credits;
        this.appointments = new ArrayList<Appointment>();
    }
    public int get_numCredits(){
    	return this.numCredits;
    }
    public ArrayList<Appointment> get_appointments(){
        return appointments;
    }
    public void set_numCredits(int s){
        numCredits = s;
    }

}
