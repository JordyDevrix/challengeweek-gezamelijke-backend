package com.example.webshop.dao;

import org.springframework.stereotype.Component;
import com.example.webshop.models.Webshop;
import com.example.webshop.dto.WebshopDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebshopDAO {
    private WebshopRepository webshopRepository;

    public WebshopDAO(WebshopRepository webshopRepository) {
        this.webshopRepository = webshopRepository;
    }

    public WebshopDTO createWebshop(WebshopDTO webshopDTO) {
        Webshop webshop = new Webshop(webshopDTO.getName(), webshopDTO.getUrl(), webshopDTO.getPrefix());
        webshopRepository.save(webshop);
        return webshopDTO;
    }

    public List<WebshopDTO> getAllWebshops() {
        List<Webshop> webshops = webshopRepository.findAll();
        List<WebshopDTO> webshopDTOs = new ArrayList<>();
        for (Webshop webshop : webshops) {
            webshopDTOs.add(new WebshopDTO(webshop.getName(), webshop.getUrl(), webshop.getPrefix()));
        }
        return webshopDTOs;
    }

    public WebshopDTO getWebshopByPrefix(String prefix) {
        Webshop webshop = webshopRepository.findByPrefix(prefix);
        return new WebshopDTO(webshop.getName(), webshop.getUrl(), webshop.getPrefix());
    }

    public WebshopDTO getWebshopById(long webshopId) {
        Webshop webshop = webshopRepository.findById(webshopId).get();
        return new WebshopDTO(webshop.getName(), webshop.getUrl(), webshop.getPrefix());
    }

    public WebshopDTO updateWebshop(WebshopDTO webshopDTO) {
        Webshop webshop = webshopRepository.findById(webshopDTO.getId()).get();
        webshop.setName(webshopDTO.getName());
        webshop.setUrl(webshopDTO.getUrl());
        webshop.setPrefix(webshopDTO.getPrefix());
        webshopRepository.save(webshop);
        return webshopDTO;
    }



}
