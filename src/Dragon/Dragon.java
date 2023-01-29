package Dragon;

public class Dragon implements Comparable<Dragon>{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long age; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0
    private DragonType type; //Поле не может быть null

    public Dragon(String name, long age, int weight, DragonType type) {

        if (name == null || name.equals("")) {
            throw new NullPointerException("the dragon must have a name \n");
        } else {
            this.name = name;
        }
        if (age <= 0) {
            throw new IllegalArgumentException("the age must be greater than zero \n");
        } else {
            this.age = age;
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("the weight must be greater than zero \n");
        } else {
            this.weight = weight;
        }
        if (type == null) {
            throw new IllegalArgumentException("the type of dragon must be selected from the list \n");
        } else {
            this.type = type;
        }
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

    public void setAge(long age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }
}
