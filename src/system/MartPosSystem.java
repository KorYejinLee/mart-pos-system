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
		boolean isUserlogined = login.isLogined();
		boolean isWorking = true;
		boolean isMainMenuReturned = false;

		SystemView.startView();
		SystemView.loginView();
		isUserlogined = login.isLogined();

		while(!isUserlogined) {
			System.out.println("══════════════════════════════════════════");
			System.out.println("              ID를 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			String inputId = scanner.nextLine().trim();
			System.out.println("══════════════════════════════════════════");
			System.out.println("           PASSWORD를 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			String inputPW = scanner.nextLine().trim();
			login.checkUserInfo(inputId, inputPW); // 유저 정보 체크
			isUserlogined = login.isLogined();

			if (!isUserlogined) {
				System.out.println("══════════════════════════════════════════");
				System.out.printf("%16s로그인 실패%-16s\n","","");
				System.out.println("══════════════════════════════════════════");
			}
		}

		while (isWorking) {
			currentTime = LocalDateTime.now();
			user.setLoginTime(currentTime);
			SystemView.mainMenuView();
			int mainMenuInput = SystemInput.inputNumberBetweenBy(1, 6);
			if (mainMenuInput == 1) {
				showProducts(products);
			} else if (mainMenuInput == 2) {
				SystemView.balanceView();
//				showBalance();
			} else if (mainMenuInput == 3) {
//				showSale();
			} else if (mainMenuInput == 4) {
//				showRemainingTime();
			} else if (mainMenuInput == 5) {
				isMainMenuReturned = false;
				while (!isMainMenuReturned) {
					SystemView.subMenuView();
					int subMenuInput = SystemInput.inputNumberBetweenBy(1, 5);
					switch (subMenuInput) {
						case 1:
//							saleMenu();
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
							isMainMenuReturned = true;
							break;
						default:
							System.out.println("══════════════════════════════════════════");
							System.out.println("           올바른 메뉴 번호를 입력해주세요.");
							System.out.println("══════════════════════════════════════════");
							break;
					}
				}
			} else if (mainMenuInput == 6) {
				currentTime = LocalDateTime.now();
				user.setLogOutTime(currentTime);

				int loginTime = user.getLoginTime().getMinute();
				int logoutTime = user.getLogOutTime().getMinute();
				int dailyWagePerMinute = 9_800;
				int workTime = logoutTime - loginTime;
				user.setDailyWage(workTime * dailyWagePerMinute);

				System.out.println("══════════════════════════════════════════");
				System.out.printf("               일한 시간 : %d 분\n",workTime);
				System.out.println("══════════════════════════════════════════");
				System.out.println("══════════════════════════════════════════");
				System.out.printf("               일당 : %s (원)\n",user.getDailyWage());
				System.out.println("══════════════════════════════════════════");
				printProductStar();
				isWorking = false;
			}
		}
	}
}