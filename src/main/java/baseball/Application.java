package baseball;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        System.out.println("숫자 야구 게임을 시작합니다.");
        Scanner scanner = new Scanner(System.in);

        List<Integer> computerNumbers = generateRandomNumbers();
        boolean isGameOver = false;

        while (!isGameOver) {
            try {
                List<Integer> userNumbers = getUserNumbers(scanner);
                boolean isCorrect = checkResult(computerNumbers, userNumbers);
                if (isCorrect) {
                    isGameOver = !askRestart(scanner);
                    if (!isGameOver) {
                        computerNumbers = generateRandomNumbers(); // 새 게임 준비
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    public static List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        while (numbers.size() < 3) {
            int randomNumber = random.nextInt(9) + 1;
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    public static List<Integer> getUserNumbers(Scanner scanner) {
        System.out.print("숫자를 입력해주세요 : ");
        String input = scanner.nextLine().replace(" ", "");
        validateInput(input);
        return convertInputToNumbers(input);
    }

    public static void validateInput(String input) {
        List<String> errors = new ArrayList<>();

        if (!input.matches("[1-9]+")) {
            errors.add("1~9까지 숫자만 입력할 수 있습니다.");
        }

        if (input.length() != 3) {
            errors.add("3자리 숫자를 입력해야 합니다.");
        }

        if (input.length() == 3 && (input.charAt(0) == input.charAt(1) ||
                input.charAt(0) == input.charAt(2) ||
                input.charAt(1) == input.charAt(2))) {
            errors.add("서로 다른 숫자를 입력해야 합니다.");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(" / ", errors));
        }
    }

    public static List<Integer> convertInputToNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (char c : input.toCharArray()) {
            numbers.add(Character.getNumericValue(c));
        }
        return numbers;
    }

    public static boolean checkResult(List<Integer> computer, List<Integer> user) {
        int strikes = 0;
        int balls = 0;

        for (int i = 0; i < 3; i++) {
            if (user.get(i).equals(computer.get(i))) {
                strikes++;
            } else if (computer.contains(user.get(i))) {
                balls++;
            }
        }

        if (strikes == 3) {
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다!");
            return true;
        }

        if (strikes > 0 || balls > 0) {
            System.out.println(balls + "볼 " + strikes + "스트라이크");
        } else {
            System.out.println("미스");
        }
        return false;
    }

    public static boolean askRestart(Scanner scanner) {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = scanner.nextLine().trim();
        if (input.equals("1")) {
            return true;
        } else if (input.equals("2")) {
            return false;
        } else {
            throw new IllegalArgumentException("1 또는 2만 입력 가능합니다.");
        }
    }
}
