package agh;

import agh.world.IMap;

import java.util.*;

public class Animal extends AbstractGameObject {
    int age = 0;
    boolean isDead = false;
    int countEatenGrass = 0;
    private Directions direction = Directions.getRandom();

    IMap map;
    int deathDate = -1;
    private final Genotype genotype;
    private int children = 0;
    private final IGenePicker genePicker;

//    private List<IAnimalObserver> observers = new ArrayList<>();

    public Animal(IMap map, int energy, IGenePicker genePicker) {
        this.energy = energy;
        this.map = map;
        this.genotype = new Genotype();
        this.genePicker = genePicker;
        this.genePicker.setGenotype(this.genotype.getGenotype());
        Random random = new Random();
        this.position = new Vector2d(random.nextInt(this.map.getUpperRight().x + 1), random.nextInt(this.map.getUpperRight().y + 1));
    }


    public void decreaseEnergy(int energy) {
        this.energy -= energy;
    }

    public void increaseEnergy(int energy) {
        this.energy += energy;
    }

    public void age() {
        age++;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isDead() {
        if (getEnergy() <= 0) {
            this.isDead = true;
            return true;
        }
        return false;
    }

    public int getCountEatenGrass() {
        return this.countEatenGrass;
    }

    public void addToCountEatenGrass() {
        countEatenGrass++;
    }

    public void addChild() {
        children++;
    }

    public int getChildrenAmount() {
        return children;
    }

    public void move() {
        this.age += 1;
        int gene = this.genePicker.getNextGene();
        switch (gene) {
            case 1 -> this.direction = this.direction.next();
            case 2 -> this.direction = this.direction.next().next();
            case 3 -> this.direction = this.direction.next().next().next();
            case 4 -> this.direction = this.direction.next().next().next().next();
            case 5 -> this.direction = this.direction.previous().previous().previous();
            case 6 -> this.direction = this.direction.previous().previous();
            case 7 -> this.direction = this.direction.previous();
            default -> {
            }
        }

        position.add(direction.toUnitVector());
    }
    public int currentActiveGene(){
        return genePicker.getCurrentGeneIndex();
    }

    public ArrayList<Integer> getGenotype() {
        return this.genotype.getGenotype();
    }

    public void die(int day) {
//        this.cell.removeElement(this);
//        this.map.removeElement(this);
        this.deathDate = day;
    }

    public int getAge() {
        return this.age;
    }

//    private void changePosition(Vector2d oldPosition, Vector2d newPosition) {
//        for (IAnimalObserver observer : this.observers) {
//            observer.positionChanged(this, oldPosition, newPosition);
//        }
//    }


}