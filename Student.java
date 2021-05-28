import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;
public class Student {
    private String id;
    private String name;
    private Date doB;
    private String phoneNum;
    private String city;
    private String country;
    private String group;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDoB() {
        return this.doB;
    }

    public void setDoB(Date doB) throws BirthDateExeception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 15);
        Date yearsAgo = cal.getTime();
        if (doB.compareTo(yearsAgo) <= 0){
            this.doB = doB;
        }
        else{
            throw new BirthDateExeception();
        }  
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) throws InvalidPhoneNumberException{
        if(phoneNum.charAt(0) != '0'){
            throw new InvalidPhoneNumberException();
        }
        else if(phoneNum.length()!=9){
            throw new InvalidPhoneNumberException("Telephone number must contain 9 digits");
        }
        else{
            this.phoneNum = phoneNum;
        }  
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) throws GroupException {
        if(group.charAt(0) != 'I' && group.charAt(0) != 'T'){
            throw new GroupException("Group name must starts with 'I' or 'T'");
        }
        this.group = group;
    }

    @Override
    public String toString() {
        return "Your ID is: "+id+" \nName: "+name+" \nDate of birth: "+ doB+" \nPhone Number: "+phoneNum+" \nCountry: "+country+" \nCity: "+city+" \nGroup Name: "+group;
    }



    public class InvalidPhoneNumberException extends Exception{
        public InvalidPhoneNumberException(){
            super("Khmer Phone number must Start with zero!");
        }
        public InvalidPhoneNumberException(String message){
            super(message);
        }
    }


    class BirthDateExeception  extends Exception{
        public BirthDateExeception(){
            super("Student must be older than 15 years old!");
        }
        public BirthDateExeception (String message){
            super(message);
        }
    }

    public class GroupException extends Exception{
        public GroupException(String message){
            super(message);
        }
    }

    public static void main(String[] args) throws Student.BirthDateExeception, Student.InvalidPhoneNumberException {
        Scanner sc = new Scanner(System.in);
        Student stu = new Student();
        System.out.println("Please enter Information of a student:");
        System.out.print("ID: ");
        stu.setId(sc.nextLine());
        System.out.print("Name: ");
        stu.setName(sc.nextLine());
        System.out.print("Date of Birth(dd/MM/yyyy): ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            stu.setDoB(dateFormat.parse(sc.nextLine()));
        } 
        catch (ParseException e) {
            System.err.println("Invalid date and time format.");
        }
        System.out.print("Phone Number: ");
        stu.setPhoneNum(sc.nextLine());
        System.out.print("City: ");
        stu.setCity(sc.nextLine());
        System.out.print("Country: ");
        stu.setCountry(sc.nextLine());
        System.out.print("Group: ");
        try {
            stu.setGroup(sc.nextLine());
        } catch (Student.GroupException e) {
            System.out.println(e);
        }

        System.out.println(stu);
        sc.close();
    }
}
