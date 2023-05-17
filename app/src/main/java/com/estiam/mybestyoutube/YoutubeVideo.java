package com.estiam.mybestyoutube;

import java.io.Serializable;

public class YoutubeVideo implements Serializable {
    private long id;
    private String titre;
    private String decscription;
    private String url;
    private String categroie;
    private long favori;

    public YoutubeVideo(String titre, String decscription, String url, String categroie, long favori) {
        this.titre = titre;
        this.decscription = decscription;
        this.url = url;
        this.categroie = categroie;
        this.favori = favori;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDecscription() {
        return decscription;
    }

    public void setDecscription(String decscription) {
        this.decscription = decscription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategroie() {
        return categroie;
    }

    public void setCategroie(String categroie) {
        this.categroie = categroie;
    }

    public long getFavori() {
        return favori;
    }

    public void setFavori(long favori) {
        this.favori = favori;
    }
}
