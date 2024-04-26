import com.textio.TextIO;
import java.util.ArrayList;

// This program allows the user to enter books into the library, check them out, and return them.

/* NOTES: I started with arrays, but that didn't seem to be a very elegant solution. I set these up as objects because
it seemed the cleanest way to address the assignment. I know we technically haven't covered OOP in the text yet,
but I felt like I had to write too much confusing code without using objects. */


public class Main {
    public static void main(String[] args) {
        int menuChoice;
        ArrayList<Book> library = new ArrayList<>(); // this will hold all the added book objects
        do { // loops through the menu until the user wants to exit
            menuChoice = libraryMenu();
            switch (menuChoice) {
                case 1:
                    addBook(library);
                    break;
                case 2:
                    borrowBook(library);
                    break;
                case 3:
                    returnBook(library);
                    break;
                case 4:
                    System.out.println("\nThanks for visiting the library! Have a great day!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again! Please enter a number between 1 and 4.");
                    break;
            }
        } while (menuChoice != 4);
    }

    // this method displays the library menu and validates user's input to ensure no errors occur at runtime

    public static int libraryMenu() {
        int menuChoice;
        try {
            System.out.println("\nWelcome to your local library! What would you like to do today?");
            System.out.println("1: Add a book to the library");
            System.out.println("2: Borrow a book from the library");
            System.out.println("3: Return a book to the library");
            System.out.println("4: Exit the library");
            TextIO.put("Enter your choice: ");
            try {
                menuChoice = Integer.parseInt(TextIO.getlnString());
            } catch (NumberFormatException nfe) {
                System.out.println("Error! You entered a letter.\n");
                menuChoice = 0;
            }
            return switch (menuChoice) {
                case 1 -> 1;
                case 2 -> 2;
                case 3 -> 3;
                case 4 -> 4;
                default -> 0; // this allows the loop to run again and display the menu for accurate entry
            };

        } catch (Exception e) {
            System.out.println("Invalid input. Try again. Please enter a number between 1 and 4.\n");
            return 0;
        }
    }

    // this method uses a for loop to search to see if book is already in the db
    // if not, we will add a new entry into the library ArrayList
    // each input will check for empty values to ensure we don't save null items in the db

    public static void addBook(ArrayList<Book> library) {
        System.out.println("\nYou will now add a book to your library. Press enter to continue.");
        String tempTitle; // temporary holding spot to check and see if book already
        // exists in the library database
        int tempQuantity;
        String tempAuthor;
        TextIO.getlnString(); // clears buffer so we don't read data prematurely
        TextIO.put("Enter the title of the book: ");
        tempTitle = TextIO.getln();
        tempTitle = validateTitle(tempTitle);
        TextIO.put("Enter the quantity of the book: ");
        tempQuantity = TextIO.getInt();
        tempQuantity = validateQuantity(tempQuantity);
        TextIO.getlnString(); // clears buffer so we don't read data prematurely
        TextIO.put("Enter the author of the book: ");
        tempAuthor = TextIO.getln();
        tempAuthor = validateAuthor(tempAuthor);


        // checking to see if book is already in the library

        for (Book book : library) {
            if (book.getTitle().equals(tempTitle) && book.getAuthor().equals(tempAuthor)) {
                // since it already exists in the db, then we will just increment the quantity
                book.setQuantity(book.getQuantity() + tempQuantity);
                System.out.println("Thank you for donating more copies of " + tempTitle + " to the library!\n");
                return;
            }
        }

        // will execute if the book isn't found in the db

        library.add(new Book(tempTitle, tempAuthor, tempQuantity));
        System.out.println("Thank you for donating " + tempTitle + " to the library!\n");
    }

// this method allows user to borrow a book
// if book is not in db, the user will get an error message

