package com.erdinc.mindbehind.controller;

import com.erdinc.mindbehind.model.CommentDto;
import com.erdinc.mindbehind.service.CommentService;
import lombok.RequiredArgsConstructor;
import com.erdinc.mindbehind.service.WriteDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/comments")
public class CommentController {

    private final CommentService commentService;

    private final WriteDataService writeDataService;

    @GetMapping(path = "/getComments")
    public ResponseEntity<List<CommentDto>> getComments()  {
        //String url = "https://my-json-server.typicode.com/typicode/demo/comments";
        final List<CommentDto> commentDtos = commentService.getComment();
        writeDataService.writeCsv(commentDtos);
        return ResponseEntity.ok(commentDtos);
    }

    @GetMapping(path = "/getCommentsWithError")
    public ResponseEntity<List<CommentDto>> getCommentsWithError(){
        throw new RuntimeException("error get comments");
    }

}
