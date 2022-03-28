package login;

import hotelsystem.userFactory.UserFactory;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.RegisterUserCommand;
import hotelsystem.userFactory.Customer;
import hotelsystem.userFactory.Staff;

import java.util.regex.Pattern;

import email.Email;

import java.util.Random;

public class Signup implements SignupInterface{
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public String type = "Customer";
    public UserFactory person;
    public int authKey;
    private String username;
    CommandInvoker invoker;

    public boolean signup(String email, String password) {
        invoker = new CommandInvoker();
        if (!isValidEmail(email)) {
            return false;
        }
        if("Staff".equals(type)) {
            person = new Staff();
        }
        else {
            person = new Customer();
        }
        person = this.createsUser(email, username, password); 
        invoker.setCommand(new RegisterUserCommand(person));
        invoker.execute();
        return true;
    }
    public void setName(String username){
        this.username = username;
    }
    public void setType(String type){
        this.type = type;
    }
    public boolean isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            return false;
        }

        return true;
    }

    public UserFactory returnUser() {
        return person;
    }
	@Override
	public void twoFactorAuth(String email) {
		Random rand = new Random();
		authKey = rand.nextInt(10000);
        String emailToSend = "Thank you for signing up to Platinum Hotels \n Your confirmation code is: " + authKey;
        new Email(email, "Platinum Hotels Signup Confirmation", emailToSend);
        //TODO Remove next line before release
        System.out.println("AUTHKEY FOR DEVELOPMENT PURPOSES //// TO BE REMOVED   : " + authKey);
	}
	@Override
	public boolean checkAuth(int num) {
		if(num == authKey){
            return true;
        }
        else{
            return false;
        }
		
	}
}
