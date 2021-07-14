package com.example.songr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    @GetMapping("/capitalize/{word}")

    public String capitalizeIt(@PathVariable String word, Model model) {
        model.addAttribute("word", word.toUpperCase());
        return "capitalize";
    }
    @GetMapping("/")
    public String splashPage() {
        return "splash";
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

        //>>>>>>>>>>>>add albums<<<<<<<

        model.addAttribute("newAlbum", new Album());
        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);
        return "albums";

    }

//    >>>>>>>>>lab12>>>> >>>>>>>>>>>>>>>>>>




    @PostMapping("/albums")
    public String albumSubmit(@ModelAttribute Album addAlbum) {
//        System.out.println(title);
//        System.out.println(artist);
//        System.out.println(songCount);
//        System.out.println(length);
//        System.out.println(imageUrl);
        albumRepository.save(addAlbum);
        return "redirect:/albums";
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>lab13>error when adding song id error>>>>>>>>>>>>>>>>>>>>>>>>>


@GetMapping("/albums/{id}")
public String getAlbumSong(@PathVariable long id, Model model){
    Album theAlbum =albumRepository.getById(id);

    model.addAttribute("album", theAlbum);
    model.addAttribute("addSong", new Song());
    return "details";
}

    @PostMapping("/albums/{id}")
    public String songSubmit(@RequestParam String title, @RequestParam long length, short trackNumber, @PathVariable long id){
        Album album_id =albumRepository.getById(id);
        Song newSong = new Song(title, length, trackNumber, album_id);
        songRepository.save(newSong);
        return "redirect:/albums/{id}";
    }

}
