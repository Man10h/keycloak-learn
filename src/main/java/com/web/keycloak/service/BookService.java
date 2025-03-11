package com.web.keycloak.service;

import com.web.keycloak.model.BookEntity;

import java.awt.print.Book;
import java.util.List;

public interface BookService {
    public String addBook(BookEntity book);

    public List<BookEntity> findAll();
}
