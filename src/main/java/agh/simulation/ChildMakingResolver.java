package agh.simulation;

import agh.Animal;
import agh.IPositionChangeObserver;
import agh.world.IMap;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChildMakingResolver {
    private final IMap map;
    private final CopyOnWriteArrayList<Animal> animals;
    private final int healthyStatus;

    private final HashSet<Animal> visitedAnimals = new HashSet<>();

    public ChildMakingResolver(IMap map, CopyOnWriteArrayList<Animal> animals, int healthyStatus) {
        this.map = map;
        this.animals = animals;
        this.healthyStatus = healthyStatus;
    }

    public void resolve() {
        var positions = this.map.getMapObjects().entrySet();

        for (var position : positions) {
            var occupants = position.getValue();
            var healthyNeighbours = occupants
                    .stream()
                    .filter(e -> e instanceof Animal).map(e -> (Animal) e)
                    .filter(e -> e.getEnergy() >= healthyStatus)
                    .toList();

            if (healthyNeighbours.size() != 0 && healthyNeighbours.size() % 2 == 0) {
                for (int i = 0; i < healthyNeighbours.size() - 1; i++) {
                    var mom = healthyNeighbours.get(i);
                    var dad = healthyNeighbours.get(i++);

                    if (visitedAnimals.contains(mom) || visitedAnimals.contains(dad))
                        continue;

                    var children = new Animal(mom, dad);
                    children.addObserver( (IPositionChangeObserver) this.map);
                    this.map.place(children);

                    children.addObserver((IPositionChangeObserver) this.map);
                    this.animals.add(children);
                    
                    this.visitedAnimals.add(mom);
                    this.visitedAnimals.add(dad);
                    this.visitedAnimals.add(children);
                }
            }
        }
    }
}
