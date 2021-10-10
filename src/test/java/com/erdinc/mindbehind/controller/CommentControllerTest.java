package com.erdinc.mindbehind.controller;

import com.erdinc.mindbehind.model.CommentDto;
import com.erdinc.mindbehind.service.CommentService;
import com.erdinc.mindbehind.service.WriteDataService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @MockBean
    private CommentService commentService;

    @MockBean
    private WriteDataService writeDataService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        when(commentService.getComment()).thenReturn(Lists.newArrayList(sampleDto()));
        when(writeDataService.writeCsv(any())).thenReturn("fileName.csv");

        this.mockMvc.perform(get("/api/comments/getComments"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$[0].body").value("body1"))
                .andExpect(jsonPath("$[0].postId").value(1L))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void test_error() throws Exception {
        when(commentService.getComment()).thenReturn(Lists.newArrayList(sampleDto()));
        when(writeDataService.writeCsv(any())).thenReturn("fileName.csv");
        this.mockMvc.perform(get("/api/comments/getCommentsWithError"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.errorCode").value(500L))
                .andExpect(jsonPath("$.errorMessage").value("error get comments"));
    }

    private List<CommentDto> sampleDto() {
        CommentDto commentDto = CommentDto.builder().id(1L).postId(1L).body("body1").build();
        return Lists.newArrayList(commentDto);
    }
}
