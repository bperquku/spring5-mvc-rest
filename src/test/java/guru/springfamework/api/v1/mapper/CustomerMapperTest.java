package guru.springfamework.api.v1.mapper;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;

public class CustomerMapperTest {

  private static final Long ID = 1L;
  private static final String FIRSTNAME = "John";
  private static final String LASTNAME = "Doe";
  CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  @Test
  public void testCustomerToCustomerDTO() {
    // given
    Customer customer = new Customer();
    customer.setId(ID);
    customer.setFirstname(FIRSTNAME);
    customer.setLastname(LASTNAME);

    // when
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

    // then
    assertEquals(FIRSTNAME, customerDTO.getFirstname());
    assertEquals(LASTNAME, customerDTO.getLastname());
  }
}
