package com.erdinc.mindbehind.service;

import com.erdinc.mindbehind.config.CommentsFeignClient;
import com.erdinc.mindbehind.model.CommentDto;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentsFeignClient commentsFeignClient;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void test(){
        when(commentsFeignClient.comments()).thenReturn(sampleDto());
        List<CommentDto> commentDtos = commentService.getComment();
        Assertions.assertEquals(1L, sampleDto().size());
    }

    private List<CommentDto> sampleDto() {
        CommentDto commentDto = CommentDto.builder().id(1L).postId(1L).body("body").build();
        return Lists.newArrayList(commentDto);
    }
}
