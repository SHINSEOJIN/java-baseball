package baseball;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");

        List<Integer> computerNumbers = generateRandomNumbers();
        System.out.println("컴퓨터 숫자: " + computerNumbers); // 테스트 출력용

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("숫자를 입력해주세요 : ");
            String userInput = scanner.nextLine();

            validateInput(userInput);

            List<Integer> userNumbers = convertInputToNumbers(userInput);
            checkResult(computerNumbers, userNumbers);

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
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

        // 입력값에서 공백 제거
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
            numbers.add(Character.getNumericValue(c));
        }
        return numbers;
    }

    public static void checkResult(List<Integer> computer, List<Integer> user) {
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
        } else if (strikes > 0 || balls > 0) {
            System.out.println(balls + "볼 " + strikes + "스트라이크");
        } else {
            System.out.println("미스");
        }
    }
}
