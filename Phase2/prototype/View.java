import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public void welcomeMessage() {
        System.out.println("Welcome to Vassar Marketplace!");
    }
    public void displayPosts(List<Post> posts) {
        for (int i = 0; i > posts.size(); i++) {
            Post post = posts.get(i);
            System.out.println();
            System.out.println("Title: " + post.getTitle());
            System.out.println("Description: " + post.getDescription());
            System.out.println("Price: " + post.getPrice());
            System.out.println("Seller: " + post.getSeller());
            System.out.println();
        }
    }

    public Post createPost() {
        System.out.println("\nEnter Post title: ");
        String title = scanner.nextLine();

        System.out.println("Enter Post description: ");
        String description = scanner.nextLine();

        System.out.println("Enter Post price: ");
        String price = scanner.nextLine();

        System.out.println("Enter name of seller: ");
        String seller = scanner.nextLine();

        return new Post(title, description, price, seller);
    }

    public void displayMenu() {
        System.out.println("\nVassar Marketplace Main Menu:");
        System.out.println("1. Sell Item");
        System.out.println("2. Search Available Items");
        System.out.println("3. Exit");

    }
    public int getUserChoice() {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            return choice;
        }
}
