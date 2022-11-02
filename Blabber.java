/* 
    Brandon Heber Ibarra
    [CS1101 - FA22] Comprehensive Lab 2
    This work is to be done individually. 
        It is not permitted to share, reproduce, or alter any part of this assignment for any purpose. 
        Students are not permitted from sharing code, uploading this assignment online in any form, or viewing/receiving/modifying code written from anyone else. 
        This assignment is part of an academic course at The University of Texas at El Paso and a grade will be assigned for the work produced individually by the student. 
*/
/* 
    CS1101 or CS1301
    Author: Brandon Ibara
    Blabber
    Making an application that resembles a social media platform. 
*/

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Blabber {
    //declare scanner as s so we can take user input 
    static Scanner s = new Scanner(System.in);
    //declare fileReader to have seperate scanner just for files
    static Scanner fileReader;
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("debug")) {
            for (String a :getUsers("Users.txt", numLines("Users.txt"))) {
                System.out.println(a);
            }
            //System.out.println(Character.isLetterOrDigit('"'));
            System.out.println(isAlphanumeric("\"", 0));
        }
        else {
            while(true){
                clear();
                System.out.println("Welcome to blabber!\nPlease use the following commands to sign in\n1. CreateAccount <uesrname>\n2. SignIn <username>");
                endTick();
                String[] command = commandHandler(s.nextLine());
                if(command.length > 2) command[0] = "TooMany";
                switch (command[0]) {
                    case "CreateAccount":
                        createAccount(command[1], getUsers("Users.txt", numLines("Users.txt")));
                        break;
                    case "SignIn":
                        cursorTop(command[1] + "sign in");
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    case "NoArg":
                        cursorTop("No parameters given for selected command\nPlease try again", 2500);
                        break;
                    case "TooMany":
                        cursorTop("Toomany parameters given\nPlease stay to `<command> <arg>`",2500);
                        break;
                    default:
                        cursorTop("Invalid command",2500);
                        break;
                }
            }
        }        
            //hold for testing purpose
            //s.nextLine();}
    }

    /*
        Method Name: commandHandler
        Return Type: String[]
        Parameters: String
        Description: split string into array for easier consumption
    */
    public static String[] commandHandler(String input){
        String[] splitString = input.split(" ");
        if(splitString.length < 2) splitString[0] = "NoArg";
        return splitString;
    }
    /*
        Method Name: signedIn
        Return Type: void
        Parameters: String, String[]
        Description: Optional method to display and run the methods when a user is signed in.(Otherwise you could have this on the main, up to personal preference
    */
    public static void signedIn(String username,String[] users){}
    /*
        Method Name: getUsers
        Return Type: String[]
        Parameters: String, int
        Description: Get the users from a text file and return them in an array
    */
    public static String[] getUsers(String filename,int size) throws IOException{
        String[] userArray = new String[size];
        if (userArray.length == 0) return userArray;
        fileReader = new Scanner(new File(filename)); 
        for (int i=0;i < size;i++) {
            userArray[i] = fileReader.nextLine();
        }
        fileReader.close();
        return userArray;
    }
    /*
        Method Name: numLines
        Return Type: int
        Parameters: String
        Description:  Get the number of lines in a given file or 0 if the file doesn’t exist.
    */
    public static int numLines(String fileName) throws IOException{
        int counter = 0;
        File _file = new File(fileName);
        if (!(_file.exists())) return counter;
        fileReader = new Scanner(_file); 
        while(fileReader.hasNextLine()) {
            fileReader.nextLine();
            counter++;
        }
        fileReader.close();
        return counter;
    }
    /*
        Method Name: isAlphanumeric
        Return Type: boolean
        Parameters: String, int
        Description: Check if a string is alphanumeric. Return true if it is. This must be recursive!
    */
    public static boolean isAlphanumeric(String username, int index) {
        boolean isAlphanumeric = true;
        if(index < username.length()-1) {
            if(!Character.isLetterOrDigit(username.charAt(index))) isAlphanumeric = false;
            else {
                index++;
                isAlphanumeric = isAlphanumeric(username, index);
            }
        }
        return isAlphanumeric;
    }
    /*
        Method Name: createAccount
        Return Type: void
        Parameters: String, String[]
        Description: This command creates an account with the specified username. 
            There may only be one account per username (no duplicate accounts for one username). 
            Users may not have the username "blabs". 
            Username creation should not be case sensitive- i.e. there can not be a user tyler and a different user TYLER. 
            Usernames must be alphanumeric (a-z, A-Z, 0-9) with a length > 1.
        Makes use of: accountExists, isAlphanumeric
    */
    public static void createAccount(String username,String[] users) throws IOException{
        if (accountExists(username, users)) cursorTop("Error: An account with username "+username+" already exists", 2500);
        else if (!isAlphanumeric(username, 0) || username.toLowerCase().equals("blabs") || !(username.length() > 1) ) cursorTop("Error: Invalid username.", 1500);
        else {
            FileWriter output = new FileWriter(new File("./userFollows/"+username+".txt"), false);
            output.close();
            output = new FileWriter(new File("Users.txt"), true);
            if (users.length == 0) output.write(username);
            else output.write("\n"+username);
            output.close();
            cursorTop("Success!", 2500);
        }
    }
    /*
        Method Name: accountExists
        Return Type: boolean
        Parameters: String, String[]
        Description: This method will return true if the account exists, false otherwise.
    */
     public static boolean accountExists(String username,String[] users){
        boolean exists = false;
        for (String user :users ) {
            if (user.toLowerCase().equals(username.toLowerCase())) exists = true; 
        }
        return exists;
     }
    /*
        Method Name: followAccount
        Return Type: void
        Parameters: String, String, String[]
        Description: This method will follow a user and write them to the signed in username text file.
        Makes use of: accountExists , checkFollowers
    */
    public static void followAccount(String username, String usernameToFollow,String[] users){}
    /*
        Method Name: checkFollowers
        Return Type: boolean
        Parameters: String, String
        Description: This method will check on the signed in username text file if usernameToFollow is already being followed. Return a boolean value.
    */
    // public static boolean checkFollowers(String username,String usernameToFollow){}
    /*
        Method Name: postBlab
        Return Type: void
        Parameters: String
        Description: Ask user to prompt a "blab" to be written in the blabs text file.
    */
    public static void postBlab(String username){}
    /*
        Method Name: getBlabs
        Return Type: String[]
        Parameters: null
        Description: Return all the blabs in an array.
    */
    // public static String[] getBlabs(){}
    /*
        Method Name: viewTimeline
        Return Type: void
        Parameters: String, Stringp[]
        Description: This method will use System.out.println for showing the contents of the array starting from the last element using recursion.
    */
    public static void viewTimeline(String username,String[] blabs,int idx){}
    /*
        Method Name: viewTimelineReverse
        Return Type: void
        Parameters: String, String[], int
        Description: Same as above but in reverse
    */
    public static void viewTimelineReverse(String username,String[] blabs,int idx){}



    /*
        Method Name: clear
        Return Type: void
        Parameters: null
        Description: This method clears out the console (keeps stuff clean)
    */
    static void clear() {
        //how does it work?
        // \033[H moves cursor to top left of console
        // \033[2J clears the screen from cursor to end of console
        // works in about every language, i almost always use it in my code
        System.out.print("\033[H\033[2J");
    }

    /*
        Method Name: endTick
        Return Type: void
        Parameters: null
        Description: This method moves the cursor to the end of the screen
    */
    static void endTick() {
        //how does it work?
        // \033[200H moves cursor to bottom of console
        // works in about every language, i almost always use it in my code
        System.out.print("\033[200H\033[F\033[2K\033[E");
        System.out.print("> ");
    }

    /*
        Method Name: cursorTop
        Return Type: void
        Parameters: String
        Description: This method moves the cursor to the top of the screen
    */
    static void cursorTop(String print) {
        //how does it work?
        // \033[200H moves cursor to bottom of console
        // works in about every language, i almost always use it in my code
        clear();
        System.out.print("\033[H");
        System.out.println(print);
        endTick();
    }
    /*
        Method Name: cursorTop
        Return Type: void
        Parameters: String, int
        Description: This method moves the cursor to the top of the screen
    */
    static void cursorTop(String print, int pause) {
        //how does it work?
        // \033[200H moves cursor to bottom of console
        // works in about every language, i almost always use it in my code
        clear();
        System.out.print("\033[H");
        System.out.println(print);
        endTick();
        pause(pause);
    }

    /*
        Method Name: hold
        Return Type: void
        Parameters: null
        Description: This method holds console still and not clear it until told so.
    */
    static void hold() {
        //how does it work?
        // just asks the scanner to wait for a input which isnt used in anything
        System.out.println("~^~^~[Press Enter to continue]~^~^~");
        s.nextLine();
    }

    /*
        Method Name: pause
        Return Type: void
        Parameters: time
        Description: This method make the process go sleep for determined time.
    */
    static void pause(int time) {
        //how does it work?
        // honestly kinda black magic for me but it pauses the thread which the program is running in
        try{Thread.currentThread().sleep(time);}
        catch(Exception _e) {_e.printStackTrace();}
    }

}