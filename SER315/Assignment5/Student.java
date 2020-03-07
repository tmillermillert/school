//import java.util.Collection;
import java.util.ArrayList;

public class Student extends User {

	public int numCredits;

	public ArrayList<Appointment> appointments;
    public Student(String email, String name, String password, int credits) {
        super(email, name, password);
        this.numCredits = credits;
        this.appointments = new ArrayList<Appointment>();
    }

}
