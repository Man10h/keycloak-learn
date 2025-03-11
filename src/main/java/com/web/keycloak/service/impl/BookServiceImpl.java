package com.web.keycloak.service.impl;

import com.web.keycloak.model.BookEntity;
import com.web.keycloak.repository.BookRepository;
import com.web.keycloak.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String addBook(BookEntity book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookRepository.save(bookEntity);
        return "add book success";
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }
}
