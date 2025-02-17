package logic;

import db.User;

import java.time.LocalDateTime;

public class Login {

	User user = User.getInstance();
	private boolean isLogined = false;
	private LocalDateTime currentTime = LocalDateTime.now();

	private static void showCommentsOf(String comments) {
		System.out.println("══════════════════════════════════════════");
		System.out.printf(comments, "", "");
		System.out.println("══════════════════════════════════════════");
	}

	public boolean ckeckUserInfoFrom(String userId, String userPW) {
		if (user.getUserId().equals(userId)) {
			if (user.getUserPW().equals(userPW)) {
				showLoginSuccessComments();
				setLoginTimeTo(currentTime);
				this.isLogined = true;
			} else if (!user.getUserPW().equals(userPW)) {
				showCommentsOf("%13s비밀번호가 틀렸습니다.%-13s\n");
				this.isLogined = false;
			}

		}

		else if (!user.getUserId().equals(userId)) {
			if (user.getUserPW().equals(userPW)) {
				showCommentsOf("%10s찾을 수 없는 사용자 입니다.%-10s\n");
			} else if (!user.getUserPW().equals(userPW)) {
				showCommentsOf("%10s찾을 수 없는 사용자 입니다.%-10s\n");
			} this.isLogined = false;
		}
		return this.isLogined;
	}

	private void showLoginSuccessComments() {
		System.out.println("══════════════════════════════════════════");
		System.out.printf("%10s%s님께서 로그인 되었습니다.%-10s\n","",user.getUserName(),"");
		System.out.println("══════════════════════════════════════════");
	}

	public boolean isLogined() {
		return isLogined;
	}

	public LocalDateTime setLoginTimeTo(LocalDateTime currentTime) {
		User.getInstance().setLogInTime(currentTime);
		return currentTime;
	}
}
