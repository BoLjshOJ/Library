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
        Comment newComment = new Comment(bookRepository.findById(1L).get(), "newComment");
        commentRepository.save(newComment);
        Comment find = commentRepository.findById(6L).get();
        assertThat(find.getText()).isEqualTo(newComment.getText());
        assertThat(find.getBook()).isEqualTo(newComment.getBook());
    }

    @Test
    public void testGetById() {
        assertThat(commentRepository.findById(1L).get())
                .hasFieldOrPropertyWithValue("text", "comment1");
    }

    @Test
    public void testGetAll() {
        assertThat(commentRepository.count()).isEqualTo(5);
        assertThat(commentRepository.findAll()).extracting("text").contains("comment1", "comment2", "comment3", "comment4", "comment5");
    }

    @Test
    public void testDeleteById() {
        Comment commForDelete = commentRepository.findById(5L).get();
        commentRepository.deleteById(5L);
        assertThat(commentRepository.findAll().size()).isEqualTo(4);
        assertThat(commentRepository.findAll()).doesNotContain(commForDelete);
    }

    @Test
    public void testGetByBookId() {
        assertThat(commentRepository.findCommentsByBookId(1L)).contains(commentRepository.findById(1L).get(), commentRepository.findById(2L).get());
    }
}