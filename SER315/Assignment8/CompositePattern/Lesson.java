public class Lesson implements Component{
	public int id;
	public int time_scheduled;
	public void print(){
		System.out.println(this.id);
	}
	public Lesson(int id, int time_scheduled){
		this.id = id;
		this.time_scheduled = time_scheduled;
	}
}