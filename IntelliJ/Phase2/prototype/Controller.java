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

    public void createPost() {
        Post newPost = view.createPost();
        model.addPost(newPost);
    }

    public void displayPosts() {
        List<Post> posts = model.getPosts();
        view.displayPosts(posts);
    }

    public void searchPosts() {
        String searchString = view.searchPosts().toUpperCase();
        List<Post> posts = model.getPosts();
        List<Post> matchingPosts = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);

            String title = post.getTitle().toUpperCase();
            String description = post.getDescription().toUpperCase();
            String price = post.getPrice().toUpperCase();
            String seller = post.getSeller().toUpperCase();

            if (title.contains(searchString) || description.contains(searchString) ||
                    price.contains(searchString) || seller.contains(searchString))
                    {
                matchingPosts.add(post);
            }
        }
        if (matchingPosts.size() == 0) {
            System.out.println("Sorry, no matches found.");
        }
        else { view.displayPosts(matchingPosts); }
    }

    public void startApp() {
        view.welcomeMessage();
    }

    public void runApp() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            String choice = view.getUserChoice();

            switch (choice) {
                case "1":
                    createPost();
                    break;
                case "2":
                    displayPosts();
                    break;
                case "3":
                    searchPosts();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}