package system;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import db.Products;
import db.User;
import logic.Login;
import view.SystemInput;
import view.SystemView;

import static db.Products.*;

public class MartPosSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setInitProductsSize(10);
		List<Products> products = generateInitProducts();

//		Products products = new Products(null, 0, 0, false, null);
		User user = new User();
		Login login = new Login();
		LocalDateTime currentTime;
//		Sale sale = new Sale(null, 0, 0, false, null);
//		ExpirationDate ed = new ExpirationDate(null, 0, 0, false, null);
		Scanner scanner = new Scanner(System.in);
		boolean logined = login.isLogined();
		boolean worked = true;
		boolean mainBack = false;

		SystemView.startView();
		SystemView.loginView();
		logined = login.isLogined();

		while(!logined) {
			System.out.println("══════════════════════════════════════════");
			System.out.println("              ID를 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			String inputId = scanner.nextLine().trim();
			System.out.println("══════════════════════════════════════════");
			System.out.println("           PASSWORD를 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			String inputPW = scanner.nextLine().trim();
			login.checkUserInfo(inputId, inputPW); // 유저 정보 체크
			logined = login.isLogined();

			if (!logined) {
				System.out.println("══════════════════════════════════════════");
				System.out.printf("%16s로그인 실패%-16s\n","","");
				System.out.println("══════════════════════════════════════════");
			}
		}

		while (worked) {
			currentTime = LocalDateTime.now();
			user.setLoginTime(currentTime);
			SystemView.mainMenuView();
			int inputValue = SystemInput.inputNumberBetweenBy(1, 6);
			if (inputValue == 1) {
				showProducts(products);
			} else if (inputValue == 2) {
				SystemView.balanceView();
//				showBalance();
			} else if (inputValue == 3) {
//				showSale();
			} else if (inputValue == 4) {
//				showRemainingTime();
			} else if (inputValue == 5) {
				mainBack = false;
				while (!mainBack) {
					SystemView.subMenuView();
					inputValue = SystemInput.inputNumberBetweenBy(1, 5);
					switch (inputValue) {
						case 1:
//							saleMenu();
							break;
						case 2:
//							refundCard();
							break;
						case 3:
							break;
						case 4:
							searchProductByName();
						case 5:
							System.out.println("══════════════════════════════════════════");
							System.out.println("              메인 메뉴로 돌아갑니다.");
							System.out.println("══════════════════════════════════════════");
							mainBack = true;
							break;
						default:
							System.out.println("══════════════════════════════════════════");
							System.out.println("           올바른 메뉴 번호를 입력해주세요.");
							System.out.println("══════════════════════════════════════════");
							break;
					}
				}
			} else if (inputValue == 6) {
				currentTime = LocalDateTime.now();
				user.setLogOutTime(currentTime);

				int minutes1 = user.getLoginTime().getMinute();
				int minutes2 = user.getLogOutTime().getMinute();
				int dailyWagePerMinute = 9_800;
				int workTime = minutes2 - minutes1;
				user.setDailyWage((minutes2 - minutes1) * dailyWagePerMinute);

				System.out.println("══════════════════════════════════════════");
				System.out.printf("               일한 시간 : %d 분\n",workTime);
				System.out.println("══════════════════════════════════════════");
				System.out.println("══════════════════════════════════════════");
				System.out.printf("               일당 : %s (원)\n",user.getDailyWage());
				System.out.println("══════════════════════════════════════════");
				printProductStar();
				worked = false;
			}
		}
	}
}