    public static void borrowBook(ArrayList<Book> library) {
        String tempTitle;
        String tempAuthor;
        int tempQuantity;
        if (library.isEmpty()) { // if the library db has no books at all, then we shouldn't ask for input
            System.out.println("Sorry! The library is empty. Try donating a book.");
            TextIO.getlnString(); // clearing buffer to prepare for displaying menu again
            return;
        }
        System.out.println("\nYou will now borrow a book from your library. Press enter to continue.");
        TextIO.getlnString(); // clears buffer so we don't read data prematurely
        TextIO.put("Enter the book title: ");
        tempTitle = TextIO.getln();
        tempTitle = validateTitle(tempTitle);
        TextIO.put("Enter the author's name: ");
        tempAuthor = TextIO.getln();
        tempAuthor = validateAuthor(tempAuthor);
        TextIO.put("Enter the quantity you'd like to borrow: ");
        tempQuantity = TextIO.getInt();
        tempQuantity = validateQuantity(tempQuantity);
        // checking first to see if there's a match in the array
        for (Book book : library) {
            if (book.getTitle().equals(tempTitle) && book.getAuthor().equals(tempAuthor)) {
                if (book.getQuantity() > tempQuantity) {
                    book.setQuantity(book.getQuantity() - tempQuantity);
                    System.out.println("You borrowed " + tempTitle + " by " + tempAuthor + ".");
                    System.out.println("Please return to the library in two weeks.");
                    TextIO.getlnString(); // clearing buffer to prepare for displaying menu again
                    return;
                } else {
                    System.out.println("There aren't enough available copies in the library to borrow " + tempTitle + " by " + tempAuthor + ".");
                    System.out.println("Try borrowing another book or check back in a few days to see if it's available.\n");
                    TextIO.getlnString(); // clearing buffer to prepare for displaying menu again
                    return;
                }
            }
            // will execute if the book isn't in the db

            System.out.println("Sorry! We don't have that book in the library. Try borrowing a different book.\n");
            TextIO.getlnString(); // clearing buffer to prepare for displaying menu again
        }
    }

    // this method allows the user to return a book
    // if a match isn't found, an error message will display

    public static void returnBook(ArrayList<Book> library) {
        String tempTitle;
        String tempAuthor;
        int tempQuantity;
        if (library.isEmpty()) { // if the library db has no books at all, then we shouldn't ask for input
            System.out.println("Sorry! The library is empty. Try donating a book.");
            TextIO.getlnString(); // clearing buffer to prepare for displaying menu again
            return;
        }
        System.out.println("\nYou will now return a book to your library. Press enter to continue.");
        TextIO.getlnString(); // clears buffer so we don't read data prematurely
        TextIO.put("Enter the book title: ");
        tempTitle = TextIO.getln();
        tempTitle = validateTitle(tempTitle);
        TextIO.put("Enter the author's name: ");
        tempAuthor = TextIO.getlnString();
        tempAuthor = validateAuthor(tempAuthor);
        TextIO.put("Enter the quantity you'd like to return: ");
        tempQuantity = TextIO.getInt();
        tempQuantity = validateQuantity(tempQuantity);

        // checking to ensure book was entered correctly
        for (Book book : library) {
            if (book.getTitle().equals(tempTitle) && book.getAuthor().equals(tempAuthor)) {
                // since it already exists in the db, then we will just increment the quantity
                book.setQuantity(book.getQuantity() + tempQuantity);
                System.out.println("Thank you for returning " + tempTitle + " to the library!\n");
                TextIO.getlnString(); // clearing buffer to prepare for displaying menu again
                return;
            }
        }

        // if we get to this point, then the user is trying to return a book that doesn't exist
        // or one that was entered incorrectly

        System.out.println("That book doesn't exist in the library database. Are you sure you entered it correctly?");
        System.out.println("Check your spelling and try returning the book again from the main menu.\n");
        TextIO.getlnString(); // clearing buffer to prepare for displaying menu again
    }

    // the following methods validate user input for title, author, and quantity

    public static String validateTitle (String tempTitle) {
        while (tempTitle.isEmpty() || tempTitle.equals(" ")) {
            System.out.println("Error! Title can not be empty. Try again.");
            TextIO.put("Enter the title of the book: ");
            tempTitle = TextIO.getln();
        }
        return tempTitle;
    }

    public static int validateQuantity (int tempQuantity) {
        while (tempQuantity < 1) {
            System.out.println("Error! Quantity can not be less than 1. Try again.");
            TextIO.put("Enter the quantity of the book: ");
            tempQuantity = TextIO.getInt();
        }
        return tempQuantity;
    }

    public static String validateAuthor (String tempAuthor) {
        while (tempAuthor.isEmpty() || tempAuthor.equals(" ")) {
            System.out.println("Error! Author can not be empty. Try again.");
            TextIO.put("Enter the author of the book: ");
            tempAuthor = TextIO.getln();
        }
        return tempAuthor;
    }
}

