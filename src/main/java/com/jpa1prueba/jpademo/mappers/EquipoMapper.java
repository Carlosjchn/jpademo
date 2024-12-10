package com.jpa1prueba.jpademo.mappers;

import java.util.stream.Collectors;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import com.jpa1prueba.jpademo.dto.equipo.EquipoDetailDTO;
import com.jpa1prueba.jpademo.entities.Equipos;

public class EquipoMapper {

    public static EquipoBasicDTO toEquipoBasicDTO(Equipos equipo) {
        return EquipoBasicDTO.builder()
                .nombre(equipo.getNombre())
                .tipo(equipo.getTipo())
                .build();
    }

    public static EquipoDetailDTO toEquipoDetailDTO(Equipos equipo) {
        return EquipoDetailDTO.builder()
                .idEquipo(equipo.getIdEquipo())
                .tipo(equipo.getTipo())
                .nombre(equipo.getNombre())
                .horaInicio(equipo.getHoraInicioAct())
                .horaFin(equipo.getHoraFinAct())
                .Usuarios(equipo.getUsuarios().stream()
                        .map(UserMapper::toUserBasicDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}