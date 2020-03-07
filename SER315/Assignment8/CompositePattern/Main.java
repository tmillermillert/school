import java.util.ArrayList;

public class Main{
	public static void main(String[] args){
		Component component1 = new Lesson(1, 1);
		Component component2 = new Lesson(2, 1);
		Component component3 = new Lesson(3, 1);
		Component component4 = new Lesson(4, 1);
		Component component5 = new Lesson(5, 1);
		LessonCollection lessons = new LessonCollection();
		lessons.add(component1);
		lessons.add(component2);
		lessons.print();
		component3.print();

	}
}