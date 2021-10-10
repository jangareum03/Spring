package com.example.foodList.naver;

import com.example.foodList.naver.dto.SearchImageReq;
import com.example.foodList.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);
        System.out.println(result);
    }

    @Test
    public void searchImageTest(){
        var image = new SearchImageReq();
        image.setQuery("갈비집");

        var result = naverClient.imageSearch(image);
        System.out.println(result);
    }
}
