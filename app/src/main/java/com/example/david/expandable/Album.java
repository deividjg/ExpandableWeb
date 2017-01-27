package com.example.david.expandable;

public class Album {

    String artista, nombre, portada;
    int año;

    public Album(){
        this.artista = "";
        this.nombre = "";
        this.año = 0;
        this.portada = "";
    }

    public Album(String artista, String nombre, int año, String portada){
        this.artista = artista;
        this.nombre = nombre;
        this.año = año;
        this.portada = portada;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}
