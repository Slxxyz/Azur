package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailsServiceImplementationTest {

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private UserDetailsServiceImplementation userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsernameWithEmail_Success() {

        String email = "test@example.com";
        CustomerEntity mockCustomer = new CustomerEntity();
        mockCustomer.setUsername("testUser");
        mockCustomer.setUserPassword("encodedPassword");
        when(customerDAO.findByMailAddress(email)).thenReturn(mockCustomer);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
        verify(customerDAO, times(1)).findByMailAddress(email);
    }

    @Test
    void testLoadUserByUsernameWithEmail_NotFound() {
        String email = "unknown@example.com";
        when(customerDAO.findByMailAddress(email)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(email));
        verify(customerDAO, times(1)).findByMailAddress(email);
    }

    @Test
    void testLoadUserByUsernameWithUsername_Success() {

        String username = "testUser";
        CustomerEntity mockCustomer = new CustomerEntity();
        mockCustomer.setUsername(username);
        mockCustomer.setUserPassword("encodedPassword");
        when(customerDAO.findByUsername(username)).thenReturn(mockCustomer);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
        verify(customerDAO, times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsernameWithUsername_NotFound() {
        String username = "unknownUser";
        when(customerDAO.findByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
        verify(customerDAO, times(1)).findByUsername(username);
    }
}