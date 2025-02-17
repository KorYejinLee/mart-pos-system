package logic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import db.TotalSale;
import view.SystemInput;

import static db.Products.getProducts;
import static view.SystemView.showSaleProductMenuOptions;

public class Sale extends TotalSale {
	
//	public Sale(String _productName, int _productPrice, int _productAmount, boolean _isProhibited,
//			LocalDateTime _expirationDate) {
//		super(_productName, _productPrice, _productAmount, _isProhibited, _expirationDate);
//		// TODO Auto-generated constructor stub
//	}

	private boolean backSubMenued = false;
	private boolean isValidCardNumber = false;
	private boolean continueSale = true;
    private boolean continueRefund = true;
    private boolean hasRefund = true;
	private String [] cardNumes = {"1111", "2222", "3333"};
	private String [] cardNames = {"카카오뱅크", "국민은행", "신한은행"};
	public static String [] saleCardNumes = new String [100];
	public static String [] saleProductNames = new String [100];
	public static int [] saleProductAmountes = new int [100];
	private String CARD_REGEX = "\\d{4}-\\d{4}-\\d{8}";
	private String cardName;
	private String cardNum;

	private int saleIndex = 0;
	private Pattern pattern = Pattern.compile(CARD_REGEX);
	
	Scanner sc = new Scanner(System.in);
	
	public void saleMenu() {
		backSubMenued = false;
		isValidCardNumber = false;
		continueSale = true;
		while (!backSubMenued) {
			showSaleProductMenuOptions();
			int inputValue = SystemInput.inputNumberBetweenBy(1, 3);
			switch (inputValue) {
				case 1:
					saleCard();
					break;
				case 2:
					saleBill();
					break;
				case 3:
					backSubMenued = true;
					break;
			}
		}	
	}
	
