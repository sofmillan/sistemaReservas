package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="Room")
@RestController
@RequestMapping("api/v1")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @ApiOperation(value = "Register a new room")
    @ApiResponses( value= {
            @ApiResponse(code = 200, message = "Room registered successfully"),
            @ApiResponse(code = 400, message = "Data is not valid, check the input"),
            @ApiResponse(code = 409, message = "This room is registered already")
    })
    @PostMapping("/room")
    public ResponseEntity<Room> registerRoom(@RequestBody Room room){
        Room createdRoom = roomService.registerRoom(room);
        return ResponseEntity.ok(createdRoom);
    }
    }

