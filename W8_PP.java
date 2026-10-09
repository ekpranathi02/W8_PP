
import java.util.Scanner;

abstract class Payment {
    double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    abstract double calculate();

}

class Card extends Payment {
    Card(double amount) {
        super(amount);
    }

    double calculate() {
        return amount * 1.02;
    }
}

class Wallet extends Payment {
    Wallet(double amount) {
        super(amount);
    }

    double calculate() {
        return amount * 1.01;
    }
}

class BankTransfer extends Payment {
    BankTransfer(double amount) {
        super(amount);
    }

    double calculate() {
        return amount;
    }
}

public class W8_PP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double amount = sc.nextDouble();
            Payment p;

            switch (type) {
                case "CARD":
                    p = new Card(amount);
                    break;
                case "WALLET":
                    p = new Wallet(amount);
                    break;
                case "BANKTRANSFER":
                    p = new BankTransfer(amount);
                    break;
                default:
                    continue;
            }

            double adjusted = p.calculate();
            System.out.printf("%s: %.2f%n", type, adjusted);
            total += adjusted;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}