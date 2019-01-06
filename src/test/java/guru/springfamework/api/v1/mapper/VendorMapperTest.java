package guru.springfamework.api.v1.mapper;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;

public class VendorMapperTest {

  public static final String NAME = "some name";
  VendorMapper vendorMapper = VendorMapper.INSTANCE;
  
  @Test
  public void testVendorToVendorDTO() {
	//given
	Vendor vendor = new Vendor();
	vendor.setName(NAME);
	
	//when
	VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
	
	//then
	assertEquals(vendor.getName(), vendorDTO.getName());
  }

  @Test
  public void testVendorDtoToVendor() {
	//given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(NAME);
		
		//when
		Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
		
		//then
		assertEquals(vendorDTO.getName(), vendor.getName());
  }
}
