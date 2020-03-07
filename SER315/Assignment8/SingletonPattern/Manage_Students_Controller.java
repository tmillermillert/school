public class Manage_Students_Controller {
   private static Manage_Students_Controller instance = null;
   protected Manage_Students_Controller() {
   }
   public static Manage_Students_Controller getInstance() {
      if(instance == null) {
         instance = new Manage_Students_Controller();
      }
      return instance;
   }
}