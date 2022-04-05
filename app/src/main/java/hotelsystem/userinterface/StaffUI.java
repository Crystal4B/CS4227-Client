package hotelsystem.userinterface;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import hotelsystem.billingsystem.CouponVisitor;
import hotelsystem.login.LoginAdapter;
import hotelsystem.login.Signup;
import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.rooms.CreateRoomCommand;
import hotelsystem.requestsystem.commands.rooms.GetAllRoomsCommand;
import hotelsystem.requestsystem.commands.rooms.RemoveRoomCommand;
import hotelsystem.requestsystem.commands.users.GetAllStaffUsersCommand;
import hotelsystem.requestsystem.commands.users.RemoveUserCommand;
import hotelsystem.requestsystem.commands.vouchers.CreateVoucherCommand;
import hotelsystem.requestsystem.commands.vouchers.RemoveVoucherCommand;
import hotelsystem.requestsystem.commands.vouchers.UpdateVoucherCommand;
import hotelsystem.requestsystem.commands.vouchers.ValidateVoucherCommand;
import hotelsystem.roomfactory.Room;
import hotelsystem.roomfactory.RoomFactory;
import hotelsystem.roomfactory.RoomInterface;
import hotelsystem.userfactory.UserInterface;

/**
 * Staff user interface. This is the main staff menu. This provides functionality for staff users to create and view reservations, manage rooms, staff and vouchers, and also exit the application.
 * @author Jordan Marshall
 */
public class StaffUI {

