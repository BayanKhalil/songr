package com.example.songr;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    @GetMapping("/capitalize/{word}")

    public String capitalizeIt(@PathVariable String word, Model model) {
        model.addAttribute("word", word.toUpperCase());
        return "capitalize";
    }

    @GetMapping("/albums")
    public String albums(Model model) {
        List<Album> allAlbums = new ArrayList<>();
        Album firstOne=new Album("SAPIENTIAL","Sami Yusuf",3,12,"https://pbs.twimg.com/media/Ebmm-XdX0AETq3f.jpg");
        Album secondOne=new Album("Madad","Sami Yusuf",5,66,"https://samiyusufofficial.com/wp-content/uploads/2021/05/Madad-24.jpg");
        Album thirdOne =new Album("ISMAANE","Hamza Namira",7,89,"https://i1.sndcdn.com/artworks-000099840186-qjwlp2-t500x500.jpg");
        allAlbums.add(firstOne);
        allAlbums.add(secondOne);
        allAlbums.add(thirdOne);
        model.addAttribute("allAlbums", allAlbums);
        return "albums";
    }
}