package model;

import java.util.ArrayList;
import java.util.Collections;



public class AnimalsData {
    private final ArrayList<Animal> animals;  // create a list called animals

    public AnimalsData()
    {
        animals= new ArrayList<>();
    }

    // add methode ( later use in GUI)
    public void add(Animal a) //called a
    {
        animals.add(a);  // add animal in a list called a
    }

    // remove  animal from the list by id
    public boolean remove(int id)
    {
        for(int i=0;i<animals.size();i++)
        {
            if(animals.get(i).getId()==id)
            {
                animals.remove(i);
                return true;
            }
        }
        return false; // if not found
    }

    // update methode, the value of animal, if wanted change.

    public boolean update(int id, String species, String type,String habitat)
    {
        Animal a;
        for (Animal animal : animals) { // for loop
            if ((a = animal).getId() == id) {
                //update the new values only if they are not empty
                if (!species.equals("")) // not
                    a.setSpecies(species);
                if (!type.equals(""))
                    a.setType(type);
                if (!habitat.equals(""))
                    a.setHabitat(habitat);
                return true;
            }
        }
        return false; // if not found
    }

    // sort methode
    public void sort(String type,String habitat)
    {
        //first sort based on type and habitat as defined by compareTo in model.Animal class
        Collections.sort(animals);

        Animal a;
        // same type and habitat as specified order.
        for(int i=0;i<animals.size();i++)
        {
            a=animals.get(i);
            if(a.getType().equalsIgnoreCase(type) && a.getHabitat().equalsIgnoreCase(habitat))
            {
                animals.remove(a);
                animals.add(0,a); //bring it to front of list
            }
        }
    }

    // UI for the GUI
    public String toString()
    {
        StringBuilder str= new StringBuilder("  Animal ID   |      Species     |        Type            |        Habitat     |  ");
        for (Animal animal : animals) {
            str.append("\n").append(animal);
        }
        return str.toString();
    }

}