package baseball;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");

        List<Integer> computerNumbers = generateRandomNumbers();
        System.out.println("컴퓨터 숫자: " + computerNumbers); // (테스트용, 나중에 지워야 해)
    }

    public static List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while (numbers.size() < 3) {
            int randomNumber = random.nextInt(9) + 1; // 1~9 숫자 생성
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }
}