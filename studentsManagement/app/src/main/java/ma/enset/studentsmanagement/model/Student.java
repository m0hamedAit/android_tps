package ma.enset.studentsmanagement.model;


import java.io.Serializable;

public class Student implements Serializable {
    private String Prenom;
    private String nom;
    private String email;
    private String tel;
    private String sexe;

    public Student(String prenom, String nom, String email, String tel, String sexe) {
        Prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.tel = tel;
        this.sexe = sexe;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
