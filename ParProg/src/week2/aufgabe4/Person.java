package week2.aufgabe4;

public class Person {

    private Room room;

    public Person(Room aCurrentRoom) {
        enterRoom(aCurrentRoom);
    }

    public void enterRoom(Room aCurrentRoom) {
        room = aCurrentRoom;
        try {
            room.enter();
        }
        catch (InterruptedException anEx) {
            anEx.printStackTrace();
        }
    }

    public void exitRoom() {
        room.exit();
        room = null;
    }
}
