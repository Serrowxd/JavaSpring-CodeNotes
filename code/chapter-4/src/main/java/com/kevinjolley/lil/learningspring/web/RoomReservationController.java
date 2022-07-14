package com.kevinjolley.lil.learningspring.web;

import com.kevinjolley.lil.learningspring.business.ReservationService;
import com.kevinjolley.lil.learningspring.business.RoomReservation;
import com.kevinjolley.lil.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    // Best practice to write one class per URL endpoint
    // Then as many methods as is necessary to handle all the verbs that need to be responded to
    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value="date", required=false) String dateString, Model model) { // model is springframework as part of the mvc
        Date date = this.dateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", roomReservations); // this is a live model
        return "roomres";

        // return template name without the file extension - Thymeleaf specific? roomres.html = roomres string?
    }
}
