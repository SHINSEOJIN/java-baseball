package baseball;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Integer> computerNumbers = generateRandomNumbers();
            // System.out.println("컴퓨터 숫자: " + computerNumbers); // 테스트용

            boolean isGameEnd = false;
            while (!isGameEnd) {
                try {
                    System.out.print("숫자를 입력해주세요 : ");
                    String userInput = scanner.nextLine().trim();

                    validateInput(userInput);

                    List<Integer> userNumbers = convertInputToNumbers(userInput);
                    isGameEnd = checkResult(computerNumbers, userNumbers);

                } catch (IllegalArgumentException e) {
                    System.out.println("[ERROR] " + e.getMessage());
                }
            }

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String restartInput = scanner.nextLine().trim();

            if (restartInput.equals("1")) {
                // 새 게임 시작
                continue;
            } else if (restartInput.equals("2")) {
                // 게임 종료
                break;
            } else {
                System.out.println("[ERROR] 잘못된 입력입니다. 프로그램을 종료합니다.");
                break;
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

    public static void validateInput(String input) {
        List<String> errors = new ArrayList<>();
        String sanitizedInput = input.replace(" ", "");

        if (!sanitizedInput.matches("[1-9]+")) {
            errors.add("1~9까지 숫자만 입력할 수 있습니다.");
        }

        if (sanitizedInput.length() != 3) {
            errors.add("3자리 숫자를 입력해야 합니다.");
        }

        if (sanitizedInput.length() == 3 && (sanitizedInput.charAt(0) == sanitizedInput.charAt(1) ||
                sanitizedInput.charAt(0) == sanitizedInput.charAt(2) ||
                sanitizedInput.charAt(1) == sanitizedInput.charAt(2))) {
            errors.add("서로 다른 숫자를 입력해야 합니다.");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(" / ", errors));
        }
    }

    public static List<Integer> convertInputToNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (char c : input.toCharArray()) {
            if (c != ' ') {
                numbers.add(Character.getNumericValue(c));
            }
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
            return true; // 게임 종료
        } else if (strikes > 0 || balls > 0) {
            System.out.println(balls + "볼 " + strikes + "스트라이크");
        } else {
            System.out.println("미스");
        }
        return false; // 게임 계속 진행
    }
}
