public class Post {
    public String title;
    public String description;
    public String price;
    // public ??? pictures;
    public String seller;

    public Post(String title, String description, String price, String seller) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.seller = seller;
    }

    // Define methods for posts
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
}