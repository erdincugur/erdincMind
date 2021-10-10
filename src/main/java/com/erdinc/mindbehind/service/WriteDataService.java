package com.erdinc.mindbehind.service;

import com.erdinc.mindbehind.model.CommentDto;

import java.util.List;

public interface WriteDataService {
    String writeCsv(List<CommentDto> commentDtoList);
}
