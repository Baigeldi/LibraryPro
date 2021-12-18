package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private int id;
    private String name;
    private String login;
    private String password;
    private List<Book> issuedbooks;

    public Reader(String name, String login, String password) {
        this.id = Library.genUniqueId();
        this.name = name;
        this.login = login;
        this.password = password;
        this.issuedbooks = new ArrayList<>();
    }

    public static void getAllReaders(List<Reader> readerList) {
        for (Reader reader : readerList) {
            reader.getInfoReader();
        }
        System.out.println("*****************************************************");
        System.out.println();
    }

    void getInfoReader() {
        System.out.println("ID: " + getId()
                + " | Имя: " + getName()
                + " | Количество взятых книг: " + getIssuedbooks().size());
    }

    public static void takeBook() {
        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>(Book.searchBook());
        System.out.println("Введите ID книги, которую хотите взять...");
        try {
            int id = scanner.nextInt();
            for (Book book : books) {
                if (book.isInStock() && book.getId() == id) {
                    book.setInStock(false);
                    book.setUser(Main.loggedReader);
                    Main.loggedReader.issuedbooks.add(book);
                    System.out.println(Main.loggedReader.getName() + " взял книгу - ");
                    book.getInfoBook();
                    System.out.println("**************************************");
                    Main.mainMenu();
                } else if (!book.isInStock() && book.getId() == id) {
                    System.out.println("Эта книга уже взята");
                    takeBook();
                }
            }
            System.out.println("Книга с таким ID не найдена!");
            takeBook();
        } catch (InputMismatchException e) {
            System.out.println("Неправильный формат ввода");
            takeBook();
        }
    }

    public static void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя...");
        String name = scanner.nextLine();
        System.out.println("Введите ваш логин...");
        String login = scanner.nextLine();
        System.out.println("Введите ваш пароль...");
        String password = scanner.nextLine();
        Reader reader = new Reader(name,login,password);

        Main.library.getReaderList().add(reader);
        System.out.println("Добавлен пользователь - ");
        reader.getInfoReader();
        System.out.println("**********************************");
    }

    public static void returnBook() {
        Scanner scanner = new Scanner(System.in);
        if (Main.loggedReader.getIssuedbooks().size() == 0){
            System.out.println("У вас нет взятых книг!");
            System.out.println("************************");
            Main.mainMenu();
        }
        for (Book book: Main.loggedReader.getIssuedbooks()) {
            book.getInfoBook();
        }
        System.out.println("Введите ID книги, которую хотите вернуть...");
        try {
            int id = scanner.nextInt();
            for (Book book: Main.loggedReader.getIssuedbooks()) {
                if (!book.isInStock() && book.getId() == id){
                    book.setInStock(true);
                    book.setUser(null);
                    Main.loggedReader.issuedbooks.remove(book);
                    System.out.println(Main.loggedReader.getName() + " вернул книгу - ");
                    book.getInfoBook();
                    System.out.println("**********************************");
                    Main.mainMenu();
                }else if (book.isInStock() && book.getId() == id){
                    System.out.println("Эту книгу вы ещё не брали! ");
                    returnBook();
                }
            }
            System.out.println("Книга с таким ID не найдена!");
            returnBook();
        }catch (InputMismatchException e){
            System.out.println("Неправильный формат ввода!");
            returnBook();
        }
    }

    public void setIssuedbooks(List<Book> issuedbooks) {
        this.issuedbooks = issuedbooks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Book> getIssuedbooks() {
        return issuedbooks;
    }
}
