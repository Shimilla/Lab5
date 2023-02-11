package dragon;

public enum DragonType {
    WATER,
    UNDERGROUND,
    AIR,
    FIRE;

    public static void getDragonType() {
        System.out.println(WATER.name());
        System.out.println(UNDERGROUND.name());
        System.out.println(AIR.name());
        System.out.println(FIRE.name());
    }
}
