import java.util.ArrayList;
import java.util.List;

/*
A floor will have one or more rooms
A floor cannot have other floors on it
A room will support the following actions:
book(String guestName) - this books a room for a specific person.
For this example, we will not model a person object and instead use a simple string value.
clean() - this cleans the room
When applied to an entire floor, these actions delegate to each room
 */
public class HotelManager {
    public static void main(String[] args) {
        Logger.getInstance().log("Managing hotel...");

        // create hotel rooms
        HotelRoom room1 = new HotelRoom();
        HotelRoom room2 = new HotelRoom();
        HotelRoom room3 = new HotelRoom();

        // create hotel floors
        HotelFloor floor1 = new HotelFloor();
        HotelFloor floor2 = new HotelFloor();

        // add hotel rooms to hotel floors
        floor1.addHotelRoom(room1);
        floor2.addHotelRoom(room2);
        floor2.addHotelRoom(room3);

        // take actions on rooms and floors and examine your output to ensure you implemented the desired
        // behaviors
        floor1.clean();
        room1.book("Caroline");
        room2.book("Mahdis");
        floor1.removeHotelRoom(room3);
    }
}

interface HotelRoomInterface {
    void book(String guestName);
    void clean();
}

class HotelRoom implements HotelRoomInterface {
    public void book(String guestName) {
        Logger.getInstance().log("Booked a room for " + guestName);
    }
    public void clean() {
        Logger.getInstance().log("Cleaned room");
    }
}

class HotelFloor implements HotelRoomInterface {
    private List<HotelRoomInterface> hotelRooms = new ArrayList<HotelRoomInterface>();

    public void book(String guestName) {
        hotelRooms.forEach(child -> {
            child.book(guestName);
        });
    }

    public void clean() {
        hotelRooms.forEach(child -> child.clean());
    }

    public void addHotelRoom(HotelRoomInterface hotelRoom) {
        hotelRooms.add(hotelRoom);
        Logger.getInstance().log("Added hotel room:" + hotelRoom);
    }

    public void removeHotelRoom(HotelRoomInterface hotelRoom) {
        hotelRooms.remove(hotelRoom);
        Logger.getInstance().log("Removed hotel room:" + hotelRoom);
    }
}