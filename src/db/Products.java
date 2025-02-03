package db;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Products {
    private static Products[] products;
    private static int size; // 객체 배열 Product 크기
    private static LocalDateTime NO_EXPIRATION = LocalDateTime.MAX;
    private String productName; // 물품 이름
    private int productPrice; // 물품 가격
    private int productAmount; // 물품 갯수
    private boolean isProhibited; // 19금 물품 유무
    private LocalDateTime expirationDate;
// 유통기한

    // 생성자를 private화 하여 외부에서 생성자 호출 차단
    private Products(String productName, int productPrice, int productAmount, boolean isProhibited, LocalDateTime expirationDate) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.isProhibited = isProhibited;
        this.expirationDate = expirationDate;
    }

    // 정적 팩토리 메소드
    public static Products of(String name, int price, int amount, boolean prohibited, LocalDateTime expirationDate) {
        return new Products(name, price, amount, prohibited, expirationDate);
    }

    public static List<Products> generateInitProducts() {
        return List.of(
                Products.of("술", 2100, 10, true, NO_EXPIRATION),
                Products.of("우유", 4500, 10, true, LocalDateTime.now().plusDays(1)),
                Products.of("어묵", 2500, 10, false, LocalDateTime.now().plusHours(1)),
                Products.of("두부", 1000, 10, false, LocalDateTime.now().plusDays(1)),
                Products.of("담배", 1500, 10, false, NO_EXPIRATION),
                Products.of("과자", 1700, 10, false, NO_EXPIRATION),
                Products.of("컵라면", 1600, 10, false, NO_EXPIRATION),
                Products.of("머리끈", 1000, 10, false, NO_EXPIRATION),
                Products.of("물티슈", 1200, 10, false, NO_EXPIRATION),
                Products.of("우산", 8000, 10, false, NO_EXPIRATION)
        );
    }

    public static void setInitProductsSize(int setSize) {
        size = setSize;
        products = new Products[size];
    }

    public static void settingSize() {
        size++;
        products = new Products[size];
    }

    public static Products[] getProducts() {
        return products;
    }

    public static void setProducts(Products[] products) {
        Products.products = products;
    }

    public static void showProducts(List<Products> products) {
        // TODO 10s를 15s로 바꾸는 과정에서 불편함을 느낌 -> 변경사항을 적게 하기 위해서 어떻게 해야할까?
        // TODO 줄 간격 맞추기
        System.out.println("════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t\n","상품명","상품가격","상품갯수","청소년 판매여부","유통기한");
        System.out.println("════════════════════════════════════════════════════════════════════════════════════");
        for (Products product : products) {
            System.out.println(product);
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════");

    }

    public static void printProductStar() {
        System.out.println("══════════════════════════════════════════");
        for (Products products : products) {
            System.out.print(products.getProductName() + " ");
            for (int i = 0; i < products.getProductAmount(); i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("══════════════════════════════════════════");
    }

    public static void searchProductByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("══════════════════════════════════════════");
        System.out.println("           검색할 상품명을 입력하세요: ");
        System.out.println("══════════════════════════════════════════");
        String searchName = scanner.nextLine();
        boolean found = false;

        for (Products products : products) {
            if (products.getProductName().equalsIgnoreCase(searchName)) {
                System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t\n","상품명","상품가격","상품갯수","청소년 판매여부","유통기한");
                System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                System.out.println(products);
                System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("══════════════════════════════════════════");
            System.out.println("         해당 상품이 존재하지 않습니다.");
            System.out.println("══════════════════════════════════════════");
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public boolean isProhibited() {
        return isProhibited;
    }

    public void setProhibited(boolean isProhibited) {
        this.isProhibited = isProhibited;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String formattedDate = (expirationDate != NO_EXPIRATION) ? expirationDate.format(formatter) : "유통기한 없음";
        return String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t",
                this.productName,
                this.productPrice,
                this.productAmount,
                this.isProhibited ? "청소년 판매불가" : "청소년 판매가능", formattedDate);
    }
}
