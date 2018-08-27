package com.imooc.firstappdemo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.function.BiPredicate;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TopLinesHandler {

    private ObjectMapper mapper;

    @GetMapping("/top_lines")
    public Mono<Object> handleGetUserById(){
        return getTopLines().map(this::extractTitles);
    }

    private Mono<String> getTopLines(){
        WebClient webClient = WebClient.create("http://c.m.163.com");
        return webClient.get().uri("/nc/article/headline/T1348647853363/0-20.html")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(resp-> resp.bodyToMono(String.class)
                );
    }

    private List<String> extractTitles(String jsonStr){
        JsonNode jsonNode;
        try{
            jsonNode = mapper.readTree(jsonStr);
        }catch (IOException e){
            throw  new UncheckedIOException(e);
        }
        JsonNode articles = jsonNode.get("T1348647853363");
        List<String> list = new ArrayList<String>(articles.size());
        for (int i = 0 ; i < articles.size() ; i++){
            JsonNode article = articles.get(i);
            String title = article.get("title").textValue();
            list.add(title);
        }
        return list;
    }
}
