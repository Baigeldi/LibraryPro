package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean inStock;
    private Reader user;

    public Book(String title, String author) {
        this.id = Library.genUniqueId();
        this.title = title;
        this.author = author;
        this.inStock = true;
    }

    public static List<Book> searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Поиск книг,авторов");
        String search = scanner.nextLine();
        List<Book> books = new ArrayList<>();
        for (Book b : Main.library.getBookList()) {
            if (b.getAuthor().toLowerCase().matches("(.*)" + search.toLowerCase() + "(.*)")
                    || b.getTitle().toLowerCase().matches("(.*)" + search.toLowerCase() + "(.*)")) {
                books.add(b);
            }
        }
        if (books.size() == 0) {
            System.out.println("Книг с такими параметрами не найдено!");
            searchBook();
        }else getAllBooks(books);
        return books;
    }

    public static void getAllBooks(List<Book> bookList) {
        for (Book book : bookList) {
            book.getInfoBook();
        }
        System.out.println("***********************************************************************");
        System.out.println();
    }

    void getInfoBook() {
        System.out.println("ID: " + getId()
                + " | Название: " + getTitle()
                + " | Автор: " + getAuthor()
                + " | Статус: " + (isInStock() ? "Доступно" : "Взято"));
    }

    public static void getAllTakenBooks(List<Book> bookList) {
        List<Book> books = new ArrayList<>();
        for (Book book: bookList) {
            if (!book.isInStock()){
                books.add(book);
            }
        }
        if (books.size() == 0){
            System.out.println("Нет взятых книг!");
        }else {
            for (Book book: books) {
                book.getInfoBook();
            }
        }
        System.out.println("*******************************");
    }

    public static void addNewBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название книги...");
        String title = scanner.nextLine();
        System.out.println("Введите автора книги...");
        String author = scanner.nextLine();
        Book book = new Book(title,author);
        Main.library.getBookList().add(book);
        System.out.print("Добавлена книга - ");
        book.getInfoBook();
        System.out.println("*********************************");
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public void setUser(Reader user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isInStock() {
        return inStock;
    }

    public Reader getUser() {
        return user;
    }


}
