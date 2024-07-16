package com.amazon.ata.optionals.optionals.models;

import java.util.List;
import java.util.Optional;

/**
 * Represents an author's publishing history by name.
 * Only the books published under this author's name are included.
 * Books published under other pseudonyms will be in their own Authors.
 */
public class Author {
    private final String name;
    private final String id;
    private final List<Author> pseudonyms;
    private final List<Book> books;

    public Author(String name, String id, List<Author> pseudonyms, List<Book> books) {
        this.name = name;
        this.id = id;
        this.pseudonyms = pseudonyms;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Author> getPseudonyms() {
        return pseudonyms;
    }

    /**
     * Finds the highest rated book this author has published.
     * @return An Optional containing the author's highest rated book, if any.
     */
    public Optional<Book> getBestRatedBook() {
        Book highestRatedBook = null;
        Double highestRating = -1.0;
        for (Book book : books) {
            Optional<Double> rating = book.getWeightedRating();
            if (rating.isPresent() && rating.get() > highestRating) {
                highestRating = rating.get();
                highestRatedBook = book;
            }
        }
        return Optional.ofNullable(highestRatedBook);
    }
}
