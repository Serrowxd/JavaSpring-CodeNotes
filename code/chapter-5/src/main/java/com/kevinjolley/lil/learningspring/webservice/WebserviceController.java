package com.kevinjolley.lil.learningspring.webservice;

import com.kevinjolley.lil.learningspring.business.ReservationService;
import com.kevinjolley.lil.learningspring.business.RoomReservation;
import com.kevinjolley.lil.learningspring.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
