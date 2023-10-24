import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startApp() {
        view.welcomeMessage();
    }

    public void runApp() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            int choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    view.createPost();
                    break;
                case 2:
                    view.displayPosts(model.getPosts());
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void createPost() {
        Post newPost = view.createPost();
        model.addPost(newPost);
    }

    public void displayPosts() {
        List<Post> posts = model.getPosts();
        view.displayPosts(posts);
    }
}