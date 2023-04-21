package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/habitacion")
    public ResponseEntity<Room> crear(@RequestBody Room room){
        Room room1 = roomService.crear(room);
        return ResponseEntity.ok(room1);
    }
    }

