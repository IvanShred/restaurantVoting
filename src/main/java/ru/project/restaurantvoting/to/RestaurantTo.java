package ru.project.restaurantvoting.to;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RestaurantTo extends BaseTo {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 200)
    private String address;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
