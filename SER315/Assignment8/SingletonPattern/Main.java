public class Main{
	public static void main(String[] args){

		Manage_Students_Controller msc1 = Manage_Students_Controller.getInstance();
		Manage_Students_Controller msc2 = Manage_Students_Controller.getInstance();
		assert(msc1.equals(msc2));

	}
}