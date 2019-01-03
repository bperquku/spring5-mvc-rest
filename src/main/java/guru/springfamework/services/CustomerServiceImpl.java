package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerMapper customerMapper;
  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
    this.customerMapper = customerMapper;
    this.customerRepository = customerRepository;
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository
        .findAll()
        .stream()
        .map(
            customer -> {
              CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
              customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
              return customerDTO;
            })
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {
    return customerRepository
        .findById(id)
        .map(customer -> {
          CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
          customerDTO.setCustomerUrl("/api/v1/customers/"+customer.getId());
          return customerDTO;
        })
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
	Customer customer  = customerMapper.customerDtoToCustomer(customerDTO);
	Customer savedCustomer = customerRepository.save(customer);
	
	CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
	returnDTO.setCustomerUrl("/api/v1/customer/"+savedCustomer.getId());
	
    return returnDTO;
  }
}
