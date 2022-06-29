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
        testBundleEqualsDiff();
        testWiki();

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

    private static void testWiki() {
        Author author1 = new Author("same", "author");
        Author author2 = new Author("same", "author");
        Author author3 = new Author("diff", "author");

        assert (author1.toString().equals("same author"));
        assert (author2.toString().equals("same author"));
        assert (author3.toString().equals("diff author"));

        assert (author1.equals(author2));
        assert (author2.equals(author1));
        assert (!author1.equals(author3));
        assert (!author3.equals(author1));
        assert (!author2.equals(author3));
        assert (!author3.equals(author2));

        assert (author1.hashCode() == author2.hashCode());
        assert (author1.hashCode() != author3.hashCode());

        // Book test
        Book book1 = new Book("same book", author1, 2020, Genre.ROMANCE);
        Book book2 = new Book("same book", author1, 2020, Genre.ROMANCE);
        Book book3 = new Book("diff book", author3, 2018, Genre.MYSTERY);

        assert (book1.toString().equals("same book [same author]"));
        assert (book2.toString().equals("same book [same author]"));
        assert (book3.toString().equals("diff book [diff author]"));

        assert (book1.equals(book2));
        assert (book2.equals(book1));
        assert (!book1.equals(book3));
        assert (!book3.equals(book1));
        assert (!book2.equals(book3));
        assert (!book3.equals(book2));

        assert (book1.hashCode() == book2.hashCode());
        assert (book1.hashCode() != book3.hashCode());

        // Bookshelf test
        Bookshelf bookshelf1 = new Bookshelf(3);
        Bookshelf bookshelf2 = bookshelf1;
        Bookshelf bookshelf3 = new Bookshelf(3);

        assert (bookshelf1.equals(bookshelf2));
        assert (bookshelf2.equals(bookshelf1));
        assert (!bookshelf1.equals(bookshelf3));
        assert (!bookshelf3.equals(bookshelf1));
        assert (!bookshelf2.equals(bookshelf3));
        assert (!bookshelf3.equals(bookshelf2));

        assert (bookshelf1.hashCode() == bookshelf2.hashCode());
        assert (bookshelf1.hashCode() != bookshelf3.hashCode());

        // Bundle test
        Bundle bundle1 = new Bundle("same bundle");
        Bundle bundle2 = new Bundle("same bundle");
        Bundle bundle3 = new Bundle("diff bundle");

        bundle1.add(book1);
        bundle1.add(book2);
        bundle1.add(book3);

        bundle2.add(book1);
        bundle2.add(book2);
        bundle2.add(book3);

        bundle3.add(book1);
        bundle3.add(book2);
        bundle3.add(book3);

        assert (bundle1.equals(bundle2));
        assert (bundle2.equals(bundle1));
        assert (!bundle1.equals(bundle3));
        assert (!bundle3.equals(bundle1));
        assert (!bundle2.equals(bundle3));
        assert (!bundle3.equals(bundle2));

        assert (bundle1.hashCode() == bundle2.hashCode());
        assert (bundle1.hashCode() != bundle3.hashCode());

        // ReadingList test
        ReadingList readingList1 = new ReadingList("same list");
        ReadingList readingList2 = new ReadingList("same list");
        ReadingList readingList3 = new ReadingList("diff list");

        readingList1.add(book1);
        readingList1.add(book2);
        readingList1.add(book3);

        readingList2.add(book1);
        readingList2.add(book2);
        readingList2.add(book3);

        readingList3.add(book1);
        readingList3.add(book2);
        readingList3.add(book3);

        String format = String.format("%d. %s%s%d. %s%s%d. %s%s",
                1, book1.toString(), System.lineSeparator(),
                2, book2.toString(), System.lineSeparator(),
                3, book3.toString(), System.lineSeparator());

        assert (readingList1.toString().equals(format));
        assert (readingList2.toString().equals(format));
        assert (readingList3.toString().equals(format));
        assert (readingList1.toString().equals(readingList2.toString()));
        assert (readingList1.toString().equals(readingList3.toString()));

        assert (readingList1.equals(readingList2));
        assert (readingList2.equals(readingList1));
        assert (!readingList1.equals(readingList3));
        assert (!readingList3.equals(readingList1));
        assert (!readingList2.equals(readingList3));
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

    private static void testBundleEqualsDiff() {
        Author author0 = new Author("Ja", "Hwang");
        Author author1 = new Author("Pope", "Kim");
        Author author3 = new Author("Junseok", "Lee");

        Book book0 = new Book("Physics", author0, 2300, Genre.SCIENCE_FICTION);
        Book book1 = new Book("Physics", author0, 2300, Genre.SCIENCE_FICTION);
        Book book2 = new Book("Hello Coding!", author1, 2016, Genre.SCIENCE_FICTION);
        Book book4 = new Book("Physics2", author3, 2301, Genre.SCIENCE_FICTION);

        Bundle bundle0 = new Bundle("Science");
        Bundle bundle1 = new Bundle("Science");
        Bundle bundle2 = new Bundle("Programming");

        bundle0.add(book0);
        bundle1.add(book1);

        assert bundle0.equals(bundle1);

        bundle0.add(book4);
        assert !bundle0.equals(bundle1);

        bundle2.add(book0);
        bundle2.add(book2);

        assert !bundle2.equals(bundle0);
        assert !bundle2.equals(bundle1);
    }

    private static void testReadingList() {
        Author author0 = new Author("Pope", "Kim");
        Author author1 = new Author("Jane", "Doe");
        Author author2 = new Author("John", "Smith");

        Book book0 = new Book("Hello Coding", author0, 2016, Genre.SUSPENSE);
        Book book1 = new Book("Intro to Professional Programming", author1, 2016, Genre.SUSPENSE);
        Book book2 = new Book("Mathematics for Software Engineering", author2, 2016, Genre.SUSPENSE);
        Book book3 = new Book("Unreal", author0, 2028, Genre.SUSPENSE);
        Book book100 = new Book("Hello Coding", author0, 2016, Genre.SUSPENSE);

        ReadingList list0 = new ReadingList("Programming");
        ReadingList list1 = new ReadingList("Programming");

        list0.add(book0);
        list0.add(book1);
        list0.add(book2);
        list0.add(book0);

        System.out.println(list0.toString());

        list1.add(book0);
        list1.add(book1);
        list1.add(book2);
        list1.add(book0);

        assert list0.equals(list1);

        HashSet<ReadingList> set = new HashSet<>();
        set.add(list0);

        assert set.contains(list1);

        ReadingList list3 = new ReadingList("Game");
        ReadingList list4 = new ReadingList("Game");

        list3.add(book0);
        list3.add(book1);
        list3.add(book2);
        list3.add(book3);

        list4.add(book100);
        list4.add(book1);
        list4.add(book2);
        list4.add(book3);

        assert list3.equals(list4);
    }
}
