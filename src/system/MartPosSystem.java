package system;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import db.Products;
import db.User;
import logic.Login;

import static db.Products.*;
import static view.SystemInput.inputNumberBetweenBy;
import static view.SystemView.*;

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

		showSystemStartComments();
		showLoginComments();
		isUserlogined = login.isLogined();

		while(!isUserlogined) {
			showInputIdComments();
			String inputId = scanner.nextLine().trim();
			showInputPasswordComments();
			String inputPW = scanner.nextLine().trim();
			login.checkUserInfo(inputId, inputPW); // 유저 정보 체크
			isUserlogined = login.isLogined();
			if (!isUserlogined) {
				showOnLoginFailureComments();
			}
		}

		while (isWorking) {
			currentTime = LocalDateTime.now();
			user.setLoginTime(currentTime);
			showMainMenuOptions();
			int mainMenuInput = inputNumberBetweenBy(1, 6);
			if (mainMenuInput == 1) {
				showProducts(products);
			} else if (mainMenuInput == 2) {
				showBalanceComments();
//				showBalance();
			} else if (mainMenuInput == 3) {
//				showSale();
			} else if (mainMenuInput == 4) {
//				showRemainingTime();
			} else if (mainMenuInput == 5) {
				isMainMenuReturned = false;
				while (!isMainMenuReturned) {
					showSubMenuOptions();
					int subMenuInput = inputNumberBetweenBy(1, 5);
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
							backToMainMenuComments();
							isMainMenuReturned = true;
							break;
						default:
							writeCorrectMenuNumbersComments();
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