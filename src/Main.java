import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer answer = new StringBuffer();
        String start_system_st = sc.nextLine();
        String finish_system_st = sc.nextLine();
        String number = sc.nextLine();
        int start_system = Integer.parseInt(start_system_st);
        int finish_system = Integer.parseInt(finish_system_st);

        int buffer = 0;
        char[] Array = number.toCharArray();
        for (int i = Array.length - 1; i >= 0; i--) {
            double pow = Math.pow(start_system, Math.abs(i - Array.length + 1));
            if (Character.isDigit(Array[i])) {
                if ((int) Array[i] - 48 > start_system) {
                    System.out.println("Лёша, вырубай, всё сломалось");
                    return;
                }
                buffer += ((int) Array[i] - 48) * pow;
            } else {
                switch (Array[i]) {
                    case 'A':
                        buffer += 10 * pow;
                        break;
                    case 'B':
                        buffer += 11 * pow;
                        break;
                    case 'C':
                        buffer += 12 * pow;
                        break;
                    case 'D':
                        buffer += 13 * pow;
                        break;
                    case 'E':
                        buffer += 14 * pow;
                        break;
                    case 'F':
                        buffer += 15 * pow;
                        break;
                }
            }
        }
        while (buffer >= finish_system) {
            if (buffer % finish_system >= 10) {
                switch (buffer % finish_system) {
                    case 10:
                        answer.append("A");
                        break;
                    case 11:
                        answer.append("B");
                        break;
                    case 12:
                        answer.append("C");
                        break;
                    case 13:
                        answer.append("D");
                        break;
                    case 14:
                        answer.append("E");
                        break;
                    case 15:
                        answer.append("F");
                        break;
                }
            } else {
                answer.append(buffer % finish_system);

            }
            buffer /= finish_system;
        }
        if (buffer >= 10) {
            switch (buffer) {
                case 10:
                    answer.append("A");
                    break;
                case 11:
                    answer.append("B");
                    break;
                case 12:
                    answer.append("C");
                    break;
                case 13:
                    answer.append("D");
                    break;
                case 14:
                    answer.append("E");
                    break;
                case 15:
                    answer.append("F");
                    break;
            }
        } else {
            answer.append(buffer);
        }
        answer.reverse();
        System.out.println(answer);
    }
}