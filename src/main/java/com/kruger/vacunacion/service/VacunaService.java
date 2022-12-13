package com.kruger.vacunacion.service;

import com.kruger.vacunacion.entity.Vacuna;
import com.kruger.vacunacion.entity.VacunaEmpleado;
import com.kruger.vacunacion.repository.VacunaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunaService {
    private VacunaRepository vacunaRepository;

    public VacunaService(VacunaRepository vacunaRepository) {
        this.vacunaRepository = vacunaRepository;
    }

    public List<Vacuna> listar(){
        return vacunaRepository.findAll();
    }
}
