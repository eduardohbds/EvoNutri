package com.evonutri.backend.backend.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import br.com.evonutri.EvoNutri.Model.Cliente;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a valid Cliente object using the builder
        cliente = Cliente.builder()
                .name("Jane Doe")
                .endereco("123 Main St")
                .phone("+123456789")
                .age("30")
                .weight(65.5)
                .email("jane.doe@example.com")
                .cpf("123.456.789-00")
                .build();
    }

    @Test
    void testGetters() {
        assertEquals("Jane Doe", cliente.getName());
        assertEquals("123 Main St", cliente.getEndereco());
        assertEquals("+123456789", cliente.getPhone());
        assertEquals("30", cliente.getAge());
        assertEquals(65.5, cliente.getWeight());
        assertEquals("jane.doe@example.com", cliente.getEmail());
        assertEquals("123.456.789-00", cliente.getCpf());
    }

    @Test
    void testSetters() {
        cliente.setName("John Doe");
        cliente.setEndereco("456 Elm St");
        cliente.setPhone("1234567890");
        cliente.setAge("40");
        cliente.setWeight(70.0);
        cliente.setEmail("johndoe@example.com");
        cliente.setCpf("09876543210");

        assertEquals("John Doe", cliente.getName());
        assertEquals("456 Elm St", cliente.getEndereco());
        assertEquals("1234567890", cliente.getPhone());
        assertEquals("40", cliente.getAge());
        assertEquals(70.0, cliente.getWeight());
        assertEquals("johndoe@example.com", cliente.getEmail());
        assertEquals("09876543210", cliente.getCpf());
    }

    @Test
    void testClienteConstructor() {
        assertNotNull(cliente);
        assertEquals("Jane Doe", cliente.getName());
        assertEquals("123 Main St", cliente.getEndereco());
        assertEquals("+123456789", cliente.getPhone());
        assertEquals("30", cliente.getAge());
        assertEquals(65.5, cliente.getWeight());
        assertEquals("jane.doe@example.com", cliente.getEmail());
        assertEquals("123.456.789-00", cliente.getCpf());
    }

    @Test
    public void testCpfValidation() {
        assertTrue(cliente.getCpf().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$"));
        Cliente invalidCpfCliente = Cliente.builder()
                .name("John Doe")
                .endereco("456 Main St")
                .phone("+123456789")
                .age("30")
                .weight(80)
                .email("john.doe@example.com")
                .cpf("invalid_cpf")
                .build();
        assertFalse(invalidCpfCliente.getCpf().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$"),
                "Cpf é um conjunto de letras");
        invalidCpfCliente.setCpf("123132");
        assertFalse(invalidCpfCliente.getCpf().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$"),
                "Cpf é menor que 6 digitos");
        invalidCpfCliente.setCpf("09876543210123456");
        assertFalse(invalidCpfCliente.getCpf().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$"),
                "Cpf é maior que 15 digitos");

    }

    @Test
    public void testPhoneValidation() {
        // Invalid phone number should fail validation
        Cliente invalidPhoneCliente = Cliente.builder()
                .name("John Doe")
                .endereco("456 Main St")
                .phone("invalid_phone")
                .age("25")
                .weight(80)
                .email("john.doe@example.com")
                .cpf("987.654.321-00")
                .build();

        assertFalse(invalidPhoneCliente.getPhone().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$"),
                "Phone é um conjunto de letras");
        invalidPhoneCliente.setPhone("123132");
        assertFalse(invalidPhoneCliente.getPhone().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$"),
                "Phone é menor que 6 digitos");
        invalidPhoneCliente.setPhone("09876543210123456");
        assertFalse(invalidPhoneCliente.getPhone().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$"),
                "Phone é maior que 15 digitos");
    }

    @Test
    public void testAgeValidation() {
        // Age within valid range
        assertTrue(cliente.getAge().matches("^(1[01][0-9]|120|[1-9]?[0-9])$"));

        // Age outside valid range should fail
        Cliente invalidAgeCliente = Cliente.builder()
                .name("John Doe")
                .endereco("456 Main St")
                .phone("+123456789")
                .age("invalid_age")
                .weight(80)
                .email("john.doe@example.com")
                .cpf("987.654.321-00")
                .build();

        assertFalse(invalidAgeCliente.getAge().matches("^(1[01][0-9]|120|[1-9]?[0-9])$"),
                "Age é um conjunto de letras");
        invalidAgeCliente.setAge("130");
        assertFalse(invalidAgeCliente.getAge().matches("^(1[01][0-9]|120|[1-9]?[0-9])$"),
                "Age é maior que 120");
        invalidAgeCliente.setAge("-120");
        assertFalse(invalidAgeCliente.getAge().matches("^(1[01][0-9]|120|[1-9]?[0-9])$"),
                "Age é negativo");
    }

    @Test
    public void testEmailValidation() {
        // Check valid email
        assertTrue(cliente.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));

        // Invalid email should fail validation
        Cliente invalidEmailCliente = Cliente.builder()
                .name("John Doe")
                .endereco("456 Main St")
                .phone("+123456789")
                .age("30")
                .weight(80)
                .email("invalid_email")
                .cpf("987.654.321-00")
                .build();

        assertFalse(invalidEmailCliente.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"),
                "Email é um conjunto de letras");
        invalidEmailCliente.setEmail("testeteste.com");
        assertFalse(invalidEmailCliente.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"),
                "Email sem o arroba \\@");
        invalidEmailCliente.setEmail(".teste@teste");
        assertFalse(invalidEmailCliente.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"),
                "Email com ponto no começo");
    }

}
