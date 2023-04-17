package dragon;

import Users.User;

public class Dragon implements Comparable<Dragon> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long age; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0
    private final DragonType type; //Поле не может быть null
    private User owner;

    public Dragon(String name, long age, int weight, DragonType type) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.type = type;
    }

    public Dragon(int id, String name, long age, int weight, DragonType type) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.type = type;
    }

    public Dragon(String name, long age, int weight, DragonType type, User owner) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.type = type;
        this.owner = owner;
    }

    public Dragon(int id, String name, long age, int weight, DragonType type, User owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.type = type;
        this.owner = owner;
    }

    @Override
    public int compareTo(Dragon dragon) {
        return (int) (age - dragon.age);
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", type=" + type +
                ", owner=" + owner.getName() + " " + owner.getSurname() +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DragonType getType() {
        return type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
