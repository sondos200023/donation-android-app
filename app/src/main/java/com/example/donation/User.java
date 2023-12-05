package com.example.donation;
public class User {
    public String nom;
    public String prenom;
    public String email;
    public String mdp;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name,String prenom,String email,String mdp) {
        this.nom = name;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }
}
