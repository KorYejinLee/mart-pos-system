package view;

import java.util.Scanner;

public class SystemInput {
    public static int inputNumberBetweenBy(int start, int end) {
        Scanner sc = new Scanner(System.in);
        int inputValue = 0;

        while(true) {
            try {
                System.out.println("══════════════════════════════════════════");
                System.out.println(            "메뉴 선택를 선택해주세요.");
                System.out.println("══════════════════════════════════════════");
                inputValue = Integer.parseInt(sc.nextLine().trim()); // 엔터 체크
                if (inputValue < 1 || inputValue > 6) {
                    System.out.println("══════════════════════════════════════════");
                    System.out.println(String.format("         %d ~ %d 사이의 숫자를 입력해주세요.",start,end));
                    System.out.println("══════════════════════════════════════════");

                } else {
                    break; // 올바른 값이면 반복문 종료
                }
            } catch (NumberFormatException e) {
                System.out.println("══════════════════════════════════════════");
                System.out.println("              숫자를 입력해주세요.");
                System.out.println("══════════════════════════════════════════");
                sc.nextLine().trim(); // 입력 버퍼를 비워준다
            }
        }

        return inputValue;
    }
}
