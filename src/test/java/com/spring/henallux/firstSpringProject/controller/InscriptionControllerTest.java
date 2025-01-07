package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.constants.Constants;
import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InscriptionControllerTest {

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private InscriptionController inscriptionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inscriptionController).build();
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/inscription"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("title"))
                .andExpect(model().attributeExists("showHeader"))
                .andExpect(model().attributeExists("showFooter"))
                .andExpect(model().attributeExists("customer"))
                .andExpect(view().name("integrated:inscription"));
    }

    @Test
    void testGetFormData_WithErrors() throws Exception {
        CustomerEntity mockCustomer = new CustomerEntity();
        mockCustomer.setMailAddress("existing@example.com");
        mockCustomer.setTelNumber("123456789");
        mockCustomer.setUsername("existingUser");
        mockCustomer.setUserPassword("password");

        when(customerDAO.mailAddressExists(mockCustomer.getMailAddress())).thenReturn(true);
        when(customerDAO.telephoneExists(mockCustomer.getTelNumber())).thenReturn(true);
        when(customerDAO.userNameExists(mockCustomer.getUsername())).thenReturn(true);

        mockMvc.perform(post("/inscription/sendInscription")
                        .flashAttr(Constants.CURRENT_USER, mockCustomer))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorEmail"))
                .andExpect(model().attributeExists("errorTelephone"))
                .andExpect(model().attributeExists("errorUsername"))
                .andExpect(model().attributeExists("customer"))
                .andExpect(view().name("integrated:inscription"));
    }

    @Test
    void testGetFormData_Success() throws Exception {
        CustomerEntity mockCustomer = new CustomerEntity();
        mockCustomer.setMailAddress("new@example.com");
        mockCustomer.setTelNumber("987654321");
        mockCustomer.setUsername("newUser");
        mockCustomer.setUserPassword("password");

        when(customerDAO.mailAddressExists(mockCustomer.getMailAddress())).thenReturn(false);
        when(customerDAO.telephoneExists(mockCustomer.getTelNumber())).thenReturn(false);
        when(customerDAO.userNameExists(mockCustomer.getUsername())).thenReturn(false);

        mockMvc.perform(post("/inscription/sendInscription")
                        .flashAttr(Constants.CURRENT_USER, mockCustomer))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/azur"));

        verify(customerDAO, times(1)).saveCustomer(mockCustomer);
    }
}
