package com.kruger.vacunacion.controller;

import com.kruger.vacunacion.entity.Empleado;
import com.kruger.vacunacion.entity.Vacuna;
import com.kruger.vacunacion.service.VacunaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("vacuna")
public class VacunaController {

    private VacunaService vacunaService;

    public VacunaController(VacunaService vacunaService) {
        this.vacunaService = vacunaService;
    }

    @Operation(description = "Listar todos los tipos de vacunas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        try{
            return ResponseEntity.ok(vacunaService.listar());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
