package com.jpa1prueba.jpademo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;
import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.mappers.HorarioMapper;

import com.jpa1prueba.jpademo.repositories.HorariosRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HorariosService {

    @Autowired
    private HorariosRepository horariosRepository;
    @Autowired
    UsuarioService usuarioService = new UsuarioService();

    public List<HorarioDetailDTO> getHorariosByIdUsuario(Long idUsuario) {
        Usuarios usuario = usuarioService.getUsuarioById(idUsuario);
        return horariosRepository.findByUsuarioAsociado(usuario)
            .stream()
            .map(HorarioMapper::toHorarioDetailDTO)
            .collect(Collectors.toList());
    }

    public List<HorarioDetailDTO> getHorariosByIdUsuarioAndMonth(Long idUsuario, int month) {
        Usuarios usuario = usuarioService.getUsuarioById(idUsuario);
        return horariosRepository.findByUsuarioAsociadoAndMonth(usuario, month)
            .stream()
            .map(HorarioMapper::toHorarioDetailDTO)
            .collect(Collectors.toList());
    }

    public List<HorarioDetailDTO> getAllHorarios() {
        return horariosRepository.findAll()
                .stream()
                .map(HorarioMapper::toHorarioDetailDTO)
                .toList();
    }

    public Horarios createHorario(Horarios horario) {
        return horariosRepository.save(horario);
    }

    public void deleteHorario(Long idHorario) {
        horariosRepository.deleteById(idHorario);
    }
}
