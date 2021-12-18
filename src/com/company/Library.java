package com.company;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Reader> readerList;
    private List<Book> bookList;
    private static List<Integer> ids = new ArrayList<>();

    public Library(List<Reader> readerList, List<Book> bookList) {
        this.readerList = readerList;
        this.bookList = bookList;
    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    public void setReaderList(List<Reader> readerList) {
        this.readerList = readerList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public static int genUniqueId() {
        int id = 0;
        while (true) {
            id = (int) (Math.random() * 899) + 100;
            if (checkForDuplicates(id)) {
                ids.add(id);
                break;
            }
        }
        return id;
    }

    private static boolean checkForDuplicates(int id) {
        for (int i : ids) {
            return (i == id)? true: false;
        }
        return true;
    }
}
