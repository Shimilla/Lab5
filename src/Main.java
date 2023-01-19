import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main  {
  public static void main(String[] args) throws IOException {
      Dragon dragon = new Dragon(1);
      Dragon horseDrag = new Dragon(2);

      ListDragon listDragon = new ListDragon();
      listDragon.add(dragon);

      CollectionDragonManager collectionManager = new CollectionDragonManager(listDragon);
      collectionManager.addM(horseDrag);







//      ReaderJson readerJson = new ReaderJson();
//      String input = readerJson.read();
//      byte[] bytesToRead = input.getBytes();
//      String output = "c:\\Games\\Java1.txt";
//
//      FileOutputStream fileOutputStream = new FileOutputStream(output);
//      fileOutputStream.write(bytesToRead);
//
//
      for (Dragon drag : listDragon) {
          System.out.println(drag);
      }



  }
}
class Dragon {
    private int id;

    public Dragon (int id) {
        this.id = id;
    }
}
class ListDragon extends ArrayList<Dragon> {

}

class ReaderJson implements Reader {
    @Override
    public String read() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }
}

interface Reader {
    String read();
}

interface Writer {
    void write();
}

class CollectionDragonManager {
    private ListDragon listDragon;

    public CollectionDragonManager(ListDragon listDragon) {
        this.listDragon = listDragon;
    }

    public boolean addM(Dragon dragon) {
        listDragon.add(dragon);
        return true;
    }
}

