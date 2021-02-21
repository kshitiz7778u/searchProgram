package com.demo.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.demo.search.dto.Product;

//Integration Test Case

@SpringBootTest(classes = SearchProgramApplication.class, 
webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
    private int port;
 
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
	public void contextLoads() {
	}
    
    @Test
    public void testSearchFlow() {
    	ResponseEntity<Product> getResponse = this.restTemplate
                .getForEntity("http://localhost:"+port+"/v1/product/1", Product.class);
    	assertEquals(204, getResponse.getStatusCodeValue());
        Product product = new Product();
        product.setProductId(1);
        product.setTitle("title");
        ResponseEntity<String> postResponse = this.restTemplate
        		.postForEntity("http://localhost:"+port+"/v1/product", product, String.class);
        assertEquals(201, postResponse.getStatusCodeValue());
        getResponse = this.restTemplate
                .getForEntity("http://localhost:"+port+"/v1/product/1", Product.class);
    	assertEquals(200, getResponse.getStatusCodeValue());
    	product.setTitle("updatedTitle");
    	this.restTemplate.put("http://localhost:"+port+"/v1/product", product, Product.class);
    	getResponse = this.restTemplate
                .getForEntity("http://localhost:"+port+"/v1/product/1", Product.class);
    	assertEquals("updatedTitle", getResponse.getBody().getTitle());
    }
    
}
