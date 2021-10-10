package com.erdinc.mindbehind.config;

import com.erdinc.mindbehind.model.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "http://my-json-server.typicode.com/typicode/demo", value = "clientsClient")
public interface CommentsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/comments")
    List<CommentDto> comments();
}
