package br.com.globo.movies.userProfile.model;

import br.com.globo.movies.movies.model.Image;
import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {
    private int id;
    private String name;
    private RealmList<Image> images;

    public RealmList<Image> getImages() {
        return images;
    }

    public void setImages(RealmList<Image> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