    /**
     * This runs the current state specified in UI.java and can also change sub-states.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static int run(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Create Reservation");
        System.out.println("2. \t View Reservations");
        System.out.println("3. \t Manage Rooms");
        System.out.println("4. \t Manage Staff");
        System.out.println("5. \t Manage Vouchers");
        System.out.println("6. \t Exit");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return UI.STAFF_MENU;
        }
        switch (option) {
            case 1:
                return UI.RESERVATION_STATE;
            case 3:
                while(true){
                    if(manageRooms(console)){
                        break;
                    }
                }
                return UI.STAFF_MENU;
            case 4:  
                while(true){
                    if(manageStaffAccount(console)) {
                        break;
                    }
                }
                return UI.STAFF_MENU;
            case 5:
                while(true){
                    if(manageVouchers(console)) {
                        break;
                    }
                }
                return UI.STAFF_MENU;
            case 6:  
                return UI.EXIT;
            default:
                return UI.STAFF_MENU;
        }
    }

    /**
     * This is a function to manage rooms. This allows staff to add and remove available rooms for users in the database.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static Boolean manageRooms(Scanner console){
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(new GetAllRoomsCommand());
        invoker.execute();
        ArrayList<RoomInterface> roomFactories = invoker.getResponse();
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Add Room");
        System.out.println("2. \t Remove Room");
        System.out.println("3. \t Back");
        int option = Integer.parseInt(console.nextLine());
        System.out.println("\n####################################################\n");
        switch (option) {
            case 1:
                RoomInterface selectedAddRoomInterface = addRoom(console);
                if(selectedAddRoomInterface != null){
                    invoker.setCommand(new CreateRoomCommand((Room) selectedAddRoomInterface));
                    invoker.execute();
                }
                break;
            case 2:
                if(roomFactories.size() == 0){
                    break;
                }
                RoomInterface selectedRemoveRoomInterface = removeRoom(console, roomFactories);
                if(selectedRemoveRoomInterface != null){
                    invoker.setCommand(new RemoveRoomCommand((Room) selectedRemoveRoomInterface));
                    invoker.execute();
                }
                break;
            case 3:
                return true;
            default:
                return false;        
        }
        return false;
    }
    
    /**
     * This function allows staff users to add rooms for availability in the database.
     * @param console Used to read user input.
     * @return Returns room of chosen type by user.
     */
    public static RoomInterface addRoom(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following rooms to add:");
        System.out.println("0. \t Back\n");
        System.out.println("1. \t Standard Room");
        System.out.println("2. \t Deluxe Room");
        System.out.println("3. \t VIP Room");
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return addRoom(console);
        }
        System.out.println("\n####################################################\n");
        System.out.println(option);
        return switch (option) {
            case 0 -> null;
            case 1 -> RoomFactory.createStandard(-1, 2);
            case 2 -> RoomFactory.createDeluxe(-1, 2);
            case 3 -> RoomFactory.createVIP(-1, 2);
            default -> addRoom(console);
        };
    }

    /**
     * This function allows staff users to remove rooms for availability in the database.
     * @param console Used to read user input.
     * @return Returns room of chosen type by user.
     */
    public static RoomInterface removeRoom(Scanner console, ArrayList<RoomInterface> roomFactories){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following rooms to remove:");
        System.out.println("\n0.\t Back\n");
        for (int i = 0; i < roomFactories.size(); i++)
        {   
            System.out.println((i+1) + ".\t" + roomFactories.get(i).toString());
        }
        int option;
        try {
            option = Integer.parseInt(console.nextLine())-1;
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return addRoom(console);
        }
        System.out.println("\n####################################################\n");
        if(option == -1){
            return null;
        }
        else if(option >= 0 && option < roomFactories.size()){
            return roomFactories.get(option);
        }
        return removeRoom(console, roomFactories);
    }

    /**
     * This is a function to manage staff accounts. This allows staff to add and remove staff accounts in the database.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean manageStaffAccount(Scanner console) {
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Add Staff");
        System.out.println("2. \t Remove Staff");
        System.out.println("3. \t List Staff Emails");
        System.out.println("4. \t Back");
        int option = Integer.parseInt(console.nextLine());
        System.out.println("\n####################################################\n");
        switch (option) {
            case 1:
                while(true){
                    if(signupStaffAccount(console)) {
                        break;
                    }
                }
                return true;
            case 2:
                while(true){
                    if(removeStaffAccount(console)) {
                        break;
                    }
                }
                return true;
            case 3:
                while(true){
                    if(listStaff(console)) {
                        break;
                    }
                }
                return true;
            case 4:
                return true;

        }
        return false;
    }

    /**
     * This is a function to signup a staff account. This can only be ran be a staff user.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean signupStaffAccount(Scanner console) {
        System.out.println("Please enter email for new User");
        String email = console.nextLine();
        System.out.println("Please enter username for new User");
        String username = console.nextLine();
        System.out.println("Please enter temporary password");
        String password = getPassword(console);
        LoginAdapter signup = new LoginAdapter(new Signup());
        signup.setName(username);
        signup.setType("Staff");
        signup.login(email, password);
        return true;
    }

    /**
     * This is a function to remove a staff account. This allows staff to remove staff accounts in the database.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean removeStaffAccount(Scanner console) {
        CommandInvoker invoker = new CommandInvoker();
        System.out.println("Please enter email of User to remove");
        String email = console.nextLine();
        invoker.setCommand(new GetAllStaffUsersCommand());
		invoker.execute();
        List<UserInterface> result = invoker.getResponse();
        for(UserInterface i : result) {
            if(Objects.equals(i.getEmail(), email)) {
                invoker.setCommand(new RemoveUserCommand(i));
                invoker.execute();
                System.out.println("User with email " + email + " has been removed.");
                return true;
            }
        }
        return true;
    }

    /**
     * This is a function to list all staff accounts rooms.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean listStaff(Scanner console) {
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(new GetAllStaffUsersCommand());
		invoker.execute();
        List<UserInterface> result = invoker.getResponse();
        for(UserInterface i : result) {
            System.out.println(i.getEmail());
        }
        return true;
    }

    /**
     * This is a function to manage vouchers. This allows staff to add and remove vouchers for users in the database.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean manageVouchers(Scanner console) {
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Add Voucher");
        System.out.println("2. \t Remove Voucher");
        System.out.println("3. \t Update Voucher");
        System.out.println("4. \t Back");
        int option = Integer.parseInt(console.nextLine());
        System.out.println("\n####################################################\n");
        switch (option) {
            case 1:
                while(true){
                    if(createVoucher(console)) {
                        break;
                    }
                }
                return true;
            case 2:
                while(true){
                    if(removeVoucher(console)) {
                        break;
                    }
                }
                return true;
            case 3:
                while(true){
                    if(updateVoucher(console)) {
                        break;
                    }
                }
                return true;
            case 4:
                return true;

        }
        return false;
    }

    /**
     * This is a function to create new vouchers. This allows staff to create new vouchers with specified code for users in the database.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean createVoucher(Scanner console) {
        CommandInvoker invoker = new CommandInvoker();
        CouponVisitor visitor = new CouponVisitor();
        visitor.TypeSet("Voucher");  
        System.out.println("Please enter discount amount");
        double discount ;
        try {
            discount = Double.parseDouble(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        visitor.DiscountSet(discount);
        invoker.setCommand(new CreateVoucherCommand(visitor));
		invoker.execute();
        CouponVisitor result = invoker.getResponse();
        System.out.println("Your voucher has been created with code : " +result.CodeGet());
        return true;
    }

    /**
     * This is a function to removes vouchers. This allows staff to remove vouchers for users in the database.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean removeVoucher(Scanner console) {
        CommandInvoker invoker = new CommandInvoker();
        System.out.println("Please enter voucher code to remove");
        String code = console.nextLine();
        CouponVisitor visitor = new CouponVisitor(code);
        invoker.setCommand(new ValidateVoucherCommand(visitor));
        invoker.execute();

        CouponVisitor result = invoker.getResponse();
        invoker.setCommand(new RemoveVoucherCommand(result));
		invoker.execute();
        return true;
    }

    /**
     * This is a function to update vouchers. This allows staff to update already created vouchers for users in the database.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static boolean updateVoucher(Scanner console) {
        CommandInvoker invoker = new CommandInvoker();
        System.out.println("Please enter voucher code to update");
        String code = console.nextLine();
        System.out.println("Please enter new discount");
        double amount = Double.parseDouble(console.nextLine());
        CouponVisitor visitor = new CouponVisitor(code);
        visitor.DiscountSet(amount);
        invoker.setCommand(new UpdateVoucherCommand(visitor));
        invoker.execute();

        CouponVisitor result = invoker.getResponse();
        invoker.setCommand(new RemoveVoucherCommand(result));
		invoker.execute();
        System.out.println("Your voucher has been update with code : " +result.CodeGet() + " and discount : " +result.DiscountGet());
        return true;
    }

	public static String getPassword(Scanner console)
	{
		Console passwordConsole = System.console();
		if (passwordConsole == null)
		{
			return console.nextLine();
		}
		else
		{
			return String.valueOf(passwordConsole.readPassword());
		}
	}
}
