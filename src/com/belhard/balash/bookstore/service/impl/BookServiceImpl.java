package com.belhard.balash.bookstore.service.impl;

import com.belhard.balash.bookstore.service.BookService;
import com.belhard.balash.bookstore.data.dao.BookDao;
import com.belhard.balash.bookstore.data.dao.impl.BookDaoImpl;
import com.belhard.balash.bookstore.data.entity.Book;
import com.belhard.balash.bookstore.service.dto.BookDto;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao = new BookDaoImpl();

    private BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAutor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setYear(book.getYear());
        bookDto.setCost(book.getCost());
        
        return bookDto;
    }

    @Override
    public BookDto getById(long id) {
        return toDto(bookDao.findById(id);
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        return toDto(bookDao.findByIsbn());
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        /*
        List<Book> books = bookDao.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        // почему здесь нельзя переменную назвать bookDto, она где-то занята, у меня метод add - красный?
        for (Book book : books) {
            BookDto bookDto = toDto(book);
            bookDtos.add(bookDto);
            return bookDto;
        }
        */

    }

    @Override
    public List<BookDto> getByAuthor(String author) {
        return toDto(bookDao.findByAuthor(author);
    }

    @Override
    public BookDto create(BookDto book) {
        return null;
    }

    @Override
    public BookDto update(BookDto book) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public long getcountAll() {
        return bookDao.countAll();
    }
}
