package baseball;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");

        // 1. 컴퓨터 숫자 3개 생성
        List<Integer> computerNumbers = generateRandomNumbers();
        System.out.println("컴퓨터 숫자: " + computerNumbers); // 테스트 출력

        // 2. 사용자 입력 받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("숫자를 입력해주세요 : ");
        String userInput = scanner.nextLine(); // 사용자 입력 저장
        System.out.println("입력한 숫자: " + userInput); // 테스트 출력
    }

    // 랜덤으로 1~9 사이 서로 다른 숫자 3개 생성
    public static List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while (numbers.size() < 3) {
            int randomNumber = random.nextInt(9) + 1; // 1~9
            if (!numbers.contains(randomNumber)) { // 중복 없으면 추가
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }
}