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

            validateInput(userInput); // 입력 검증

            System.out.println("입력한 숫자: " + userInput);

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // 랜덤으로 1~9 사이 서로 다른 숫자 3개 생성
    public static List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while (numbers.size() < 3) {
            int randomNumber = random.nextInt(9) + 1; // 1~9
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    // 입력 검증
    public static void validateInput(String input) {
        if (!input.matches("[1-9]+")) {
            throw new IllegalArgumentException("1~9까지 숫자만 입력할 수 있습니다.");
        }

        if (input.length() != 3) {
            throw new IllegalArgumentException("3자리 숫자를 입력해야 합니다.");
        }

        if (input.charAt(0) == input.charAt(1) ||
                input.charAt(0) == input.charAt(2) ||
                input.charAt(1) == input.charAt(2)) {
            throw new IllegalArgumentException("서로 다른 숫자를 입력해야 합니다.");
        }
    }
}
