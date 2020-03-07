import java.util.Date;
public class Appointment {

	private Date time;

	private int id;

	private TutorData tutor;

	public StudentData student;
    
    public Appointment(Date time, int id , TutorData tutor){
        this.time = time;
        this.id = id;
        this.tutor = tutor;
        this.student = null;
    }
    public Date get_time(){
        return this.time;
    }

    public void add_student(StudentData student){
        this.student = student;
    }
    public TutorData get_tutor(){
        return tutor;
    }
    public StudentData get_student(){
        return student;
    }

}
