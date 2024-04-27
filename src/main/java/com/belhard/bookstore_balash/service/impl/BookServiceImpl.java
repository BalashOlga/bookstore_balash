package com.belhard.bookstore_balash.service.impl;

import com.belhard.bookstore_balash.service.BookService;
import com.belhard.bookstore_balash.data.dao.BookDao;
import com.belhard.bookstore_balash.data.dao.impl.BookDaoImpl;
import com.belhard.bookstore_balash.data.entity.Book;
import com.belhard.bookstore_balash.service.dto.BookDto;

import java.math.BigDecimal;
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

    private Book toBook(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setAuthor(bookDto.getAutor());
        book.setIsbn(bookDto.getIsbn());
        book.setYear(bookDto.getYear());
        book.setCost(bookDto.getCost());

        return book;
    }

    @Override
    public BookDto getById(long id) {
        return toDto(bookDao.findById(id));
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        return toDto(bookDao.findByIsbn(isbn));
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
        return bookDao.findByAuthor(author)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return toDto(bookDao.create(toBook(bookDto)));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return toDto(bookDao.update(toBook(bookDto)));
    }

    @Override
    public boolean delete(long id) {
        return bookDao.delete(id);
    }

    @Override
    public long getcountAll() {
        return bookDao.countAll();
    }

    @Override
    public BigDecimal getCostByAuthor(String author) {
        List<Book> bookList = bookDao.findByAuthor(author);

        if (bookList.isEmpty()){
            return null;
        } else {
            BigDecimal i = BigDecimal.valueOf(0);
            for (Book book : bookList) {
                if (book.getCost() != null) {
                    i = i.add(book.getCost());
                }
            }
            return i;
        }
    }
}