	public void saleCard() {
		backSubMenued = false;
		continueSale = true;
        while(!isValidCardNumber) {
			System.out.println("══════════════════════════════════════════");
	        System.out.println("  카드 번호를 입력하세요 (1111-2222-33333333) : ");
	        System.out.println("══════════════════════════════════════════");
	        String cardNumber = sc.nextLine().trim();
	        
	        // 정규식으로 형식을 확인
	        Matcher matcher = pattern.matcher(cardNumber);
	        if (!matcher.matches()) {
	            System.out.println("══════════════════════════════════════════");
	            System.out.println("         카드 번호 형식이 올바르지 않습니다.");
	            System.out.println("══════════════════════════════════════════");
	        } else {
	        	String firstFourDigits = cardNumber.split("-")[0];
	        		for (int cardIndex = 0; cardIndex < cardNumes.length; ++cardIndex) {
	        			if (firstFourDigits.equals(cardNumes[cardIndex])) {
	        				saleCardNumes[saleIndex] = cardNumber;
	        				cardName = cardNames[cardIndex];
	        		        System.out.println("══════════════════════════════════════════");
	        		        System.out.println("            카드 이름:" + cardName);
	        		        System.out.println("══════════════════════════════════════════");
	        		        cardNum = cardNumber;
	        		        isValidCardNumber = true;
	        			}  
	        		} 
	        		if (isValidCardNumber == false) {
	        			System.out.println("══════════════════════════════════════════");
	    	            System.out.println("            알 수 없는 카드 번호입니다.");
	    	            System.out.println("══════════════════════════════════════════");
	        		}
	        }
        }
        
        while (continueSale) {
        	System.out.println("══════════════════════════════════════════");
            System.out.println("              상품명을 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			String inputProductName = sc.nextLine().trim();
			System.out.println("══════════════════════════════════════════");
            System.out.println("            구매할 갯수를 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			int inputProductAmount = Integer.parseInt(sc.nextLine().trim());
			boolean productFound = false;
			for (int i = 0; i < getProducts().length; ++i) {
				if (getProducts()[i] != null && getProducts()[i].getProductName().equals(inputProductName)) {
					if (inputProductAmount <= getProducts()[i].getProductAmount()) {
						if (getProducts()[i].getProductName().equals("술") || getProducts()[i].getProductName().equals("담배")) {
							System.out.println("══════════════════════════════════════════");
				            System.out.println("     생년월일을 입력해주세요. (예 : 20240616) : ");
							System.out.println("══════════════════════════════════════════");
							String birthday = sc.nextLine().trim();
							int birthYear = Integer.parseInt(birthday.substring(0, 4));
							int age = 2024 - birthYear + 1;
							if (age > 20) {
								System.out.println("══════════════════════════════════════════");
					            System.out.println("            성인 인증이 완료되었습니다. ");
								System.out.println("══════════════════════════════════════════");
								getProducts()[i].setProductAmount(getProducts()[i].getProductAmount() - inputProductAmount);
								addSale(inputProductAmount * getProducts()[i].getProductPrice());
								System.out.println("══════════════════════════════════════════");
								System.out.println(inputProductName + "가(이) " + inputProductAmount + "개 판매 되었습니다.");
								System.out.println("══════════════════════════════════════════");
								saleProductNames[saleIndex] = inputProductName;
								saleProductAmountes[saleIndex] = inputProductAmount;
								cardNumes[saleIndex] = cardNum;
								saleIndex++;
								productFound = true;
								break;
							} else {
								System.out.println("══════════════════════════════════════════");
					            System.out.println("       미성년자는 19금 물품을 구매할 수 없습니다.");
								System.out.println("══════════════════════════════════════════");
							}
						} else {
							getProducts()[i].setProductAmount(getProducts()[i].getProductAmount() - inputProductAmount);
							addSale(inputProductAmount * getProducts()[i].getProductPrice());
							System.out.println("══════════════════════════════════════════");
							System.out.println(inputProductName + "가(이) " + inputProductAmount + "개 판매 되었습니다.");
							System.out.println("══════════════════════════════════════════");
							saleProductNames[saleIndex] = inputProductName;
							saleProductAmountes[saleIndex] = inputProductAmount;
							cardNumes[saleIndex] = cardNum;
							saleIndex++;
							productFound = true;
							break;
						}
					} else {
						System.out.println("══════════════════════════════════════════");
			            System.out.printf("         %s의 재고는 %d(개) 입니다.\n",inputProductName, getProducts()[i].getProductAmount());
						System.out.println("══════════════════════════════════════════");
					}
				}
			}
			if (!productFound) {
				System.out.println("══════════════════════════════════════════");
	            System.out.println("           해당 상품을 찾을 수 없습니다.");
				System.out.println("══════════════════════════════════════════");
			}
			System.out.println("══════════════════════════════════════════");
            System.out.println("     계속해서 상품을 구매하시겠습니까? (Y/N)");
			System.out.println("══════════════════════════════════════════");
			String response = sc.nextLine().trim();
			if(!response.equalsIgnoreCase("Y")) {
				continueSale = false;
			}
        }
	}
	
	public void saleBill() {
		continueSale = true;
		while (continueSale) {
			System.out.println("══════════════════════════════════════════");
	        System.out.println("              상품명을 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			String inputProductName = sc.nextLine().trim();
			System.out.println("══════════════════════════════════════════");
	        System.out.println("            구매할 갯수를 입력해주세요.");
			System.out.println("══════════════════════════════════════════");
			int inputProductAmount = Integer.parseInt(sc.nextLine().trim());
			boolean productFound = false;
			for (int i = 0; i < getProducts().length; ++i) {
				if (getProducts()[i] != null && getProducts()[i].getProductName().equals(inputProductName)) {
					if (inputProductAmount <= getProducts()[i].getProductAmount()) {
						if (getProducts()[i].getProductName().equals("술") || getProducts()[i].getProductName().equals("담배")) {
							System.out.println("══════════════════════════════════════════");
				            System.out.println("     생년월일을 입력해주세요. (예 : 20240616) : ");
							System.out.println("══════════════════════════════════════════");
							String birthday = sc.nextLine().trim();
							int birthYear = Integer.parseInt(birthday.substring(0, 4));
							int age = 2024 - birthYear + 1;
							if (age > 20) {
								System.out.println("══════════════════════════════════════════");
					            System.out.println("            성인 인증이 완료되었습니다. ");
								System.out.println("══════════════════════════════════════════");
								System.out.println("══════════════════════════════════════════");
						        System.out.println("               현금을 입력해주세요.");
								System.out.println("══════════════════════════════════════════");
								int inputProductBill = Integer.parseInt(sc.nextLine().trim());
								if ( inputProductBill > getBalance() ) {
									System.out.println("══════════════════════════════════════════");
							        System.out.println("               잔고가 부족합니다.");
									System.out.println("══════════════════════════════════════════");
								} else if (inputProductBill < inputProductAmount * getProducts()[i].getProductPrice()) {
									System.out.println("══════════════════════════════════════════");
							        System.out.println("          현금이 구매할 금액보다 작습니다.");
									System.out.println("══════════════════════════════════════════");
								} else {
									int change = inputProductBill - (inputProductAmount * getProducts()[i].getProductPrice());
									System.out.println("══════════════════════════════════════════");
							        System.out.println("       거스름돈은" + change + "원 입니다." );
									System.out.println("══════════════════════════════════════════");
									getProducts()[i].setProductAmount(getProducts()[i].getProductAmount() - inputProductAmount);
									addSale(inputProductAmount * getProducts()[i].getProductPrice());
									addBalance(inputProductAmount * getProducts()[i].getProductPrice());
									System.out.println("══════════════════════════════════════════");
									System.out.println(inputProductName + "가(이) " + inputProductAmount + "개 판매 되었습니다.");
									System.out.println("══════════════════════════════════════════");
									productFound = true;
									break;
								}
								
							} else {
								System.out.println("══════════════════════════════════════════");
					            System.out.println("       미성년자는 19금 물품을 구매할 수 없습니다.");
								System.out.println("══════════════════════════════════════════");
							}
						} else {
							System.out.println("══════════════════════════════════════════");
					        System.out.println("               현금을 입력해주세요.");
							System.out.println("══════════════════════════════════════════");
							int inputProductBill = Integer.parseInt(sc.nextLine().trim());
							if ( inputProductBill > getBalance() ) {
								System.out.println("══════════════════════════════════════════");
						        System.out.println("               잔고가 부족합니다.");
								System.out.println("══════════════════════════════════════════");
							} else if (inputProductBill < inputProductAmount * getProducts()[i].getProductPrice()) {
								System.out.println("══════════════════════════════════════════");
						        System.out.println("          현금이 구매할 금액보다 작습니다.");
								System.out.println("══════════════════════════════════════════");
							} else {
								int change = inputProductBill - (inputProductAmount * getProducts()[i].getProductPrice());
								System.out.println("══════════════════════════════════════════");
						        System.out.println("       거스름돈은" + change + "원 입니다." );
								System.out.println("══════════════════════════════════════════");
								getProducts()[i].setProductAmount(getProducts()[i].getProductAmount() - inputProductAmount);
								addSale(inputProductAmount * getProducts()[i].getProductPrice());
								addBalance(inputProductAmount * getProducts()[i].getProductPrice());
								System.out.println("══════════════════════════════════════════");
								System.out.println(inputProductName + "가(이) " + inputProductAmount + "개 판매 되었습니다.");
								System.out.println("══════════════════════════════════════════");
								productFound = true;
								break;
							}
						}
					} else {
						System.out.println("══════════════════════════════════════════");
			            System.out.printf("         %s의 재고는 %d(개) 입니다.\n",inputProductName, getProducts()[i].getProductAmount());
						System.out.println("══════════════════════════════════════════");
					}
				}
			}
			if (!productFound) {
				System.out.println("══════════════════════════════════════════");
	            System.out.println("           해당 상품을 찾을 수 없습니다.");
				System.out.println("══════════════════════════════════════════");
			}
			System.out.println("══════════════════════════════════════════");
	        System.out.println("     계속해서 상품을 구매하시겠습니까? (Y/N)");
			System.out.println("══════════════════════════════════════════");
			String response = sc.nextLine().trim();
			if(!response.equalsIgnoreCase("Y")) {
				continueSale = false;
			}
	    }
	}
	public void refundCard() {
		continueRefund = true;
		isValidCardNumber = false;
		hasRefund = true;
	    Scanner sc = new Scanner(System.in);
	    while (!this.isValidCardNumber) {
	        System.out.println("══════════════════════════════════════════");
	        System.out.println("  카드 번호를 입력하세요 (1111-2222-33333333) : ");
	        System.out.println("══════════════════════════════════════════");
	        String cardNumber = sc.nextLine();
	        for (int i = 0; Sale.saleCardNumes[i] != null && i < Sale.saleCardNumes.length; ++i) {
	            if (cardNumber.equals(Sale.saleCardNumes[i])) {
	            	isValidCardNumber = true;
	            } else {
	            	System.out.println(Sale.saleCardNumes[i]);
	            }
	        }
	        if (!this.isValidCardNumber) {
	            System.out.println("══════════════════════════════════════════");
	            System.out.println("          일치하는 카드 번호가 없습니다.");
	            System.out.println("══════════════════════════════════════════");
	        }
	    }

	    while (this.continueRefund) {
            System.out.println("══════════════════════════════════════════");
	        System.out.println("              상품명을 입력해주세요.");
            System.out.println("══════════════════════════════════════════");
	        String inputProductName = sc.nextLine();
            System.out.println("══════════════════════════════════════════");
	        System.out.println("               갯수를 입력해주세요.");
            System.out.println("══════════════════════════════════════════");
	        int inputProductAmount = Integer.parseInt(sc.next());	        
	        boolean productFound = false;
	        for (int i = 0; i < getProducts().length; ++i) {
	            if (getProducts()[i] != null && getProducts()[i].getProductName().equals(inputProductName)) {
	                for (int j = 0; j < saleProductNames.length; ++j) {
	                    if (saleProductNames[j] != null && saleProductNames[j].equals(inputProductName)) {
	                        if (inputProductAmount <= saleProductAmountes[j]) {
	                            // Perform refund
								getProducts()[i].setProductAmount(getProducts()[i].getProductAmount() + inputProductAmount);
	                            minSale(inputProductAmount * getProducts()[i].getProductPrice());
	                            System.out.println("══════════════════════════════════════════");
	                            System.out.println(inputProductName + "가(이) " + inputProductAmount + "개 환불 되었습니다.");
	                            System.out.println("══════════════════════════════════════════");
	                            productFound = true;
	                            // Adjust sale product arrays
	                            for (int k = j; k < saleProductNames.length - 1; k++) {
	                            	saleProductNames[k] = saleProductNames[k + 1];
	                            	saleProductNames[k] = saleProductNames[k + 1];
	                            }
	                            saleProductNames[saleProductNames.length - 1] = null;
	                            saleProductAmountes[saleProductAmountes.length - 1] = 0;
	                        } else {
	                            System.out.println("══════════════════════════════════════════");
	                            System.out.printf("%s의 재고는 %d(개) 입니다.\n", inputProductName, getProducts()[i].getProductAmount());
	                            System.out.println("══════════════════════════════════════════");
	                        }
	                    }
	                }
	            }
	        }
	        
	        if (!productFound) {
                System.out.println("══════════════════════════════════════════");
	            System.out.println("          해당 상품을 찾을 수 없습니다.");
                System.out.println("══════════════════════════════════════════");
                this.continueRefund = false;
	        } else {
                System.out.println("══════════════════════════════════════════");
	            System.out.println("        계속해서 상품을 환불하시겠습니까? (Y/N): ");
                System.out.println("══════════════════════════════════════════");
	            String response = sc.nextLine().trim();
	            if (!response.equalsIgnoreCase("Y")) {
	                this.continueRefund = false;
	            }
	        }
	    }
	}
}
