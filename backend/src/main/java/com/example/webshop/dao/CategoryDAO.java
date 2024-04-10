package com.example.webshop.dao;


import com.example.webshop.dto.CategoryDTO;
import com.example.webshop.dto.WebshopDTO;
import com.example.webshop.models.Category;
import com.example.webshop.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CategoryDAO {

    private WebshopDAO webshopDAO;

    public CategoryDAO(WebshopDAO webshopDAO) {
        this.webshopDAO = webshopDAO;
    }

    public List<Category> getAllCategories(){
        List<WebshopDTO> webshopList = this.webshopDAO.getAllWebshops();
        List<Category> categoryList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for (WebshopDTO webshop : webshopList) {
            String url = (webshop.getUrl() + "/pub/categories/all");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String responseBody = response.body();

                // Parse the JSON response body into a list of Product objects
                List<Category> categoriesFromWebshop = mapper.readValue(responseBody, new TypeReference<List<Category>>(){});

                for (Category category : categoriesFromWebshop) {
                    // give product id the webshop prefix
                    category.setId(webshop.getPrefix() + "-" + category.getId());
                    Set<Product> productsFromCategorie = category.getProducts();

                    for (Product product : productsFromCategorie) {
                        // give product id the webshop prefix
                        product.setId(webshop.getPrefix() + "-" + product.getId());
                    }
                }

                // Add the list of Product objects to the productList
                categoryList.addAll(categoriesFromWebshop);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return categoryList;
    }

    public Category getCategoryById(String id){
        // get prefix from id
        String prefix = id.split("-")[0];
        // get id from id
        String categoryId = id.split("-")[1];

        WebshopDTO webshop = this.webshopDAO.getWebshopByPrefix(prefix);
        String url = webshop.getUrl() + "/pub/categories/" + categoryId;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            // Parse the JSON response body into a Product object
            Category categoryFromWebshop = mapper.readValue(responseBody, new TypeReference<Category>(){});

            // give product id the webshop prefix
            categoryFromWebshop.setId(webshop.getPrefix() + "-" + categoryFromWebshop.getId());
            Set<Product> productsFromCategorie = categoryFromWebshop.getProducts();

            for (Product product : productsFromCategorie) {
                // give product id the webshop prefix
                product.setId(webshop.getPrefix() + "-" + product.getId());
            }

            return categoryFromWebshop;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createCategory(CategoryDTO categoryDTO, long webshopId) {
        WebshopDTO webshop = this.webshopDAO.getWebshopById(webshopId);
        String url = webshop.getUrl() + "/admin/categories/create";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).
                POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + categoryDTO.name + "\"}"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return "new category created";

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "category creation failed";
        }

    }
}
