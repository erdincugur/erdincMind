package com.erdinc.mindbehind.service;

import com.erdinc.mindbehind.model.CommentDto;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WriteDataServiceTest {

    @InjectMocks
    private WriteDataServiceImpl writeDataService;

    @Test
    public void test() throws IOException {
        List<CommentDto> commentDtos = sampleDto();
        String fileName = writeDataService.writeCsv(commentDtos);
        File file = new File(fileName);
        String fileContent = FileUtils.readFileToString(file);
        Assertions.assertTrue(StringUtils.isNotBlank(fileContent));
        boolean delete = file.delete();
        Assertions.assertTrue(delete);
    }

    private List<CommentDto> sampleDto() {
        CommentDto commentDto = CommentDto.builder().id(1L).postId(1L).body("body1").build();
        return Lists.newArrayList(commentDto);
    }
}
