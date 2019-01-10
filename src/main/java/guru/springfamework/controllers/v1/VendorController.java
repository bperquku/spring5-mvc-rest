package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Vendor API")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

  public static final String BASE_URL = "/api/v1/vendors";

  private final VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @ApiOperation(value = "This returns vendor list",notes="Here you can type some notes.")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public VendorListDTO getVendorList() {
    return vendorService.getAllVendors();
  }
  
  @ApiOperation(value = "This returns vendor by given id",notes="Here you can type some notes.")
  @GetMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO getVendorById(@PathVariable Long id) {
    return vendorService.getVendorById(id);
  }

  @ApiOperation(value = "This will create a Vendor",notes="Here you can type some notes.")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
    return vendorService.createNewVendor(vendorDTO);
  }

  @ApiOperation(value = "This will update a Vendor",notes="Here you can type some notes.")
  @PutMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @ApiOperation(value = "This will patch Vendor",notes="Here you can type some notes.")
  @PatchMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @ApiOperation(value = "This will delete a Vendor",notes="Here you can type some notes.")
  @DeleteMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteVendor(@PathVariable Long id) {
    vendorService.deleteVendorById(id);
  }
}
