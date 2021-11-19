package StudentCourse.models;

public class Cbox {
    private final int ID;
    private final String name;

    // create a CboxResource with id and name attributes
    public Cbox(int id, String name) {
        this.ID = id;
        this.name = name;
    }

    // getters for the ids and names in the comboboxes
    public int getID() { return this.ID; }
    public String getName() { return this.name; }
}
