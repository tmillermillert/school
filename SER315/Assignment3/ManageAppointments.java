//import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

public class ManageAppointments {

	private TutorData tutor;

	private ManageSystem ms;

	private ArrayList<Appointment> appointments;

	private Appointment a;

	private BookAndCancelAppointment bookAndCancelAppointment;

	private int current_id;
	
    
    public ManageAppointments(){
        appointments = new ArrayList<Appointment>();
        current_id = 0;
    }

    public void set_ms(ManageSystem ms){
        this.ms = ms;
    }
    public void set_bookAndCancelAppointment(BookAndCancelAppointment bookAndCancelAppointment){
        this.bookAndCancelAppointment = bookAndCancelAppointment;
    }
    public void add_appointment(Appointment a){
        this.appointments.add(a);
    }


    public String create_appointment(Date time, String email){
    	tutor = ms.get_tutor(email);
    	Appointment appt = tutor.get_appt_by_time(time);
    	if (appt == null){
    		a = new Appointment(time, current_id++, tutor);
            tutor.add_appointment(a);
    		this.add_appointment(a);

    		return "appointment_successfully_added";
    	}
    	return "already_an_appointment_during_timeframe";


    }
    public ArrayList<Appointment> get_appointments(){
        return appointments;
    }

}
