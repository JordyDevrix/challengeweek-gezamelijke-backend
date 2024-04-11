package com.example.webshop.dao;

import com.example.webshop.models.Product;
import com.example.webshop.models.Webshop;
import com.example.webshop.dto.WebshopDTO;
import com.example.webshop.dao.WebshopDAO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private WebshopDAO webshopDAO;
    private Webshop webshop;

    public ProductDAO(WebshopDAO webshopDAO) {
        this.webshopDAO = webshopDAO;
    }


    public List<Product> getAllProducts(){
        List<WebshopDTO> webshopList = this.webshopDAO.getAllWebshops();
        List<Product> productList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for (WebshopDTO webshop : webshopList) {
            String url = (webshop.getUrl() + "/pub/products/all");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String responseBody = response.body();

                // Parse the JSON response body into a list of Product objects
                List<Product> productsFromWebshop = mapper.readValue(responseBody, new TypeReference<List<Product>>(){});

                for (Product product : productsFromWebshop) {
                    // give product id the webshop prefix
                    product.setId(webshop.getPrefix() + "-" + product.getId());
                }

                // Add the list of Product objects to the productList
                productList.addAll(productsFromWebshop);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return productList;
    }

    public Product getProductById(String id){
        // get prefix from id
        String prefix = id.split("-")[0];

        // uppercase the prefix
        prefix = prefix.toUpperCase();

        // get id from id
        String productId = id.split("-")[1];

        WebshopDTO webshop = this.webshopDAO.getWebshopByPrefix(prefix);
        String url = webshop.getUrl() + "/pub/products/" + productId;
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
            Product productFromWebshop = mapper.readValue(responseBody, new TypeReference<Product>(){});

            // give product id the webshop prefix
            productFromWebshop.setId(webshop.getPrefix() + "-" + productFromWebshop.getId());

            return productFromWebshop;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
