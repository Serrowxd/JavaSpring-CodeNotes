package com.kevinjolley.lil.learningspring.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kevinjolley.lil.learningspring.data.Guest;
import com.kevinjolley.lil.learningspring.data.GuestRepository;
import com.kevinjolley.lil.learningspring.data.Reservation;
import com.kevinjolley.lil.learningspring.data.ReservationRepository;
import com.kevinjolley.lil.learningspring.data.Room;
import com.kevinjolley.lil.learningspring.data.RoomRepository;
import org.springframework.stereotype.Service;

// This allows spring to recognize the class
// @Service on business services gives you a good place to write abstract oriented programming
// that responds to service invocations.
@Service
public class ReservationService {
    // **Notes**
    // You can add @Autowired to each of these
    // Issue is you have written code that is not easy to test
    // You can also create setters for each of the elements, but would need to annotate with @Autowired
    // Issue is you deviate from good OOP practices
    // This class has a hard requirement for roomRepository, and by using setters there is no hard requirement other than spring will run it.

    // Best practice is to make them all final, then create a constructor.
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }
}

