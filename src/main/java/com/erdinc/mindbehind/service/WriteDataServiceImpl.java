package com.erdinc.mindbehind.service;

import com.erdinc.mindbehind.model.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class WriteDataServiceImpl implements WriteDataService{

    @Override
    public String writeCsv(List<CommentDto> commentDtoList) {
       List<String[]> lines = new ArrayList<>();
       lines.add(new String[]{"id", "postId", "body"});

       for(CommentDto commentDto : commentDtoList){
           if(commentDto.getId() != null){
               lines.add(new String[]{commentDto.getId().toString(), commentDto.getPostId().toString(), commentDto.getBody()});
           }
           else{
               lines.add(new String[]{"0", "0", commentDto.getBody()});
           }

       }
       return writeTofFile(lines);
    }

    private String writeTofFile(List<String[]> lines) {
        String rndFileName = RandomStringUtils.randomAlphanumeric(10) + ".csv";
        File csvOutputFile = new File(rndFileName);

        try(PrintWriter printWriter = new PrintWriter(csvOutputFile)){
            lines.stream()
                    .map(this:: convertToCsv)
                    .forEach(printWriter::println);
        }
        catch (Exception ex){
            log.error("Error write to a file", ex);
        }
        return rndFileName;
    }

    private String convertToCsv(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
