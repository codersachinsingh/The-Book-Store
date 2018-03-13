package com.bookstore.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


// ----------------Book Class------------------------
class Book implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private String description;
    private String[] tags;
    private int pages;
    private double price;
    private int id;
    private Category category;
    private final long date;
    //Default Constructor
    public Book() {
        title = "No Title";
        author = "Not Specified";
        publisher = "Not Specified";
        description = "Empty";
        tags[0] = "no tags";
        pages = 0;
        price = 0.0;
        date = new Date().getTime();
    }

    //Constructor with title , author , pages and price
    public Book(String title, String author, int pages, double price) {
        super();
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        date = new Date().getTime();
    }

    //Setters for Publisher , Description , id and tags
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setId(int id) {
        this.id = id;
    }
    public long getPublishedDate() {
       return date;
    }

    //Getters for all fields
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTags() {
        return tags;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }


    public int getId() {
        return id;
    }

    public String getCategory() {
        switch(this.category) {
            case BIOGRAPHIES : return "Biographies";
            case KIDS : return "Kids";
            default:
                return "Not specified";

        }
    }

}



//this class stores a book with its stock quantity.
class BookStock implements Serializable {
	private Book book;
	private int stock;

	public BookStock(Book book, int stock) {
		super();
		this.book = book;
		this.stock = stock;
	}

	public Book getBook() {
		return book;
	}

	public int getStock() {
		return stock;
	}

	
}



class BooksManager {
    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected ResourcesManager resourcesManager;
    // array stores all books in library
    protected ArrayList<BookStock> bookstocks;
    protected Scanner scanner = new Scanner(System.in);

    public BooksManager(ResourcesManager resourcesManager) {
        this.resourcesManager = resourcesManager;
        this.bookstocks = resourcesManager.getAllBookstock();
    }
}

public class BookManagementTools extends BooksManager {
    BookManagementTools(ResourcesManager resourcesManager) {
        super(resourcesManager);
    }

        public void addAdditionalFeilds(Book book) {
            System.out.print("\nEnter Publisher : ");
            String publisher = scanner.nextLine();
        }

        public void addNewBook() throws IOException {
        //TITLE
        System.out.print("Enter Book Title : ");
        String title = reader.readLine();
        //AUTHOR
        System.out.print("\nEnter Book's Author : ");
        String author = reader.readLine();
        //PAGES
        System.out.print("\nEnter Pages : ");
        int pages = scanner.nextInt();
        //ID
        System.out.print("\nEnter Book ID : ");
        int id = scanner.nextInt();
        //PRICE
        System.out.print("\nEnter Price : ");
        double price = scanner.nextDouble();

        Book book = new Book(title,author,pages,price);

        // ask to fill other fields
            int ask;
            System.out.println("Do you want to enter more fields : \nEnter 1 Yes\nOther for  No\n\n ==> ");
            ask = scanner.nextInt();
            if (ask==1) {
                addAdditionalFeilds(book);
            }
            System.out.println("Enter Current Stock of This Book : ");
            int stock  = scanner.nextInt();
            bookstocks.add(new BookStock(book,stock));
            //update on disk file.
            resourcesManager.saveResources();

    }

    public void viewbook(Book book) {
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
        System.out.println(book.getPages());
    }

   Book[] getLastFiveAddedBooks() {
        // first sort all books by date
       bookstocks.sort((book1,book2)-> {
           if (book1.getBook().getPublishedDate() != book2.getBook().getPublishedDate()) {
               if (book1.getBook().getPublishedDate() > book2.getBook().getPublishedDate()) return 1;
               else return -1;
           }
           else
               return 0;
       });
       int bookqty = bookstocks.size();
       if (bookqty>5) bookqty = 5;
       Book[] books = new Book[bookqty];
       for (int i = 0;i<bookqty;i++) {
           books[i] = bookstocks.get(i).getBook();
       }
       return books;
    }
}