package system;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import db.Products;
import db.User;
import logic.DailyWage;
import logic.Login;
import logic.Logout;

import static db.Products.*;
import static view.SystemInput.inputNumberBetweenBy;
import static view.SystemView.*;

public class MartPosSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = User.getInstance();
		Login login = new Login();
		Logout logout = new Logout();
		DailyWage dailyWage = new DailyWage();

		setInitProductsSize(10);
		List<Products> products = generateInitProducts();

//		Products products = new Products(null, 0, 0, false, null);
//		User user = new User();
//		Sale sale = new Sale(null, 0, 0, false, null);
//		ExpirationDate ed = new ExpirationDate(null, 0, 0, false, null);
		Scanner scanner = new Scanner(System.in);
		boolean isUserlogined = login.isLogined();
		boolean isWorking = true;
		boolean isMainMenuReturned = false;

		showSystemStartComments();
		showLoginComments();
		isUserlogined = login.isLogined();

		while(true) {
			showInputIdComments();
			String inputUserId = scanner.nextLine().trim();
			showInputPasswordComments();
			String inputUserPassword = scanner.nextLine().trim();
			login.ckeckUserInfoFrom(inputUserId, inputUserPassword);
			isUserlogined = login.isLogined();
			if (!isUserlogined) {
				showOnLoginFailureComments();
			} else {
				break;
			}
		}

		while (isWorking) {
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
				isWorking = false;
				logout.setLogOutTimeTo(LocalDateTime.now());
				dailyWage.calculateDailyWageFrom(user.getLogInTime(), user.getLogOutTime());
//				printProductStar();
			}
		}
	}
}