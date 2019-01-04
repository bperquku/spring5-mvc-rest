package guru.springfamework.controllers.v1;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import guru.springfamework.services.CustomerService;

public class CustomerControllerTest extends AbstractRestControllerTest {

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
    customer1.setFirstname("John");
    customer1.setLastname("Doe");
    customer1.setCustomerUrl("some url");

    CustomerDTO customer2 = new CustomerDTO();
    customer2.setFirstname("Filan");
    customer2.setLastname("Fisteku");
    customer2.setCustomerUrl("some url");

    List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

    when(customerService.getAllCustomers()).thenReturn(customers);

    mockMvc
        .perform(get(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers", hasSize(2)));
  }

  @Test
  public void testGetCustomerById() throws Exception {
    CustomerDTO customer1 = new CustomerDTO();
    customer1.setFirstname("John");
    customer1.setLastname("Doe");
    customer1.setCustomerUrl("some url");

    when(customerService.getCustomerById(anyLong())).thenReturn(customer1);
    mockMvc
        .perform(get(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstname", equalTo("John")))
        .andExpect(jsonPath("$.lastname", equalTo("Doe")));
  }

  @Test
  public void testCreateNewCustomer() throws Exception {
    CustomerDTO customer = new CustomerDTO();
    customer.setFirstname("John");
    customer.setLastname("Doe");

    CustomerDTO returnDTO = new CustomerDTO();
    returnDTO.setFirstname("John");
    returnDTO.setLastname("Doe");
    returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

    when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

    mockMvc
        .perform(
            post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstname", equalTo("John")))
        .andExpect(jsonPath("$.lastname", equalTo("Doe")))
        .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
  }

  @Test
  public void testUpdateCustomer() throws Exception {
    CustomerDTO customer = new CustomerDTO();
    customer.setFirstname("John");
    customer.setLastname("Doe");

    CustomerDTO returnDTO = new CustomerDTO();
    returnDTO.setFirstname("John");
    returnDTO.setLastname("Doe");
    returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

    when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class)))
        .thenReturn(returnDTO);

    mockMvc
        .perform(
            put(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstname", equalTo("John")))
        .andExpect(jsonPath("$.lastname", equalTo("Doe")))
        .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
  }

  @Test
  public void patchCustomer() throws Exception {
    CustomerDTO customer = new CustomerDTO();
    customer.setFirstname("Fred");

    CustomerDTO returnDTO = new CustomerDTO();
    returnDTO.setFirstname(customer.getFirstname());
    returnDTO.setLastname("Flinstone");
    returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

    when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

    mockMvc
        .perform(
            patch(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstname", equalTo("Fred")))
        .andExpect(jsonPath("$.lastname", equalTo("Flinstone")))
        .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
  }

  @Test
  public void testDeleteCustomer() throws Exception {
    mockMvc
        .perform(delete(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    verify(customerService, timeout(1)).deleteCustomerById(anyLong());
  }
}
