package academy.pocu.comp2500.lab7.app;

import academy.pocu.comp2500.lab7.*;

import java.util.HashSet;

public class Program {

    public static void main(String[] args) {
        testCompareAuthor();
        testCompareBook();
        testBundle();
        testReadingList();

        System.out.println("No prob: lab7");
    }

    private static void testCompareAuthor() {
        Author author0 = new Author("Ja", "Hwang");
        Author author1 = author0;
        Author author2 = new Author("Ja", "Hwang");
        Author author3 = new Author("Pope", "Kim");

        assert author0 == author1;
        assert author0.equals(author1);
        assert author0.equals(author2);
        assert !author0.equals(author3);
    }

    private static void testCompareBook() {
        Author author0 = new Author("Ja", "Hwang");
        Author author1 = new Author("Pope", "Kim");

        Book book0 = new Book("Event horizon", author0, 2022, Genre.SUSPENSE);
        Book book1 = new Book("Event horizon", author0, 2022, Genre.SUSPENSE);
        Book book2 = new Book("Hello Coding", author1, 2022, Genre.SUSPENSE);

        assert book0 != book1;
        assert book0.equals(book1);
        assert !book0.equals(book2);

        HashSet<Book> books = new HashSet<>();
        books.add(book0);
        books.add(book2);

        assert books.contains(book1);
    }

    private static void testBundle() {
        Author author0 = new Author("Ja", "Hwang");
        Author author1 = new Author("Pope", "Kim");
        Author author3 = new Author("Junseok", "Lee");

        Book book0 = new Book("Physics", author0, 2300, Genre.SCIENCE_FICTION);
        Book book1 = new Book("Physics", author0, 2300, Genre.SCIENCE_FICTION);
        Book book2 = new Book("Hello Coding!", author1, 2016, Genre.SCIENCE_FICTION);
        Book book4 = new Book("Physics2", author3, 2301, Genre.SCIENCE_FICTION);

        Bundle bundle0 = new Bundle("Science");

        assert bundle0.add(book0);
        assert !bundle0.add(book1);
        assert bundle0.add(book4);
        assert bundle0.remove(book1);
        assert !bundle0.remove(book0);
        assert bundle0.add(book1);

        Bundle bundle1 = new Bundle("Science");
        bundle1.add(book0);
        bundle1.add(book4);

        assert bundle0.equals(bundle1);

        Bundle bundle2 = new Bundle("Coding");
        bundle2.add(book2);

        assert !bundle0.equals(bundle2);
    }

    private static void testReadingList() {
//        1. Hello Coding [Pope Kim]
//        2. Intro to Professional Programming [Jane Doe]
//        3. Mathematics for Software Engineering [John Smith]
//        4. Hello Coding [Pope Kim]

        Author author0 = new Author("Pope", "Kim");
        Author author1 = new Author("Jane", "Doe");
        Author author2 = new Author("John", "Smith");

        Book book0 = new Book("Hello Coding", author0, 2016, Genre.SUSPENSE);
        Book book1 = new Book("Intro to Professional Programming", author1, 2016, Genre.SUSPENSE);
        Book book2 = new Book("Mathematics for Software Engineering", author2, 2016, Genre.SUSPENSE);

        ReadingList list = new ReadingList("Programming");
        list.add(book0);
        list.add(book1);
        list.add(book2);

        System.out.println(list.toString());
    }
}
