package agh.gui;

import agh.SimulationConfigVariant;

import java.lang.constant.Constable;

public class EnumStringParser {

    private final String val;

    EnumStringParser(String value) {
        this.val = value;
    }

    Constable getValue() {
        if(val.equals("LITTLE_CRAZY") || val.equals("FULL_PREDISPOSITION"))
            return SimulationConfigVariant.AnimalBehavior.valueOf(this.val);
        if(val.equals("EQUATOR") || val.equals("TOXIC_GRAVES"))
            return SimulationConfigVariant.PlantGrowth.valueOf(this.val);
        if(val.equals("RANDOM") || val.equals("CORRECTED"))
            return SimulationConfigVariant.Mutation.valueOf(this.val);
        if(val.equals("GLOBE") || val.equals("HELL_PORTAL"))
            return SimulationConfigVariant.MapLoop.valueOf(this.val);
        return null;
    }
}
