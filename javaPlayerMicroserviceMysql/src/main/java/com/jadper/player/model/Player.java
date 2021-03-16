package com.jadper.player.model;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    private String name;
    private int jersey;
    private int age;
    private String born;

    public Player() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJersey(int jersey) {
         this.jersey = jersey;
    }

    public int getJersey() {
        return this.jersey;
    }

    public void setAge(int age) {
        this.age = age;
   }

   public int getAge() {
       return this.age;
   }

   public void setBorn(String born) {
    this.born = born;
}

    public String getBorn() {
      return this.born;
    }


}
