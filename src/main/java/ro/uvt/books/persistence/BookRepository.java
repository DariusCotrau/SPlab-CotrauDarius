package ro.uvt.books.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uvt.books.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
