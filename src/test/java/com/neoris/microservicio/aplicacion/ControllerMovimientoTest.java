/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoris.microservicio.aplicacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.microservicio.dominio.entity.Movimiento;
import com.neoris.microservicio.dominio.service.impl.MovimientoServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 *
 * @author Santiago Betancur
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ControllerMovimientoTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MovimientoServiceImpl movimientoServiceImpl;

    @Test
    public void listar_todos_los_movimientos() throws Exception {
        // arrange

        List<Movimiento> listM = new ArrayList<>();
        Movimiento mdto1 = new Movimiento();
        mdto1.setTipoMovimiento("RETIRO");
        mdto1.setValor(500.0);
        mdto1.setSaldo(5000.0);

        Movimiento mdto2 = new Movimiento();
        mdto2.setTipoMovimiento("RETIRO");
        mdto2.setValor(800.0);
        mdto2.setSaldo(8000.0);

        listM.add(mdto1);
        listM.add(mdto2);

        // act 
        Mockito.when(movimientoServiceImpl.findAll()).thenReturn(listM);

        //assert
        mvc.perform(MockMvcRequestBuilders.get("/api/movimientos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].tipoMovimiento", is("RETIRO"))).andExpect(jsonPath("$[0].valor", is(500.0)))
                .andExpect(jsonPath("$[1].tipoMovimiento", is("RETIRO")))
                .andExpect(jsonPath("$[1].saldo", is(8000.0)));
    }
}
