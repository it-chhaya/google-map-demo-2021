package com.devkh.mapdemo;

public class PitchCard {

    private Integer image;
    private String name;

    public PitchCard(Integer image, String name) {
        this.image = image;
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PitchCard{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
