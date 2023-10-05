package validate;

import java.util.Scanner;

public class Validate {
    static Scanner input = new Scanner(System.in);
    public static String IDRegex = "[A-Za-z0-9]{3,10}";
    public static String NameRegex = "^[a-zA-ZÀ-ỹ ]{4,40}$";
    public static String PriceRegex = "[0-9]{5}";
    public static String QuantityRegex = "[0-9]{2}";
    public static String choiceRegex = "[0-9]";

    public static int checkChoice() {
        String choice = "";
        while (true) {
            choice = input.nextLine();
            if (!choice.matches(choiceRegex)) {
                System.out.println("Nhập đúng lựa chọn trong menu");
            } else {
                break;
            }
        }
        return Integer.parseInt(choice);
    }

    public static String checkID() {
        String ID = "";
        while (true) {
            ID = input.nextLine();
            if (!ID.matches(IDRegex)) {
                System.out.println("Hãy nhập đúng định dạng ID");
            } else {
                break;
            }
        }
        return ID;
    }

    public static String checkName() {
        String Name = "";
        while (true) {
            Name = input.nextLine();
            if (!Name.matches(NameRegex)) {
                System.out.println("Hãy nhập đúng định dạng Tên");
            } else {
                break;
            }
        }
        return Name;
    }


    public static int checkQuantity() {
        String Quantity = "";
        while (true) {
            Quantity = input.nextLine();
            if (!Quantity.matches(QuantityRegex)) {
                System.out.println("Số lượng dưới 100");
            } else {
                break;
            }
        }
        return Integer.parseInt(Quantity);
    }

    public static double checkPrice() {
        String Price = "";
        while (true) {
            Price = input.nextLine();
            if (!Price.matches(PriceRegex)) {
                System.out.println("Nhập Đúng Giá: ");
            }else {
                break;
            }
        }
        return Double.parseDouble(Price);
    }
}
