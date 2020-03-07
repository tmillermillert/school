public class Main{
	public static void main(String[] args){
		ClassCreator cc = new ClassCreator();
		IClass class1 = cc.factoryMethod();
		System.out.println(class1.toString());
		IClass class2 = cc.factoryMethod();
		System.out.println(class2.toString());
	}
}