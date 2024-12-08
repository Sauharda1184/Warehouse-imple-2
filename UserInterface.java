import java.util.Scanner;

public class UserInterface {
    private ClientList clientList;
    private ProductList productList;
    private Warehouse warehouse;

    public UserInterface(ClientList clientList, ProductList productList, Warehouse warehouse) {
        this.clientList = clientList;
        this.productList = productList;
        this.warehouse = warehouse;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Client");
            System.out.println("2. Add Product");
            System.out.println("3. Display Clients");
            System.out.println("4. Display Products");
            System.out.println("5. Add Item to Wishlist");
            System.out.println("6. Display Wishlist");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter client name: ");
                    String clientName = scanner.nextLine();
                    clientList.addClient(new Client(clientName));
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    productList.addProduct(new Product(productName, quantity, price));
                    break;
                case 3:
                    System.out.println(clientList);
                    break;
                case 4:
                    System.out.println(productList);
                    break;
                case 5:
                    System.out.print("Enter client name: ");
                    String clientNameForWishlist = scanner.nextLine();
                    Client client = clientList.getClient(clientNameForWishlist);
                    if (client != null) {
                        System.out.print("Enter product name to add to wishlist: ");
                        String productNameForWishlist = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int wishQuantity = scanner.nextInt();
                        Product productForWishlist = productList.getProduct(productNameForWishlist);
                        if (productForWishlist != null && productList.isProductAvailable(productNameForWishlist, wishQuantity)) {
                            client.addToWishlist(productForWishlist, wishQuantity);
                            System.out.println("Product added to wishlist.");
                        } else {
                            warehouse.addToWaitlist(productForWishlist, client, wishQuantity);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Wishlist:");
                    System.out.println(clientList.getClients());
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ClientList clientList = new ClientList();
        ProductList productList = new ProductList();
        Warehouse warehouse = new Warehouse();
        UserInterface ui = new UserInterface(clientList, productList, warehouse);
        ui.displayMenu();
    }
}
