/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newebank;

/**
 *
 * @author DELL
 */
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class NewEbank {

    public static Scanner sc = new Scanner(System.in);

    public static String GetLanguage() {
        String choice;
        System.out.println("-------Login Program-------\n"
                + "1. Vietnamese\n"
                + "2. English\n"
                + "3. Exit");
        System.out.print("Please choice one option: ");
        choice = sc.nextLine();
        switch (choice.replace(" ", "")) {
            case "1":
                return "vi";
            case "2":
                return "en";
            case "3":
                return "exit";
            default:
                return GetLanguage();
        }

    }

    public static void CheckAcc(ResourceBundle rc, String acc) {
        if (acc.isBlank()) {
            System.out.println(rc.getString("acc_error"));
            GetAcc(rc);
            return;
        }
        if (acc.replace(" ", "").length() != 10) {
            System.out.println(rc.getString("acc_error"));
            GetAcc(rc);
            return;
        }
        for (int i = 0; i < 10; i++) {
            int temp = acc.charAt(i);
            //System.out.println(temp);
            if (temp > 57 || temp < 48) {
                System.out.println(rc.getString("acc_error"));
                GetAcc(rc);
                return;
            }
        }
    }

    public static void CheckPass(ResourceBundle rc, String pass) {
        if (pass.isBlank()) {
            System.out.println(rc.getString("pass_error"));
            GetPass(rc);
            return;
        }
        if (pass.length() < 8 || pass.length() > 31) {
            System.out.println(rc.getString("pass_error"));
            GetPass(rc);
            return;
        }
        int num = 0;
        for (int i = 0; i < pass.replace(" ", "").length(); i++) {
            int temp = pass.replace(" ", "").charAt(i);

            if (temp > 57 || temp < 48) {
                num++;
            }
        }
        if (num == pass.replace(" ", "").length() || num == 0) {
            System.out.println(rc.getString("pass_error"));
            GetPass(rc);
        }
    }

    public static void GetAcc(ResourceBundle rc) {
        System.out.print(rc.getString("acc"));
        String acc = sc.nextLine();
        CheckAcc(rc, acc);
    }

    public static void GetPass(ResourceBundle rc) {
        System.out.print(rc.getString("pass"));
        String pass = sc.nextLine();
        CheckPass(rc, pass);
    }

    public static String generateCaptcha() {
        String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy1234567890";
        int charsToPrint = 5;
        char[] chars = elegibleChars.toCharArray();
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < charsToPrint; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
            char characterToShow = chars[randomIndex];
            finalString.append(characterToShow);
        }
        System.out.println("CaptCha: "+finalString.toString());
        return finalString.toString();
    }
    
    public static void CheckCaptcha(ResourceBundle rc, String Captcha){
        System.out.print(rc.getString("enter_captcha"));
        String tempcaptcha=sc.nextLine();
        if (tempcaptcha.isBlank()) {
            System.out.println(rc.getString("captcha_error"));
            return;
        }
        if (!tempcaptcha.equals(Captcha)) {
            System.out.println(rc.getString("captcha_error"));
            CheckCaptcha(rc, generateCaptcha());
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String language = GetLanguage();
        if ("exit".equals(language)) {
            System.exit(0);
        }
        Locale locale = new Locale(language);
        ResourceBundle resource = ResourceBundle.getBundle("Language.language", locale);
        GetAcc(resource);
        GetPass(resource);
        CheckCaptcha(resource, generateCaptcha());
        System.out.println(resource.getString("success"));
    }

}
