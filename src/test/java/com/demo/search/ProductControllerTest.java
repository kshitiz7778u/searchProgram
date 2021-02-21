package com.demo.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.search.dto.Product;
import com.demo.search.service.ProductService;

public class ProductControllerTest extends AbstractTest{
	
	@MockBean
    private ProductService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getProduct() throws Exception {
	   String uri = "/v1/product/1";
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(204, status);
	}
	
	@Test
	public void createProduct() throws Exception {
	   String uri = "/v1/product";
	   Product product = new Product();
	   product.setProductId(3);
	   product.setTitle("Fan");
	   
	   String inputJson = super.mapToJson(product);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
	}
	
	@Test
	public void updateProduct() throws Exception {
	   String uri = "/v1/product";
	   Product product = new Product();
	   product.setProductId(3);
	   product.setTitle("TableFan");
	   
	   String inputJson = super.mapToJson(product);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	}
	
	@Test
	public void deleteProduct() throws Exception {
	   String uri = "/v1/product/3";
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	}

}
