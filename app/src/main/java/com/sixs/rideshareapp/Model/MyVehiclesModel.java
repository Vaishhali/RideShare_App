package com.sixs.rideshareapp.Model;

/**
 * Created by RASHMI on 19-09-2016.
 */
public class MyVehiclesModel {

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isSelected;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    String name, model;
}
