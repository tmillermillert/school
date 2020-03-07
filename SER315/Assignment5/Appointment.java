
import java.time.LocalTime;
public class Appointment {

	public LocalTime time;

	public int id;

	public Tutor tutor;

	public Student student;
    
    public Appointment(LocalTime time, boolean booked, int id , Tutor t, Student s){
        this.time = time;
        this.id = id;
        this.tutor = t;
        this.student = s;
    }

}
