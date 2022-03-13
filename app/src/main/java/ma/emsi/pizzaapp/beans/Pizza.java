package ma.emsi.pizzaapp.beans;



public class Pizza {
    private int id;
    private String nom;
    private double prix;
    private int image;
    private static int comp;
    private String desc;

    public Pizza(String nom, double prix, int image,String desc) {
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.id = ++comp;
        this.desc=desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
