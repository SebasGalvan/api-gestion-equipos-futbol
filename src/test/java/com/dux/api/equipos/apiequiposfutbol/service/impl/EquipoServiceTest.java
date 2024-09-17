package com.dux.api.equipos.apiequiposfutbol.service.impl;

import com.dux.api.equipos.apiequiposfutbol.entity.Equipo;
import com.dux.api.equipos.apiequiposfutbol.repository.IEquiposRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {

    @Mock
    private IEquiposRepository equiposRepository;

    @InjectMocks
    private EquipoService equipoService;

    private static Equipo equipo;

    @BeforeAll()
    public static void setup() {
        equipo = new Equipo(1L,"Equipo Test", "Liga Test","Pais Tests");
    }

    @Test
    public void findAll_sinFiltros_retornaListaCompletaEquipos() {
        when(equiposRepository.findAll()).thenReturn(List.of(equipo));

        List<Equipo> equipos = equipoService.findAll();

        Assert.isTrue(equipos.size() == 1, "No se encontraron equipos");
        Assert.isTrue(equipos.contains(equipo), "El elemento no se encontró en la lista");
        verify(equiposRepository, times(1)).findAll();
    }

    @Test
    public void findAll_sinElemento_retornaListaVacia() {
        when(equiposRepository.findAll()).thenReturn(List.of());

        List<Equipo> equipos = equipoService.findAll();

        Assert.isTrue(equipos.isEmpty(), "Se esperaba una lista vacía");
        verify(equiposRepository, times(1)).findAll();
    }

    @Test
    public void findEquipoById_conIdValido_retornaEquipo() {
        when(equiposRepository.findById(any(Long.class))).thenReturn(Optional.of(equipo));

        Equipo equipoEncontrado = equipoService.findEquipoById(1L);

        Assert.isTrue(equipoEncontrado.getId().equals(equipo.getId()), "No se encontró el equipo");
        verify(equiposRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void findEquipoById_conIdNoValido_lanzaIllegalArgumentException() {
        Long idNoExistente = 999L;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> equipoService.findEquipoById(idNoExistente)
        );
        Assertions.assertEquals("Equipo no encontrado", exception.getMessage());
    }


    @Test
    public void findEquipoByNombre_conNombreValido_retornaEquipo() {
        when(equiposRepository.findEquipoByNombre(any(String.class))).thenReturn(List.of(equipo));
        List<Equipo> equiposEncontrados = equipoService.findEquipoByNombre("Equipo de prueba");
        Assert.isTrue(equiposEncontrados.size() == 1, "No se encontraron equipos");
        verify(equiposRepository, times(1)).findEquipoByNombre(any(String.class));
    }


    @Test
    void updateEquipo_conEquipoConDatosCompletosValidos_retornaEquipoActualizado() {
        Equipo equipoActualizado = new Equipo(1L, "Nuevo Nombre", "Nueva Liga", "Nuevo Pais");
        Equipo equipoEcontardo = new Equipo(1L, "Nombre Anterior", "Liga Anterior", "Pais  Anterior");

        when(equiposRepository.findById(1L)).thenReturn(Optional.of(equipoEcontardo));
        when(equiposRepository.save(any(Equipo.class))).thenReturn(equipoActualizado);

        Equipo actualizado = equipoService.updateEquipo(equipoActualizado, 1L);

        Assertions.assertEquals("Nuevo Nombre", actualizado.getNombre());
        Assertions.assertEquals("Nueva Liga", actualizado.getLiga());
        Assertions.assertEquals("Nuevo Pais", actualizado.getPais());

    }

    @Test
    void updateEquipo_conEquipoNoEncontrado_rlanzaIllegalArgumentException() {
        when(equiposRepository.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> equipoService.updateEquipo(equipo, 1L)
        );
        assertThrows(IllegalArgumentException.class, () -> equipoService.updateEquipo(equipo, 1L));
    }

    @Test
    public void saveEquipo_conEquipoCamposCompletosYValidos_retornaEquipoConId() {
        equipo.setId(1L);
        when(equiposRepository.save(any(Equipo.class))).thenReturn(equipo);
        Equipo equipoGuardado = equipoService.saveEquipo(equipo);
        assertEquals(equipo, equipoGuardado);
    }

    @Test
    public void saveEquipo_conEquipoConNombreEquipoExistente_lanzaConstraintViolationException () {
         Equipo equipoANuevo = Equipo.builder()
                .id(1L)
                .nombre("Equipo ya Existente")
                .liga("Liga")
                .pais("Pais")
                .build();
        when(equiposRepository.save(equipoANuevo)).thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class, () -> equipoService.saveEquipo(equipoANuevo));
    }

    @Test
    public void saveEquipo_conEquipoConNombreNull_lanzaRuntimeException() {
        Equipo equipo = new Equipo(1L,null, "Primera Liga", "España");
        assertThrows(RuntimeException.class, () -> {
            equipoService.saveEquipo(equipo);
        });
    }

    @Test
    public void saveEquipo_conEquipoConLigaNull_lanzaRuntimeException() {
        Equipo equipo = new Equipo(1L,"Equipo", null, "España");
        assertThrows(RuntimeException.class, () -> {
            equipoService.saveEquipo(equipo);
        });
    }

    @Test
    public void saveEquipo_conEquipoConPaisNull_lanzaRuntimeException() {
        Equipo equipo = new Equipo(1L,"Equipo", "Primera Liga", null);
        assertThrows(RuntimeException.class, () -> {
            equipoService.saveEquipo(equipo);
        });
    }

    @Test
    public void saveEquipo_conEquipoConNombreVacio_lanzaRuntimeException() {
        Equipo equipo = new Equipo(1L,"", "Primera Liga", "España");
        assertThrows(RuntimeException.class, () -> {
            equipoService.saveEquipo(equipo);
        });
    }

    @Test
    public void saveEquipo_conEquipoConLigaVacio_lanzaRuntimeException() {
        Equipo equipo = new Equipo(1L,"Equipo", "", "España");
        assertThrows(RuntimeException.class, () -> {
            equipoService.saveEquipo(equipo);
        });
    }

    @Test
    public void saveEquipo_conEquipoConPaisVacio_lanzaRuntimeException() {
        Equipo equipo = new Equipo(1L,"Equipo", "Primera Liga", "");
        assertThrows(RuntimeException.class, () -> {
            equipoService.saveEquipo(equipo);
        });
    }


    @Test
    void deleteEquipoById_equipoNoEncontrado_lanzaInvalidRequestErrorException() {
        Long equipoId = 1L;
        when(equiposRepository.findById(equipoId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            equipoService.deleteEquipoById(equipoId);
        });
        verify(equiposRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteEquipoById_conIdEquipoValido_borraEquipo() {
        Long equipoId = 1L;

        Equipo equipo = new Equipo();
        when(equiposRepository.findById(equipoId)).thenReturn(Optional.of(equipo));

        equipoService.deleteEquipoById(equipoId);

        verify(equiposRepository).deleteById(equipoId);
    }

}