package test.classes;

import main.classes.Book;
import main.classes.Library;
import main.classes.SearchByType;
import main.classes.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LibraryTests {

    private Library library;

    @BeforeEach
    void beforeEach() {
        library = new Library();
    }

    @Test
    @DisplayName("Test for check student are registered when lend book")
    void checkStudentRegistered() {
        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 11);

        Student student1 = new Student("Alice", 10);
        Student student2 = new Student("Bob", 11);

        library.addBook(book1);
        library.addBook(book2);

        library.addStudent(student1);

        Assertions.assertFalse(library.lendBook(book2, student2));
        Assertions.assertTrue(library.lendBook(book1, student1));
    }

    @Test
    @DisplayName("Test for check after return book student doesn't have this book on list of books.")
    void checkStudentBookAfterReturnBook() {
        Book book1 = new Book("Book-1", "Author-1", 10);

        Student student1 = new Student("Alice", 10);

        library.addBook(book1);

        library.addStudent(student1);

        library.lendBook(book1, student1);


        Assertions.assertTrue(library.returnBook(book1, student1));
        Assertions.assertFalse(library.returnBook(book1, student1));
    }

    @Test
    @DisplayName("Test for valid search type (id, name) for the searchStudents method")
    void testSearchStudents() {
        Student student1 = new Student("Ali", 10);
        Student student2 = new Student("Hassan", 11);
        Student student3 = new Student("Hossein", 12);
        Student student4 = new Student("Sajjad", 13);

        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);
        library.addStudent(student4);

        assertEquals(student1.getId(), library.searchStudents(SearchByType.ID, List.of(student1.getId())).get(0).getId());
        assertEquals(student2.getName(), library.searchStudents(SearchByType.NAME, List.of(student2.getName())).get(0).getName());
        assertNull(library.searchStudents(SearchByType.AUTHOR, List.of(student3.getId())));
        assertNull(library.searchStudents(SearchByType.TITLE, List.of(student4.getId())));
    }

    @Test
    @DisplayName("Test for valid search type (id, author, title) for the searchBooks method")
    void testSearchBooks() {
        Book book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 11);
        Book book3 = new Book("Book-3", "Author-3", 12);
        Book book4 = new Book("Book-4", "Author-4", 13);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        assertEquals(book1.getId(), library.searchBooks(SearchByType.ID, List.of(book1.getId())).get(0).getId());
        assertEquals(book2.getTitle(), library.searchBooks(SearchByType.TITLE, List.of(book2.getTitle())).get(0).getTitle());
        assertEquals(book3.getAuthor(), library.searchBooks(SearchByType.AUTHOR, List.of(book3.getAuthor())).get(0).getAuthor());
        assertNull(library.searchBooks(SearchByType.NAME, List.of(book4.getId())));
    }


}
