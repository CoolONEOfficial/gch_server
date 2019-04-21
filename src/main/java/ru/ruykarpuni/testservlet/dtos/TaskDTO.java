package ru.ruykarpuni.testservlet.dtos;

import java.net.URI;

public class TaskDTO {
    private String name;
    private URI[] PhotoURIs;
    private CoordinateDTO position;
    private String[] votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI[] getPhotoURIs() {
        return PhotoURIs;
    }

    public void setPhotoURIs(URI[] photoURIs) {
        PhotoURIs = photoURIs;
    }

    public CoordinateDTO getPosition() {
        return position;
    }

    public void setPosition(CoordinateDTO position) {
        this.position = position;
    }

    public String[] getVotes() {
        return votes;
    }

    public void setVotes(String[] votes) {
        this.votes = votes;
    }

    public TaskDTO(String name, URI[] photoURIs, CoordinateDTO position, String[] votes) {
        this.name = name;
        PhotoURIs = photoURIs;
        this.position = position;
        this.votes = votes;
    }
}
