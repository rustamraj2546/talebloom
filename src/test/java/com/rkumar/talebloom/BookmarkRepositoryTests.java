package com.rkumar.talebloom;

import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.repositories.BookmarkRepository;
import com.rkumar.talebloom.repositories.StoryRepository;
import com.rkumar.talebloom.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class BookmarkRepositoryTests {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Test
    @Transactional
    void findByStoryId() {
        Optional<StoryEntity> storyEntity = storyRepository.findById(2L);
        if(storyEntity.isPresent()) {
            StoryEntity story = storyEntity.get();

            System.out.println(story);
        }
    }

}
