package com.kruger.vacunacion.util;

import com.kruger.vacunacion.entity.Empleado;
import com.kruger.vacunacion.enums.EstadoVacunacionEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kruger.vacunacion.util.Constants.CAMPO_APELLIDOS;
import static com.kruger.vacunacion.util.Constants.CAMPO_NOMBRES;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;

public class ValidacionUtils {

    public static void validarCedula(String cedula) throws Exception {
        if (isBlank(cedula)){
            throw new Exception("La cedula tiene un valor null o vacio");
        }
        if (!isDigits(cedula)){
            throw new Exception("La cedula debe contener solo valores numéricos");
        }
        if (cedula.length() != 10){
            throw new Exception("El número de digitos de la cédula debe de ser igual a 10");
        }
        int digitoVerificador = Integer.parseInt(String.valueOf(cedula.charAt(9)));
        Integer[] arrayCoeficientes = new Integer[]{2, 1, 2, 1, 2, 1, 2, 1, 2};

        Integer[] digitosTMP = new Integer[cedula.length()];
        int indice = 0;
        for (char valorPosicion : cedula.toCharArray()) {
            digitosTMP[indice] = Integer.parseInt(String.valueOf(valorPosicion));
            indice++;
        }

        int total = 0;
        int key = 0;

        for (Integer valorPosicion : digitosTMP) {
            if (key < arrayCoeficientes.length) {
                valorPosicion = (digitosTMP[key] * arrayCoeficientes[key]);

                if (valorPosicion >= 10) {
                    char[] valorPosicionSplit = String.valueOf(valorPosicion).toCharArray();
                    valorPosicion = (Integer.parseInt(String.valueOf(valorPosicionSplit[0]))) + (Integer.parseInt(String.valueOf(valorPosicionSplit[1])));

                }
                total = total + valorPosicion;
            }

            key++;
        }
        int residuo = total % 10;
        int resultado;

        if (residuo == 0) {
            resultado = 0;
        } else {
            resultado = 10 - residuo;
        }

        if (resultado != digitoVerificador) {
            throw new Exception("La cedula no es válida");
        }
    }

    public static void validarCorreoElectronico(String valor) throws Exception{
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(valor);
        if (!matcher.matches()){
            throw new Exception("El correo electrónico no es válido");
        }
    }

    public static void validarSoloTexto(String texto, String nombre) throws Exception{
        if (isBlank(texto)){
            throw new Exception("El contenido del campo " + nombre + " es vacío");
        }else if (!StringUtils.isAlphaSpace(texto)){
            throw new Exception("El campo " + nombre + " debe contener solo letras");
        }
    }

    public static void validarEmpleado(Empleado empleado) throws Exception{
        validarCedula(empleado.getCedula());
        validarSoloTexto(empleado.getNombres(), CAMPO_NOMBRES);
        validarSoloTexto(empleado.getApellidos(), CAMPO_APELLIDOS);
        validarCorreoElectronico(empleado.getCorreo());
        empleado.setEstadoVacunacion(empleado.getEstadoVacunacion() == null ? EstadoVacunacionEnum.NOVACUNADO : empleado.getEstadoVacunacion());
    }

    public static void validarVacunacion(Empleado empleado) throws Exception{
        if (empleado.getEstadoVacunacion() == null){
            throw new Exception("El estado de vacunación del empleado esta vacio");
        }else if (empleado.getEstadoVacunacion().equals(EstadoVacunacionEnum.VACUNADO)
                && CollectionUtils.isEmpty(empleado.getVacunasEmpleado())){
            throw new Exception("El empleado consta con estado VACUNADO pero no tiene ningún registro de vacunación");
        }
    }
}
