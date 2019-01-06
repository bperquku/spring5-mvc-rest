package guru.springfamework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

  private CategoryRepository categoryRepository;
  private CustomerRepository customerRepository;
  private VendorRepository vendorRepository;

  public Bootstrap(
      CategoryRepository categoryRepository,
      CustomerRepository customerRepository,
      VendorRepository vendorRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    loadCategories();
    loadCustomers();
    loadVendors();
  }

  private void loadVendors() {
    Vendor vendor1 = new Vendor();
    vendor1.setName("Vendor 1");
    vendorRepository.save(vendor1);

    Vendor vendor2 = new Vendor();
    vendor2.setName("Vendor 2");
    vendorRepository.save(vendor2);
  }

  private void loadCustomers() {
    Customer customer1 = new Customer();
    customer1.setId(1L);
    customer1.setFirstname("John");
    customer1.setLastname("Doe");

    Customer customer2 = new Customer();
    customer2.setId(2L);
    customer2.setFirstname("Filan");
    customer2.setLastname("Fisteku");

    customerRepository.save(customer1);
    customerRepository.save(customer2);

    System.out.println("Customer Data Loaded = " + customerRepository.count());
  }

  private void loadCategories() {
    Category fruits = new Category();
    fruits.setName("Fruits");

    Category dried = new Category();
    dried.setName("Dried");

    Category fresh = new Category();
    fresh.setName("Fresh");

    Category exotic = new Category();
    exotic.setName("Exotic");

    Category nuts = new Category();
    nuts.setName("Nuts");

    categoryRepository.save(fruits);
    categoryRepository.save(dried);
    categoryRepository.save(fresh);
    categoryRepository.save(exotic);
    categoryRepository.save(nuts);

    System.out.println("Category Data Loaded = " + categoryRepository.count());
  }
}
