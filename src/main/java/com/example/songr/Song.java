package com.example.songr;

import javax.persistence.*;

@Entity
public class Song {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    String title;
    double length ;
    double trackNumber;

    @ManyToOne
    @JoinColumn(name="album_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    Album album;

    public Song() {}

    public Song( String title, double length, double trackNumber, Album album) {
        this.title = title;
        this.length = length;
        this.trackNumber = trackNumber;
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", trackNumber=" + trackNumber +
                ", album=" + album +
                '}';
    }

    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(double trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
