package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.AbstractIntegrationTest;
import com.bajorek_kalandyk.crm.domain.form.ProductForm;
import com.bajorek_kalandyk.crm.domain.model.Product;
import com.bajorek_kalandyk.crm.domain.model.ProductCategory;
import com.bajorek_kalandyk.crm.repository.ProductCategoryRepository;
import com.bajorek_kalandyk.crm.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ProductRestControllerIT extends AbstractIntegrationTest
{
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void properlyCreateProductWhenUsingRestController() throws Exception
    {
        // given
        final BigDecimal price = valueOf(123.99);
        final ProductCategory category = productCategoryRepository.save(ProductCategory.builder()
                .name("TEST_CATEGORY")
                .build());
        final ProductForm productForm = ProductForm.builder()
                .name("IT_TEST")
                .description("IT_DESC")
                .price(price)
                .categoryId(category.getId())
                .build();

        // when
        mockMvc.perform(post("/product/create")
                .content(objectMapper.writeValueAsString(productForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        Product productEntity = productRepository.findByName("IT_TEST");
        assertThat(productEntity).isNotNull();
        assertThat(productEntity.getCategory()).isEqualTo(category);
        assertThat(productEntity.getPrice()).isEqualTo(price);
    }

    @Test
    public void properlyGetProductByIdWhenUsingRestController() throws Exception
    {
        // given
        final BigDecimal price = valueOf(123.99);
        final ProductCategory category = productCategoryRepository.save(ProductCategory.builder()
                .name("TEST_CATEGORY")
                .build());
        final Product product = productRepository.save(Product.builder()
                .name("IT_TEST")
                .description("IT_DESC")
                .price(price)
                .category(category)
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(get("/product/getById/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Product response = objectMapper.readValue(contentAsString, Product.class);
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(product);
    }
}