package hello.boot.jpa;

import hello.boot.jpa.domain.item.Book;
import hello.boot.jpa.web.BookForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BookTest {

    @Test
    void jpa상속테스트() {

        Book book = new Book();
        BookForm form = new BookForm();
        form.setName("lss");
        book.setName(form.getName());


        Assertions.assertThat(book.getName()).isEqualTo("lss");
    }
}
