package guru.springfamework.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

public class CustomerServiceTest {

  private static final Long ID = 1L;

  private static final String FIRSTNAME = "John";
  private static final String LASTNAME = "Doe";

  private static final String CUSTOMERURL = "some url";

  CustomerService customerService;

  @Mock CustomerRepository customerRepository;

  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
    customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
  }

  @Test
  public void testGetAllCustomers() {
    // given
    List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

    when(customerRepository.findAll()).thenReturn(customers);
    // when
    List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
    // then
    assertEquals(3, customerDTOS.size());
  }

  @Test
  public void testGetCustomerById() {
	Customer customer = new Customer();
	customer.setId(ID);
	customer.setFirstName(FIRSTNAME);
	customer.setLastName(LASTNAME);
	
	when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
	
	 // when
    CustomerDTO customerDTO = customerService.getCustomerById(ID);

    // then
    assertEquals(FIRSTNAME, customerDTO.getFirstName());
    assertEquals(LASTNAME, customerDTO.getLastName());
  }
}
