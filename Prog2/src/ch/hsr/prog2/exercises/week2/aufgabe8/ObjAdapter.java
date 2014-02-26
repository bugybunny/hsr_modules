package ch.hsr.prog2.exercises.week2.aufgabe8;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class ObjAdapter extends AbstractBikeStore {

    protected List<Bike> bikeList = new ArrayList<>();

    public ObjAdapter() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addBike(Bike aBike) {
        bikeList.add(aBike);
    }

    @Override
    public Bike removeBike(int anIndex) {
        return bikeList.remove(anIndex);
    }

    @Override
    public void clear() {
        bikeList.clear();
    }

    @Override
    public int size() {
        return bikeList.size();
    }

}
