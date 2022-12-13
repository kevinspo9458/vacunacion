package com.kruger.vacunacion.service;

import com.kruger.vacunacion.entity.Empleado;
import com.kruger.vacunacion.entity.Usuario;
import com.kruger.vacunacion.entity.Vacuna;
import com.kruger.vacunacion.entity.VacunaEmpleado;
import com.kruger.vacunacion.enums.EstadoVacunacionEnum;
import com.kruger.vacunacion.enums.RolUsuarioEnum;
import com.kruger.vacunacion.repository.EmpleadoRepository;
import com.kruger.vacunacion.repository.UsuarioRepository;
import com.kruger.vacunacion.repository.VacunaEmpleadoRepository;
import com.kruger.vacunacion.repository.VacunaRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.kruger.vacunacion.util.ValidacionUtils.*;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;
    private UsuarioRepository usuarioRepository;
    private VacunaEmpleadoRepository vacunaEmpleadoRepository;
    private VacunaRepository vacunaRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository, UsuarioRepository usuarioRepository, VacunaEmpleadoRepository vacunaEmpleadoRepository, VacunaRepository vacunaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.vacunaEmpleadoRepository = vacunaEmpleadoRepository;
        this.vacunaRepository = vacunaRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Empleado crearEmpleado(Empleado empleado) throws Exception{
        validarEmpleado(empleado);
        String username = generarUsuario(empleado);
        String password = RandomStringUtils.randomAlphanumeric(8);
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRolUsuario(RolUsuarioEnum.EMPLEADO);
        usuarioRepository.save(usuario);
        empleado.setUsuario(usuario);
        empleadoRepository.save(empleado);
        return empleado;
    }

    @Transactional(rollbackFor = Exception.class)
    public void actualizarEmpleado(Empleado empleado) throws Exception{
        validarEmpleado(empleado);
        validarVacunacion(empleado);
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleado.getId());
        if (!empleadoOpt.isPresent()){
            throw new Exception("El empleado no ha sido encontrado");
        }
        Empleado empleadoPersist = empleadoOpt.get();
        empleadoPersist.setCedula(empleado.getCedula());
        empleadoPersist.setNombres(empleado.getNombres());
        empleadoPersist.setApellidos(empleado.getApellidos());
        empleadoPersist.setCorreo(empleado.getCorreo());
        empleadoPersist.setFechaNacimento(empleado.getFechaNacimento());
        empleadoPersist.setDireccion(empleado.getDireccion());
        empleadoPersist.setTelefono(empleado.getTelefono());
        empleadoPersist.setEstadoVacunacion(empleado.getEstadoVacunacion());
        limpiarVacunasEmpleado(empleadoPersist);

        if (empleado.getVacunasEmpleado() != null) {
            for (VacunaEmpleado vacunaEmpleado : empleado.getVacunasEmpleado()) {
                Long idVacuna = vacunaEmpleado.getVacuna().getId();
                Optional<Vacuna> vacunaOpt = vacunaRepository.findById(idVacuna);
                if (!vacunaOpt.isPresent()) {
                    throw new Exception("La vacuna con id=" + idVacuna + " no ha sido encontrada");
                }
                Vacuna vacuna = vacunaOpt.get();
                vacunaEmpleado.setId(null);
                vacunaEmpleado.setEmpleado(empleadoPersist);
                vacunaEmpleado.setVacuna(vacuna);
                vacunaEmpleadoRepository.save(vacunaEmpleado);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarUsuario(Long id) throws Exception{
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        if (!empleadoOpt.isPresent()){
            throw new Exception("El empleado no ha sido encontrado");
        }
        Empleado empleado = empleadoOpt.get();
        limpiarVacunasEmpleado(empleado);
        Usuario usuario = empleado.getUsuario();
        empleadoRepository.delete(empleado);
        usuarioRepository.delete(usuario);
    }

    public List<Empleado> listar(){
        return empleadoRepository.findAll();
    }

    public List<Empleado> listarPorEstadoVacunacion(EstadoVacunacionEnum estadoVacunacion){
        return empleadoRepository.findByEstadoVacunacion(estadoVacunacion);
    }

    public List<Empleado> listarPorFechaVacunacion(Date fechaInicio, Date fechaFin){
        return empleadoRepository.findByFechaVacunaEmpleado(fechaInicio, fechaFin);
    }

    public List<Empleado> listarPorTipoVacuna(Long idVacuna){
        return empleadoRepository.findByIdVacunaEmpleado(idVacuna);
    }

    private void limpiarVacunasEmpleado(Empleado empleado) throws Exception{
        for (VacunaEmpleado vacunaEmpleado : empleado.getVacunasEmpleado()){
            vacunaEmpleadoRepository.delete(vacunaEmpleado);
        }
    }

    private String generarUsuario(Empleado empleado){
        int index = 0;
        String inicio = "";
        String fin = empleado.getApellidos().contains(" ") ? empleado.getApellidos().split(" ")[0] : empleado.getApellidos();
        String usuario = "";

        while (true){
            if (index < empleado.getNombres().length()){
                String letra = String.valueOf(empleado.getNombres().charAt(index));
                if (letra.equals(" ")){
                    continue;
                }
                inicio += letra;
                usuario = (inicio + fin).toUpperCase();
                index++;
            }else{
                usuario = (inicio + fin).toUpperCase() + new Random().nextInt();
            }
            if (!existeUsuario(usuario)){
                return usuario;
            }
        }
    }

    private boolean existeUsuario(String username){
        Usuario usuario = usuarioRepository.findUsuarioByUsername(username);
        return usuario != null;
    }
}
