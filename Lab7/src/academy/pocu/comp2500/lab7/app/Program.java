package academy.pocu.comp2500.lab7.app;

import academy.pocu.comp2500.lab7.*;

import java.util.HashSet;

public class Program {

    public static void main(String[] args) {
        testCompareAuthor();
        testCompareBook();
        testBundle();
        testReadingList();
        testOfficial();

        System.out.println("No prob: lab7");
    }

    public static void testOfficial() {
        Author author = new Author("James", "Bond");
        Book book0 = new Book("How to be the best", author, 1990, Genre.BIOGRAPHY);
        Bookshelf bookshelf = new Bookshelf(10);

        assert (bookshelf.add(book0));
        assert (bookshelf.remove(book0));
        assert (!bookshelf.remove(book0));

        Book book1 = new Book("C# for dummies", new Author("Jason", "Bourne"), 2005, Genre.ROMANCE);
        Book book2 = new Book("C# for dummies", new Author("Jason", "Bourne"), 2005, Genre.ROMANCE);
        Book book3 = new Book("Java for dummies", new Author("James", "Bond"), 2007, Genre.MYSTERY);

        Bundle bundle = new Bundle("Programming");

        assert (bundle.add(book0));
        assert (bundle.add(book1));
        assert (!bundle.add(book2));
        assert (bundle.add(book3));

        assert (bundle.remove(book3));
        assert (bundle.remove(book0));
        assert (!bundle.remove(book0));

        ReadingList readingList = new ReadingList("Summer Break Homework");

        readingList.add(book0);
        readingList.add(book1);
        readingList.add(book2);
        readingList.add(book3);

        assert (readingList.remove(book3));
        assert (readingList.remove(book0));
        assert (!readingList.remove(book0));
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

        Bundle bundle3 = new Bundle("Just");
        HashSet<Bundle> set = new HashSet<>();
        set.add(bundle0);

        assert set.contains(bundle1);
        assert !set.contains(bundle3);

        Bundle bundle2 = new Bundle("Coding");
        bundle2.add(book2);

        assert !bundle0.equals(bundle2);
    }

    private static void testReadingList() {
        Author author0 = new Author("Pope", "Kim");
        Author author1 = new Author("Jane", "Doe");
        Author author2 = new Author("John", "Smith");

        Book book0 = new Book("Hello Coding", author0, 2016, Genre.SUSPENSE);
        Book book1 = new Book("Intro to Professional Programming", author1, 2016, Genre.SUSPENSE);
        Book book2 = new Book("Mathematics for Software Engineering", author2, 2016, Genre.SUSPENSE);

        ReadingList list0 = new ReadingList("Programming");
        list0.add(book0);
        list0.add(book1);
        list0.add(book2);
        list0.add(book0);

        System.out.println(list0.toString());

        ReadingList list1 = new ReadingList("Programming");
        list1.add(book0);
        list1.add(book1);
        list1.add(book2);
        list1.add(book0);

        assert list0.equals(list1);

        HashSet<ReadingList> set = new HashSet<>();
        set.add(list0);

        assert set.contains(list1);
    }
}
