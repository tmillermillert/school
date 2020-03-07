import java.util.ArrayList;
public class LessonCollection implements Component{
	ArrayList<Component> lessons;
	public LessonCollection(){
		lessons = new ArrayList<Component>();
	}
	public void print(){
		System.out.println("{");
		for(Component c: lessons){
			c.print();
		}
		System.out.println("}");
	}
	public LessonCollection add(Component component){
		lessons.add(component);
		return this;
	}
}