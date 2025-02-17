package db;

import java.time.LocalDateTime;

public class User {
	private String userName = "이씨유"; // 사용자 이름
	private String userId = "cu"; // 사용자 id
	private String userPW = "1234"; // 사용자 password
	private static User instance;
	private LocalDateTime LogInTime;
	private int dailyWage; // 일당
	private LocalDateTime LogOutTime;

	private User() {
	}

	public static User getInstance() {
		if (instance == null) {
			instance = new User();
		}
		return instance;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public void setLogOutTime(LocalDateTime logOutTime) {
		LogOutTime = logOutTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setDailyWage(int dailyWage) {
		this.dailyWage = dailyWage;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPW() {
		return userPW;
	}

	public LocalDateTime getLogInTime() {
		return LogInTime;
	}

	public void setLogInTime(LocalDateTime logInTime) {
		LogInTime = logInTime;
	}

	public LocalDateTime getLogOutTime() {
		return LogOutTime;
	}

	public int getDailyWage() {
		return dailyWage;
	}
}