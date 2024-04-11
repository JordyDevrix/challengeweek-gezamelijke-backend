package com.example.webshop.utils;

import com.example.webshop.dao.WebshopDAO;
import com.example.webshop.models.CustomUser;
import com.example.webshop.models.Webshop;
import com.example.webshop.dto.WebshopDTO;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.webshop.dao.UserRepository;


@Component
public class Seeder {
    private WebshopDAO webshopDAO;
    private final PasswordEncoder passwordEncoder;
    private CustomUser customUser;
    private UserRepository userDAO;

    public Seeder(WebshopDAO webshopDAO, PasswordEncoder passwordEncoder, UserRepository userDAO) {
        this.webshopDAO = webshopDAO;
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event){
        this.seedApi();
    }

    private void seedApi(){

        Webshop webshop1 = new Webshop("Meuboons", "http://s1121717.student.inf-hsleiden.nl:21717/api", "MEUBOONS");
        Webshop webshop2 = new Webshop("CardCenter", "http://s1149771.student.inf-hsleiden.nl:29771/api", "CARDCENTER");
        Webshop webshop3 = new Webshop("Plenty", "http://s1148280.student.inf-hsleiden.nl:28280/api", "PLENTY");
        Webshop webshop4 = new Webshop("SoundsLike", "http://s1151166.student.inf-hsleiden.nl:21166/api", "SOUNDSLIKE");
        Webshop webshop5 = new Webshop("Shoes For All", "http://s1150300.student.inf-hsleiden.nl:20300/api", "SHOESFORALL");

        this.webshopDAO.createWebshop(new WebshopDTO(webshop1.getName(), webshop1.getUrl(), webshop1.getPrefix()));
        this.webshopDAO.createWebshop(new WebshopDTO(webshop2.getName(), webshop2.getUrl(), webshop2.getPrefix()));
        this.webshopDAO.createWebshop(new WebshopDTO(webshop3.getName(), webshop3.getUrl(), webshop3.getPrefix()));
        this.webshopDAO.createWebshop(new WebshopDTO(webshop4.getName(), webshop4.getUrl(), webshop4.getPrefix()));
        this.webshopDAO.createWebshop(new WebshopDTO(webshop5.getName(), webshop5.getUrl(), webshop5.getPrefix()));



        String encodedPassword = passwordEncoder.encode("admin");
        CustomUser user = new CustomUser("admin", encodedPassword, "ROLE_ADMIN");
        this.userDAO.save(user);

    }
}
