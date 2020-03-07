public class User {

	private String password;

	private String email;

	private String name;
    
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String get_password(){
    	return password;
    }
    public String get_email(){
    	return email;
    }
    public String get_name(){
    	return name;
    }

}
