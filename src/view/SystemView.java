package view;

public class SystemView {
	public static void showLoginComments() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║                 로그인                  ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
	}

	public static void showSystemStartComments() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║            Mart POS System             ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
	}

    public static void showInputIdComments() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("              ID를 입력해주세요.");
        System.out.println("══════════════════════════════════════════");
    }

    public static void showInputPasswordComments() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("           PASSWORD를 입력해주세요.");
        System.out.println("══════════════════════════════════════════");
    }

    public static void showOnLoginFailureComments() {
        System.out.println("══════════════════════════════════════════");
        System.out.printf("%16s로그인 실패%-16s\n","","");
        System.out.println("══════════════════════════════════════════");
    }
	
	public static void showBalanceComments() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║                Balance                 ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
	}
	
	public static void showMainMenuOptions() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║                Main Menu               ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("══════════════════════════════════════════");
        System.out.println("      [1] 재고 체크\t[2] 현재 잔고 체크");
        System.out.println("      [3] 매출액\t\t[4] 유통기한");
        System.out.println("      [5] 업무 시작\t[6] 업무 종료");
        System.out.println("══════════════════════════════════════════");
	}

    public static void backToMainMenuComments() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("              메인 메뉴로 돌아갑니다.");
        System.out.println("══════════════════════════════════════════");
    }

    public static void writeCorrectMenuNumbersComments() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("           올바른 메뉴 번호를 입력해주세요.");
        System.out.println("══════════════════════════════════════════");
    }
	
	public static void showSubMenuOptions() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║             Start Work Menu            ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("══════════════════════════════════════════");
        System.out.println("        [1] 물건 판매");
        System.out.println("        [2] 물건 환불");
        System.out.println("        [3] 믈픔 입고 화면");
        System.out.println("        [4] 물품 재고 및 제품 이름 검색");
        System.out.println("        [5] 돌아가기");
        System.out.println("══════════════════════════════════════════");
	}
	
	public static void showSaleProductMenuOptions() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║              Sale Product              ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("══════════════════════════════════════════");
        System.out.println("        [1] 카드 [2] 현금 [3] 돌아가기");
        System.out.println("══════════════════════════════════════════");
	}
}


