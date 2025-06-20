package com.jcomm.bookshelf;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("A bookshelf")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfTest {

    private BookShelf bookShelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init(Map<String, Book> books) {
        bookShelf = new BookShelf();
        this.effectiveJava = books.get("Effective Java");
        this.codeComplete = books.get("Code Complete");
        this.mythicalManMonth = books.get("The Mythical Man-Month");
        this.cleanCode = books.get("Clean Code");
    }


    @Test
    void emptyBookShelfWhenNoBookAdded() {
        //given

        List<Book> books = bookShelf.books();

        assertThat(books).isEmpty();
    }

    @Test
    void bookShelfContains2BooksWhen2BooksAdded() {

        bookShelf.add(effectiveJava, codeComplete);

        var books = bookShelf.books();
        assertThat(books).hasSize(2);
    }

    @Test
    void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        //given
        bookShelf.add();

        //when
        var books = bookShelf.books();

        //then
        assertThat(books).hasSize(0);
    }

    @Test
    void booksReturnedAreUnmodifiableForClient() {
        //given
        bookShelf.add(effectiveJava, codeComplete);
        var books = bookShelf.books();

        //when/then
        assertThatThrownBy(() -> books.add(mythicalManMonth))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void bookShelArrangedByBookTitle() {
        BookShelf bookShelf = new BookShelf();
        bookShelf.add(effectiveJava, codeComplete);
        List<Book> books = bookShelf.arrange();

        assertThat(books).containsAll(Arrays.asList(codeComplete, effectiveJava));
    }

    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
        BookShelf bookShelf = new BookShelf();
        bookShelf.add(effectiveJava, codeComplete, mythicalManMonth);
        bookShelf.arrange();
        var books = bookShelf.books();
        assertThat(books).containsExactly(effectiveJava, codeComplete, mythicalManMonth);
    }

    @Test
    void bookshelfArrangedByUserProvidedCriteria() {
        bookShelf.add(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = bookShelf.arrange(reversed);
        assertThat(books).isSortedAccordingTo(reversed);
    }
}
