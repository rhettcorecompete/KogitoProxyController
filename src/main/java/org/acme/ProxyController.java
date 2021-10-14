package org.acme;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;


@org.springframework.stereotype.Component
@RestController
public class ProxyController {

    @PostMapping("/proxy")
    @ResponseBody
    public String Mappr(@RequestBody String body, HttpMethod method, HttpServletRequest request) throws URISyntaxException
    {
        //see "proxy-example.json" for an example api contract
        //add dmnname into json and direct to that dmn name.
        //dynamically get URL: String url = ((HttpServletRequest)request).getRequestURL().toString(); this should work.


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        
        URI uri = new URI("http://localhost:8080/Traffic%20Violation");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity =
        restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();

    }

    @PostMapping("/batch")
    @ResponseBody
    public String Batch(@RequestBody String body, HttpMethod method, HttpServletRequest request) throws URISyntaxException
    {
        //receives a json list and a dmn name.
        //for each item in that list, execute the dmn name.(loop)
        //
        System.out.println("hello");    
        return "hello world";
     }



}