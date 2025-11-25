package com.rkumar.talebloom.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.rkumar.talebloom.dto.StoryCreatedResDto;
import com.rkumar.talebloom.dto.StoryResDto;
import com.rkumar.talebloom.entities.*;
import com.rkumar.talebloom.exceptions.ResourceNotFoundException;
import com.rkumar.talebloom.repositories.CategoryRepository;
import com.rkumar.talebloom.repositories.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rkumar.talebloom.dto.StoryDto;
import com.rkumar.talebloom.repositories.StoryRepository;
import com.rkumar.talebloom.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoryService {
    private final ModelMapper modelMapper;
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;


    public StoryCreatedResDto createStory(StoryDto storyDto, Long authorId) {
        UserEntity author = userRepository.findById(authorId).orElseThrow(
                () -> new IllegalArgumentException("Author with id '" + authorId + "' does not exist.")
        );

        Optional<StoryEntity> storyEntity = storyRepository.findByTitleAndAuthorId(storyDto.getTitle(), authorId);
        if (storyEntity.isPresent()) {
            throw new IllegalArgumentException("Story with title: '" + storyDto.getTitle() + "', Already authored by authorId: '" + authorId);
        }
        
        StoryEntity storyToBeCreated = modelMapper.map(storyDto, StoryEntity.class);
        storyToBeCreated.setAuthor(author);

        CategoryEntity category = categoryRepository.findByCategoryName(storyDto.getCategory())
                .orElseGet(() -> {
                    CategoryEntity newCategory = new CategoryEntity();
                    newCategory.setCategoryName(storyDto.getCategory());
                    return categoryRepository.save(newCategory);
                });

        storyToBeCreated.setCategory(category);

        Set<TagEntity> tags = storyDto.getTags().stream()
                .map((tagName) -> {
                    return tagRepository.findByTagName(tagName).orElseGet(() -> {
                        TagEntity newTag = new TagEntity();
                        newTag.setTagName(tagName);
//                        newTag.setStory(storyToBeCreated);
                        return tagRepository.save(newTag);
                    });
                })
                .collect(Collectors.toSet());

        storyToBeCreated.setTags(tags);

        StoryEntity savedStory = storyRepository.save(storyToBeCreated);
//        System.out.println(savedStory);
        return modelMapper.map(savedStory, StoryCreatedResDto.class);
    }

    public StoryResDto getStoryById(Long storyId) {
        StoryEntity storyEntity = storyRepository.findById(storyId)
                .orElseThrow(() -> new ResourceNotFoundException("Story not found with id: " + storyId));

        return modelMapper.map(storyEntity, StoryResDto.class);
    }

    public List<StoryResDto> getAllStories(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("publishedAt").descending());
        Page<StoryEntity> storyPage = storyRepository.findAll(pageable);
//        List<StoryEntity> storyEntities = storyRepository.findAll();

        List<StoryEntity> storyEntities = storyPage.getContent();

        return storyEntities.stream()
                .map(story -> {
                    return modelMapper.map(story, StoryResDto.class);
                })
                .collect(Collectors.toList());
    }


    public List<StoryResDto> getStoriesByTag(String tagName) {
        List<StoryEntity> storyEntities = storyRepository.findByTags_TagName(tagName);

        return storyEntities.stream()
                .map(story -> modelMapper.map(story, StoryResDto.class))
                .collect(Collectors.toList());
    }

    public List<StoryResDto> getStoryByCategory(String category) {
        List<StoryEntity> stories = storyRepository.findByCategory_categoryName(category);

        return stories.stream()
                .map(story -> modelMapper.map(story, StoryResDto.class))
                .collect(Collectors.toList());
    }
}
