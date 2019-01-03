package guru.springfamework.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CustomerService;

public class CustomerControllerTest {

  @Mock CustomerService customerService;

  @InjectMocks CustomerController customerController;

  MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  }

  @Test
  public void testGetAllCustomers() throws Exception {
    CustomerDTO customer1 = new CustomerDTO();
    customer1.setFirstName("John");
    customer1.setLastName("Doe");
    customer1.setCustomerUrl("some url");

    CustomerDTO customer2 = new CustomerDTO();
    customer2.setFirstName("Filan");
    customer2.setLastName("Fisteku");
    customer2.setCustomerUrl("some url");

    List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

    when(customerService.getAllCustomers()).thenReturn(customers);

    mockMvc
        .perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers", hasSize(2)));
  }

  @Test
  public void testGetCustomerById() throws Exception {
    CustomerDTO customer1 = new CustomerDTO();
    customer1.setFirstName("John");
    customer1.setLastName("Doe");
    customer1.setCustomerUrl("some url");

    when(customerService.getCustomerById(anyLong())).thenReturn(customer1);
    mockMvc
        .perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName", equalTo("John")))
        .andExpect(jsonPath("$.lastName", equalTo("Doe")));
  }
}
