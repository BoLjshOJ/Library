package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Comment;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import({CommentRepositoryJpa.class, BookRepositoryJpa.class})
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testCount() {
        assertThat(commentRepository.count()).isEqualTo(5);
    }

    @Test
    public void testInsert() {
        Comment newComment = new Comment(bookRepository.getById(1L), "newComment");
        commentRepository.insert(newComment);
        Comment find = commentRepository.getById(6L);
        assertThat(find.getText()).isEqualTo(newComment.getText());
        assertThat(find.getBook()).isEqualTo(newComment.getBook());
    }

    @Test
    public void testGetById() {
        assertThat(commentRepository.getById(1L))
                .hasFieldOrPropertyWithValue("text", "comment1");
    }

    @Test
    public void testGetAll() {
        assertThat(commentRepository.count()).isEqualTo(5);
        Comment one = commentRepository.getById(1L);
        Comment two = commentRepository.getById(2L);
        Comment three = commentRepository.getById(3L);
        Comment four = commentRepository.getById(4L);
        Comment five = commentRepository.getById(5L);
        assertThat(commentRepository.getAll()).contains(one, two, three, four, five);
    }

    @Test
    public void testDeleteById() {
        Comment commForDelete = commentRepository.getById(5L);
        commentRepository.deleteById(5L);
        assertThat(commentRepository.getAll().size()).isEqualTo(4);
        assertThat(commentRepository.getAll()).doesNotContain(commForDelete);
    }

    @Test
    public void testGetByBookId() {
        assertThat(commentRepository.getByBookId(1L)).contains(commentRepository.getById(1L), commentRepository.getById(2L));
    }
}