    public class Book {
        private final String title;
        private final String author;
        private int quantity;

        // constructor that assigns values upon instantiation

        public Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }

        // getters and setters to access objects for assigning and retrieving values after created

        public String getTitle() {
            return title;
        }

        /* public void setTitle(String title) {
            this.title = title;
        } */  // not necessary for this program

        public String getAuthor() {
            return author;
        }

        /*public void setAuthor(String author) {
            this.author = author;
        }*/ // not necessary for this program

        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }