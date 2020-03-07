public class ClassCreator implements IClassCreator {

	private boolean createType1;

	public ClassCreator(){
		createType1 = true; //Group Class
	}

	public IClass factoryMethod() {
		IClass iClass = null;
		if (createType1){
			iClass = new Group_Class();
		}
		else{
			iClass = new Private_Class();
		}
		createType1 = !createType1;
		return iClass;
	}

}
