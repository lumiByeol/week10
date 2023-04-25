import java.util.HashMap;
import java.util.Scanner;

public class Account {
    String login;
    String password;

    static HashMap<String, String> userDatabase = new HashMap<>();

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void checkLogin() throws WrongLoginException {
        System.out.println("Please type in your login: ");
        Scanner inputLogin = new Scanner(System.in);
        String userLogin = inputLogin.nextLine();

        if (userLogin != null && userLogin.length() < 20 && userLogin.length() > 5) {
            System.out.println("Login accepted");
            this.login = userLogin;
        } else {
            System.out.println("Unacceptable login");
            throw new WrongLoginException();
        }
    }

    public void checkPassword() throws WrongPasswordException {
        System.out.println("Please type in your password: ");
        Scanner inputPassword = new Scanner(System.in);
        Scanner inputPassword2 = new Scanner(System.in);
        String userPassword = inputPassword.nextLine();
        System.out.println("Please confirm your password: ");
        String passwordConfirm = inputPassword2.nextLine();

        if(userPassword.length() > 8 && userPassword.length() < 20 && userPassword.equals(passwordConfirm)){
            System.out.println("Password accepted");
            this.password = userPassword;
        }
        else {
            System.out.println("Unacceptable password");
            throw new WrongPasswordException();
        }
    }

    public void checkLoginAndPassword() throws WrongLoginOrPasswordException {
        System.out.println("Checking the database...");
        if(userDatabase.containsKey(this.login) && userDatabase.containsValue(this.password)){
            System.out.println("Success! You're signed in");
        }
        else {
            System.out.println("User not found. Please check your credentials");
            throw new WrongLoginOrPasswordException();
        }
    }

    public static void main(String[] args) {
        userDatabase.put("alice123", "whiterabbit");
        userDatabase.put("madHatter", "table_raven");
        userDatabase.put("redQueen420", "offWithTheirHeads");

        Account acc = new Account("", "");
        try{
            acc.checkLogin();
        }
        catch (WrongLoginException e){
            System.out.println("Error: " + e);
        }

        try{
            acc.checkPassword();
        }
        catch (WrongPasswordException e){
            System.out.println("Error: " + e);
        }

        try{
            acc.checkLoginAndPassword();
        }
        catch (WrongLoginOrPasswordException e){
            System.out.println("Error: " + e);
        }
    }
}
