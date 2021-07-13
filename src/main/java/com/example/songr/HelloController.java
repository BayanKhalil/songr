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
        return "albums";
    }

//    >>>>>>>>>lab12>>>> >>>>>>>>>>>>>>>>>>




    @PostMapping("/new")
    public RedirectView addAlbums(@RequestParam (value = "title" ,required = false, defaultValue = "title")String title,@RequestParam(value="artist") String artist,
                                  @RequestParam(value="songCount") double songCount,
                                  @RequestParam(value="length") double length,
                                  @RequestParam(value="imageUrl") String imageUrl,Model model) {
        System.out.println(title);
        System.out.println(artist);
        System.out.println(songCount);
        System.out.println(length);
        System.out.println(imageUrl);
        Album album = new Album(title, artist, songCount, length, imageUrl);
        albumRepository.save(album);

        return new RedirectView("/new");
    }

    @GetMapping("/new")
    public String getAlbums(Model model) {
        Iterable<Album> albumCollection = albumRepository.findAll();
        model.addAttribute("allAlbums", albumCollection);

        return "new";
    }


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>lab13>error when adding song id error>>>>>>>>>>>>>>>>>>>>>>>>>

    @GetMapping("/albums/{id}")
    public String getDetailView (@PathVariable long id, Model model) {
        // note that attributeName "album", the key, has to match the template (so, it matches the object
        // - ie album.title)
        model.addAttribute("allAlbums", albumRepository.getById(id));
        return "details";
    }

    // >>>>>>>>>>>>>>>>problem with id
    @PostMapping("/albums/{id}")
    public RedirectView addSongs(@RequestParam (value="id") long id,@RequestParam (value = "title")String title, @RequestParam(value="length") double length, @RequestParam(value="trackNumber") double trackNumber) {
        // find the album in the db
        Album albumId = albumRepository.getById(id);

        Song newSong = new Song(title, length, trackNumber, albumId);
        songRepository.save(newSong);
        return new RedirectView("/albums/" + id);
    }

}
