package looker;

import details.*;

public class Porthole {
    private String name;
    private Material material;

    public Porthole(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

}
