package com.erdinc.mindbehind.service;

import com.erdinc.mindbehind.config.CommentsFeignClient;
import com.erdinc.mindbehind.model.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsFeignClient commentsFeignClient;

    @Override
    public List<CommentDto> getComment() {
        return commentsFeignClient.comments();
    }
}
