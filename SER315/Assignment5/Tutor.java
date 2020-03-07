import java.util.ArrayList;
//import java.util.Collection;

public class Tutor extends User {

	public ArrayList<Subject> subjects;

	public ArrayList<Appointment> appointments;
    
    public Tutor(String email, String name, String password){
        super(email, name, password);
        subjects = new ArrayList<Subject>();
        appointments = new ArrayList<Appointment>();
    }

}
