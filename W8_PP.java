
import java.util.Scanner;

abstract class Question {
    String correct, answer;
    double points;

    Question(String correct, String answer, double points) {
        this.correct = correct;
        this.answer = answer;
        this.points = points;
    }

    abstract double grade();
}

class MCQ extends Question {
    MCQ(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        return answer.equals(correct) ? points : 0;
    }
}

class TF extends Question {
    TF(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        return answer.equals(correct) ? points : 0;
    }
}

class Essay extends Question {
    Essay(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        String[] keywords = correct.split(",");
        int matched = 0;

        for (String word : keywords) {
            if (answer.toLowerCase().contains(word.trim().toLowerCase())) {
                matched++;
            }
        }

        if (matched >= 2) return points * 0.75;
        if (matched == 1) return points * 0.50;
        return 0;
    }
}

public class W8_PP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] parts = line.split("\"");
            String type = parts[0].trim().split("\\s+")[0];
            String questionText = parts[1];
            String correct = parts[3];
            String answer = parts[5];
            String remaining = parts[6].trim();
            double points = Double.parseDouble(remaining);

            Question q;

            switch (type.toUpperCase()) {
                case "MCQ":
                    q = new MCQ(correct, answer, points);
                    break;
                case "TF":
                    q = new TF(correct, answer, points);
                    break;
                case "ESSAY":
                    q = new Essay(correct, answer, points);
                    break;
                default:
                    continue;
            }

            double score = q.grade();
            System.out.printf("%s: %.2f%n", type, score);
            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}