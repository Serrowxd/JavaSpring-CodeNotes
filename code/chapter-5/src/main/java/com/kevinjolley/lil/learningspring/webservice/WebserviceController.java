package com.kevinjolley.lil.learningspring.webservice;

import com.kevinjolley.lil.learningspring.business.ReservationService;
import com.kevinjolley.lil.learningspring.business.RoomReservation;
import com.kevinjolley.lil.learningspring.data.Guest;
import com.kevinjolley.lil.learningspring.data.Room;
import com.kevinjolley.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController // puts an @ResponseBody to every method.
@RequestMapping("/api")
public class WebserviceController {
    // Each Controller responds to a URL with the given methods within them.

    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET) // /api/reservations because of the RequestMapping above
    public List<RoomReservation> getReservations(@RequestParam(value="date", required=false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date); // return the object, spring takes care of the rest
    }

    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return this.reservationService.getRooms();
    }
}
