package model;

public class Animal implements Comparable<Animal>{
    private int Id;
    private String species;  // dyreart
    private String type;  // bird, Mammal , Reptile
    private String habitat;


    // constructer other classes can use
    public Animal(int id, String species, String type, String habitat) {
        this.Id = id;
        this.species = species;
        this.type = type;
        this.habitat = habitat;
    }

    // Getter and setter
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String toString()
    {
        return Id+"\t"+ species +"\t"+type+"\t"+ habitat;
    }

    // interface
    @Override
    public int compareTo(Animal o) {
        if(type.equals(o.type) )
        {
            return habitat.compareToIgnoreCase(o.habitat);
        }
        else
            return type.compareToIgnoreCase(o.type);
    }
}

