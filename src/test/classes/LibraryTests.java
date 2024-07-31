package test.classes;

import main.classes.Book;
import main.classes.Library;
import main.classes.Student;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class LibraryTests {

    @Test
    @DisplayName("Test for check student are registered when lend book")
    public void checkStudentRegistered() {
        Library library = new Library();

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
    public void checkStudentBookAfterReturnBook() {
        Library library = new Library();

        Book book1 = new Book("Book-1", "Author-1", 10);

        Student student1 = new Student("Alice", 10);

        library.addBook(book1);

        library.addStudent(student1);

        library.lendBook(book1, student1);


        Assertions.assertTrue(library.returnBook(book1, student1));
        Assertions.assertFalse(library.returnBook(book1, student1));
    }


}
