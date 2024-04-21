package com.belhard.balash.bookstore.service.dto;

import java.math.BigDecimal;

public class BookDto {
    private Long id;
    private String author;
    private String isbn;
    private Integer year;
    private BigDecimal cost;

    public BookDto(){

    };

    public BookDto(Long id, String autor, String isbn, Integer year, BigDecimal cost) {
        this.id = id;
        this.author = autor;
        this.isbn = isbn;
        this.year = year;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                ", cost=" + cost +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getAutor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getYear() {
        return year;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
