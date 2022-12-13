package com.kruger.vacunacion.controller;

import com.kruger.vacunacion.entity.Empleado;
import com.kruger.vacunacion.enums.EstadoVacunacionEnum;
import com.kruger.vacunacion.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.kruger.vacunacion.util.Constants.MSG_OPERACION_EXITOSA;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("empleado")
public class EmpleadoController {

    private EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @Operation(description = "Crear un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Empleado empleado){
        try{
            return ResponseEntity.ok(empleadoService.crearEmpleado(empleado));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(description = "Editar un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @PostMapping("/editar")
    public ResponseEntity<?> editar(@RequestBody Empleado empleado){
        try{
            empleadoService.actualizarEmpleado(empleado);
            return ResponseEntity.ok(MSG_OPERACION_EXITOSA);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(description = "Eliminar un usuario  mediante su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @DeleteMapping("/eliminar/{idEmpleado}")
    public ResponseEntity<?> eliminar(@PathVariable @Parameter(name = "idEmpleado", description = "Identificador del empleado") Long idEmpleado){
        try{
            empleadoService.eliminarUsuario(idEmpleado);
            return ResponseEntity.ok(MSG_OPERACION_EXITOSA);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(description = "Listar todos los empleados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Empleado.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        try{
            return ResponseEntity.ok(empleadoService.listar());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(description = "Listar por estado de vacunación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Empleado.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @GetMapping("/listar-estado/{estadoVacunacion}")
    public ResponseEntity<?> listarPorEstadoVacunacion(@PathVariable @Parameter(name = "estadoVacunacion", description = "Estado de vacunacion VACUNADO/NOVACUNADO") EstadoVacunacionEnum estadoVacunacion){
        try{
            return ResponseEntity.ok(empleadoService.listarPorEstadoVacunacion(estadoVacunacion));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(description = "Listar empleados por tipo de vacuna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Empleado.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @GetMapping("/listar-por-vacuna/{idVacuna}")
    public ResponseEntity<?> listarPorTipoVacuna(@PathVariable @Parameter(name = "idVacuna", description = "Identificador del tipo de vacuna") Long idVacuna){
        try{
            return ResponseEntity.ok(empleadoService.listarPorTipoVacuna(idVacuna));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(description = "Listar empleados vacunados en un rango de fechas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La operación fue realizada con exito.", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Empleado.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    })
    @GetMapping("/listar-por-fecha/{fechaInicio}/{fechaFin}")
    public ResponseEntity<?> listarPorFechaVacunacion(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") @Parameter(name = "fechaInicio", description = "Fecha de inicio del rango de busqueda") Date fechaInicio,
                                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") @Parameter(name = "fechaFin", description = "Fecha de fin del rango de busqueda") Date fechaFin){
        try{
            return ResponseEntity.ok(empleadoService.listarPorFechaVacunacion(fechaInicio, fechaFin));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
