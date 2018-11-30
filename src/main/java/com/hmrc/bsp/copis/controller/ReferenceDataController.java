package com.hmrc.bsp.copis.controller;


import com.hmrc.bsp.copis.domain.reference.*;
import com.hmrc.bsp.copis.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ReferenceDataController.java: This controller is not indented to be so bigger, which handles all reference data types CRUD operations.
 * All CRUD operations are almost the same except the underlying types which are encoded by a different path parameter under its parent path "/reference-data"
 *
 * The URL Pattern is:
 *
 * 		1) /reference-data/TypeOfReference("get" all reference records for the type)
 * 	    2) /reference-data/TypeOfReference/{code} ("get" the reference record by the code)
 * 	    3) /reference-data/TypeOfReference ("post" for creating an new reference record)
 * 	    4) /reference-data/TypeOfReference ("put" for updating the existing reference record)
 * 	    5) /reference-data/TypeOfReference ("delete" for deleting the existing reference record)
 *
 */

@RestController
@RequestMapping("/reference-data")
@Slf4j
public class ReferenceDataController {
	@Autowired
	private ActionTypeRepository actionTypeRepository;
	@Autowired
	private CategoryOfGoodsRepository categoryOfGoodsRepository;
	@Autowired
	private CountryCodeRepository countryCodeRepository;
	@Autowired
	private CustomsOfficeRepository customsOfficeRepository;
	@Autowired
	private CustomsProcedureRepository customsProcedureRepository;
	@Autowired
	private EUCountryCodeRepository euCountryCodeRepository;
	@Autowired
	private InterventionRepository interventionRepository;
	@Autowired
	private IPRTypeRepository iprTypeRepository;
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private TrafficTypeRepository trafficTypeRepository;
	@Autowired
	private TypeOfPlaceRepository typeOfPlaceRepository;
	@Autowired
	private TypeOfTransportRepository typeOfTransportRepository;
	@Autowired
	private UnitOfMeasureRepository unitOfMeasureRepository;


	@RequestMapping("/action-type")
	public List<ActionType> getAllActionTypes() {
		return this.actionTypeRepository.findAll();
	}
	
	@RequestMapping("/action-type/{code}")
	public ActionType getActionType(@PathVariable @NotBlank String code) {
		return this.getAllActionTypes().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/action-type")
	public ActionType createActionType(@RequestBody @NotNull ActionType reference) {
		String code = reference.getCode();
		ActionType e = this.getActionType(code);
		if(e != null) {
			log.warn(String.format("ActionType with the key: %s already exists.", code));
		} else {
			e = this.actionTypeRepository.save(reference);
			log.info(String.format("new ActionType: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/action-type")
	public ActionType updateActionType(@RequestBody @NotNull ActionType reference) {
		String code = reference.getCode();
		ActionType e = this.getActionType(code);
		if(e == null) {
			log.warn(String.format("ActionType with the code: %s does not exist.", code));
		} else {
			e = this.actionTypeRepository.save(reference);
			log.info(String.format("The ActionType: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/action-type/{code}")
	public void deleteActionType(@PathVariable @NotBlank String code) {
		ActionType e = this.getActionType(code);
		if(e == null) {
			log.warn(String.format("ActionType with the code: %s does not exist.", code));
		} else {
			this.actionTypeRepository.delete(e);
			log.info(String.format("The ActionType with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/category-of-goods")
	public List<CategoryOfGoods> getAllCategoryOfGoods() {
		return this.categoryOfGoodsRepository.findAll();
	}

	@RequestMapping("/category-of-goods/{code}")
	public CategoryOfGoods getCategoryOfGoods(@PathVariable @NotBlank String code) {
		return this.getAllCategoryOfGoods().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/category-of-goods")
	public CategoryOfGoods createCategoryOfGoods(@RequestBody @NotNull CategoryOfGoods reference) {
		String code = reference.getCode();
		CategoryOfGoods e = this.getCategoryOfGoods(code);
		if(e != null) {
			log.warn(String.format("CategoryOfGoods with the key: %s already exists.", code));
		} else {
			e = this.categoryOfGoodsRepository.save(reference);
			log.info(String.format("new CategoryOfGoods: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/category-of-goods")
	public CategoryOfGoods updateCategoryOfGoods(@RequestBody @NotNull CategoryOfGoods reference) {
		String code = reference.getCode();
		CategoryOfGoods e = this.getCategoryOfGoods(code);
		if(e == null) {
			log.warn(String.format("CategoryOfGoods with the code: %s does not exist.", code));
		} else {
			e = this.categoryOfGoodsRepository.save(reference);
			log.info(String.format("The CategoryOfGoods: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/category-of-goods/{code}")
	public void deleteCategoryOfGoods(@PathVariable @NotBlank String code) {
		CategoryOfGoods e = this.getCategoryOfGoods(code);
		if(e == null) {
			log.warn(String.format("CategoryOfGoods with the code: %s does not exist.", code));
		} else {
			this.categoryOfGoodsRepository.delete(e);
			log.info(String.format("The CategoryOfGoods with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/country-code")
	public List<CountryCode> getAllCountryCodes() {
		return this.countryCodeRepository.findAll();
	}

	@RequestMapping("/country-code/{code}")
	public CountryCode getCountryCode(@PathVariable @NotBlank String code) {
		return this.getAllCountryCodes().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/country-code")
	public CountryCode createCountryCode(@RequestBody @NotNull CountryCode reference) {
		String code = reference.getCode();
		CountryCode e = this.getCountryCode(code);
		if(e != null) {
			log.warn(String.format("CountryCode with the key: %s already exists.", code));
		} else {
			e = this.countryCodeRepository.save(reference);
			log.info(String.format("new CountryCode: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/country-code")
	public CountryCode updateCountryCode(@RequestBody @NotNull CountryCode reference) {
		String code = reference.getCode();
		CountryCode e = this.getCountryCode(code);
		if(e == null) {
			log.warn(String.format("CountryCode with the code: %s does not exist.", code));
		} else {
			e = this.countryCodeRepository.save(reference);
			log.info(String.format("The CountryCode: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/country-code/{code}")
	public void deleteCountryCode(@PathVariable @NotBlank String code) {
		CountryCode e = this.getCountryCode(code);
		if(e == null) {
			log.warn(String.format("CountryCode with the code: %s does not exist.", code));
		} else {
			this.countryCodeRepository.delete(e);
			log.info(String.format("The CountryCode with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/eu-country-code")
	public List<EUCountryCode> getAllEUCountryCodes() {
		return this.euCountryCodeRepository.findAll();
	}

	@RequestMapping("/eu-country-code/{code}")
	public EUCountryCode getEUCountryCode(@PathVariable @NotBlank String code) {
		return this.getAllEUCountryCodes().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/eu-country-code")
	public EUCountryCode createEUCountryCode(@RequestBody @NotNull EUCountryCode reference) {
		String code = reference.getCode();
		EUCountryCode e = this.getEUCountryCode(code);
		if(e != null) {
			log.warn(String.format("EUCountryCode with the key: %s already exists.", code));
		} else {
			e = this.euCountryCodeRepository.save(reference);
			log.info(String.format("new EUCountryCode: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/eu-country-code")
	public EUCountryCode updateEUCountryCode(@RequestBody @NotNull EUCountryCode reference) {
		String code = reference.getCode();
		EUCountryCode e = this.getEUCountryCode(code);
		if(e == null) {
			log.warn(String.format("EUCountryCode with the code: %s does not exist.", code));
		} else {
			e = this.euCountryCodeRepository.save(reference);
			log.info(String.format("The EUCountryCode: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/eu-country-code/{code}")
	public void deleteEUCountryCode(@PathVariable @NotBlank String code) {
		EUCountryCode e = this.getEUCountryCode(code);
		if(e == null) {
			log.warn(String.format("EUCountryCode with the code: %s does not exist.", code));
		} else {
			this.euCountryCodeRepository.delete(e);
			log.info(String.format("The EUCountryCode with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/customs-office")
	public List<CustomsOffice> getAllCustomsOffices() {
		return this.customsOfficeRepository.findAll();
	}

	@RequestMapping("/customs-office/{code}")
	public CustomsOffice getCustomsOffice(@PathVariable @NotBlank String code) {
		return this.getAllCustomsOffices().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/customs-office")
	public CustomsOffice createCustomsOffice(@RequestBody @NotNull CustomsOffice reference) {
		String code = reference.getCode();
		CustomsOffice e = this.getCustomsOffice(code);
		if(e != null) {
			log.warn(String.format("CustomsOffice with the key: %s already exists.", code));
		} else {
			e = this.customsOfficeRepository.save(reference);
			log.info(String.format("new CustomsOffice: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/customs-office")
	public CustomsOffice updateCustomsOffice(@RequestBody @NotNull CustomsOffice reference) {
		String code = reference.getCode();
		CustomsOffice e = this.getCustomsOffice(code);
		if(e == null) {
			log.warn(String.format("CustomsOffice with the code: %s does not exist.", code));
		} else {
			e = this.customsOfficeRepository.save(reference);
			log.info(String.format("The CustomsOffice: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/customs-office/{code}")
	public void deleteCustomsOffice(@PathVariable @NotBlank String code) {
		CustomsOffice e = this.getCustomsOffice(code);
		if(e == null) {
			log.warn(String.format("CustomsOffice with the code: %s does not exist.", code));
		} else {
			this.customsOfficeRepository.delete(e);
			log.info(String.format("The CustomsOffice with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/customs-procedure")
	public List<CustomsProcedure> getAllCustomsProcedures() {
		return this.customsProcedureRepository.findAll();
	}

	@RequestMapping("/customs-procedure/{code}")
	public CustomsProcedure getCustomsProcedure(@PathVariable @NotBlank String code) {
		return this.getAllCustomsProcedures().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/customs-procedure")
	public CustomsProcedure createCustomsProcedure(@RequestBody @NotNull CustomsProcedure reference) {
		String code = reference.getCode();
		CustomsProcedure e = this.getCustomsProcedure(code);
		if(e != null) {
			log.warn(String.format("CustomsProcedure with the key: %s already exists.", code));
		} else {
			e = this.customsProcedureRepository.save(reference);
			log.info(String.format("new CustomsProcedure: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/customs-procedure")
	public CustomsProcedure updateCustomsProcedure(@RequestBody @NotNull CustomsProcedure reference) {
		String code = reference.getCode();
		CustomsProcedure e = this.getCustomsProcedure(code);
		if(e == null) {
			log.warn(String.format("CustomsProcedure with the code: %s does not exist.", code));
		} else {
			e = this.customsProcedureRepository.save(reference);
			log.info(String.format("The CustomsProcedure: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/customs-procedure/{code}")
	public void deleteCustomsProcedure(@PathVariable @NotBlank String code) {
		CustomsProcedure e = this.getCustomsProcedure(code);
		if(e == null) {
			log.warn(String.format("CustomsProcedure with the code: %s does not exist.", code));
		} else {
			this.customsProcedureRepository.delete(e);
			log.info(String.format("The CustomsProcedure with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/intervention")
	public List<Intervention> getAllInterventions() {
		return this.interventionRepository.findAll();
	}

	@RequestMapping("/intervention/{code}")
	public Intervention getIntervention(@PathVariable @NotBlank String code) {
		return this.getAllInterventions().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/intervention")
	public Intervention createIntervention(@RequestBody @NotNull Intervention reference) {
		String code = reference.getCode();
		Intervention e = this.getIntervention(code);
		if(e != null) {
			log.warn(String.format("Intervention with the key: %s already exists.", code));
		} else {
			e = this.interventionRepository.save(reference);
			log.info(String.format("new Intervention: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/intervention")
	public Intervention updateIntervention(@RequestBody @NotNull Intervention reference) {
		String code = reference.getCode();
		Intervention e = this.getIntervention(code);
		if(e == null) {
			log.warn(String.format("Intervention with the code: %s does not exist.", code));
		} else {
			e = this.interventionRepository.save(reference);
			log.info(String.format("The Intervention: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/intervention/{code}")
	public void deleteIntervention(@PathVariable @NotBlank String code) {
		Intervention e = this.getIntervention(code);
		if(e == null) {
			log.warn(String.format("Intervention with the code: %s does not exist.", code));
		} else {
			this.interventionRepository.delete(e);
			log.info(String.format("The Intervention with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/ipr-type")
	public List<IPRType> getAllIPRTypes() {
		return this.iprTypeRepository.findAll();
	}

	@RequestMapping("/ipr-type/{code}")
	public IPRType getIPRType(@PathVariable @NotBlank String code) {
		return this.getAllIPRTypes().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/ipr-type")
	public IPRType createIPRType(@RequestBody @NotNull IPRType reference) {
		String code = reference.getCode();
		IPRType e = this.getIPRType(code);
		if(e != null) {
			log.warn(String.format("IPRType with the key: %s already exists.", code));
		} else {
			e = this.iprTypeRepository.save(reference);
			log.info(String.format("new IPRType: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/ipr-type")
	public IPRType updateIPRType(@RequestBody @NotNull IPRType reference) {
		String code = reference.getCode();
		IPRType e = this.getIPRType(code);
		if(e == null) {
			log.warn(String.format("IPRType with the code: %s does not exist.", code));
		} else {
			e = this.iprTypeRepository.save(reference);
			log.info(String.format("The IPRType: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/ipr-type/{code}")
	public void deleteIPRType(@PathVariable @NotBlank String code) {
		IPRType e = this.getIPRType(code);
		if(e == null) {
			log.warn(String.format("IPRType with the code: %s does not exist.", code));
		} else {
			this.iprTypeRepository.delete(e);
			log.info(String.format("The IPRType with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/traffic-type")
	public List<TrafficType> getAllTrafficTypes() {
		return this.trafficTypeRepository.findAll();
	}

	@RequestMapping("/traffic-type/{code}")
	public TrafficType getTrafficType(@PathVariable @NotBlank String code) {
		return this.getAllTrafficTypes().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/traffic-type")
	public TrafficType createTrafficType(@RequestBody @NotNull TrafficType reference) {
		String code = reference.getCode();
		TrafficType e = this.getTrafficType(code);
		if(e != null) {
			log.warn(String.format("TrafficType with the key: %s already exists.", code));
		} else {
			e = this.trafficTypeRepository.save(reference);
			log.info(String.format("new TrafficType: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/traffic-type")
	public TrafficType updateTrafficType(@RequestBody @NotNull TrafficType reference) {
		String code = reference.getCode();
		TrafficType e = this.getTrafficType(code);
		if(e == null) {
			log.warn(String.format("TrafficType with the code: %s does not exist.", code));
		} else {
			e = this.trafficTypeRepository.save(reference);
			log.info(String.format("The TrafficType: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/traffic-type/{code}")
	public void deleteTrafficType(@PathVariable @NotBlank String code) {
		TrafficType e = this.getTrafficType(code);
		if(e == null) {
			log.warn(String.format("TrafficType with the code: %s does not exist.", code));
		} else {
			this.trafficTypeRepository.delete(e);
			log.info(String.format("The TrafficType with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/type-of-place")
	public List<TypeOfPlace> getAllTypeOfPlaces() {
		return this.typeOfPlaceRepository.findAll();
	}

	@RequestMapping("/type-of-place/{code}")
	public TypeOfPlace getTypeOfPlace(@PathVariable @NotBlank String code) {
		return this.getAllTypeOfPlaces().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/type-of-place")
	public TypeOfPlace createTypeOfPlace(@RequestBody @NotNull TypeOfPlace reference) {
		String code = reference.getCode();
		TypeOfPlace e = this.getTypeOfPlace(code);
		if(e != null) {
			log.warn(String.format("TypeOfPlace with the key: %s already exists.", code));
		} else {
			e = this.typeOfPlaceRepository.save(reference);
			log.info(String.format("new TypeOfPlace: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/type-of-place")
	public TypeOfPlace updateTypeOfPlace(@RequestBody @NotNull TypeOfPlace reference) {
		String code = reference.getCode();
		TypeOfPlace e = this.getTypeOfPlace(code);
		if(e == null) {
			log.warn(String.format("TypeOfPlace with the code: %s does not exist.", code));
		} else {
			e = this.typeOfPlaceRepository.save(reference);
			log.info(String.format("The TypeOfPlace: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/type-of-place/{code}")
	public void deleteTypeOfPlace(@PathVariable @NotBlank String code) {
		TypeOfPlace e = this.getTypeOfPlace(code);
		if(e == null) {
			log.warn(String.format("TypeOfPlace with the code: %s does not exist.", code));
		} else {
			this.typeOfPlaceRepository.delete(e);
			log.info(String.format("The TypeOfPlace with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/type-of-transport")
	public List<TypeOfTransport> getAllTypeOfTransports() {
		return this.typeOfTransportRepository.findAll();
	}

	@RequestMapping("/type-of-transport/{code}")
	public TypeOfTransport getTypeOfTransport(@PathVariable @NotBlank String code) {
		return this.getAllTypeOfTransports().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/type-of-transport")
	public TypeOfTransport createTypeOfTransport(@RequestBody @NotNull TypeOfTransport reference) {
		String code = reference.getCode();
		TypeOfTransport e = this.getTypeOfTransport(code);
		if(e != null) {
			log.warn(String.format("TypeOfTransport with the key: %s already exists.", code));
		} else {
			e = this.typeOfTransportRepository.save(reference);
			log.info(String.format("new TypeOfTransport: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/type-of-transport")
	public TypeOfTransport updateTypeOfTransport(@RequestBody @NotNull TypeOfTransport reference) {
		String code = reference.getCode();
		TypeOfTransport e = this.getTypeOfTransport(code);
		if(e == null) {
			log.warn(String.format("TypeOfTransport with the code: %s does not exist.", code));
		} else {
			e = this.typeOfTransportRepository.save(reference);
			log.info(String.format("The TypeOfTransport: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/type-of-transport/{code}")
	public void deleteTypeOfTransport(@PathVariable @NotBlank String code) {
		TypeOfTransport e = this.getTypeOfTransport(code);
		if(e == null) {
			log.warn(String.format("TypeOfTransport with the code: %s does not exist.", code));
		} else {
			this.typeOfTransportRepository.delete(e);
			log.info(String.format("The TypeOfTransport with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/site")
	public List<Site> getAllSites() {
		return this.siteRepository.findAll();
	}

	@RequestMapping("/site/{code}")
	public Site getSite(@PathVariable @NotBlank String code) {
		return this.getAllSites().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/site")
	public Site createSite(@RequestBody @NotNull Site reference) {
		String code = reference.getCode();
		Site e = this.getSite(code);
		if(e != null) {
			log.warn(String.format("Site with the key: %s already exists.", code));
		} else {
			e = this.siteRepository.save(reference);
			log.info(String.format("new Site: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/site")
	public Site updateSite(@RequestBody @NotNull Site reference) {
		String code = reference.getCode();
		Site e = this.getSite(code);
		if(e == null) {
			log.warn(String.format("Site with the code: %s does not exist.", code));
		} else {
			e = this.siteRepository.save(reference);
			log.info(String.format("The Site: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/site/{code}")
	public void deleteSite(@PathVariable @NotBlank String code) {
		Site e = this.getSite(code);
		if(e == null) {
			log.warn(String.format("Site with the code: %s does not exist.", code));
		} else {
			this.siteRepository.delete(e);
			log.info(String.format("The Site with the code %s has been deleted successfully.", code));
		}
	}

	@RequestMapping("/unit-of-measure")
	public List<UnitOfMeasure> getAllUnitOfMeasures() {
		return this.unitOfMeasureRepository.findAll();
	}

	@RequestMapping("/unit-of-measure/{code}")
	public UnitOfMeasure getUnitOfMeasure(@PathVariable @NotBlank String code) {
		return this.getAllUnitOfMeasures().stream()
				.filter(e -> e.getCode().equalsIgnoreCase(code.trim()))
				.findAny().orElse(null);
	}

	@PostMapping("/unit-of-measure")
	public UnitOfMeasure createUnitOfMeasure(@RequestBody @NotNull UnitOfMeasure reference) {
		String code = reference.getCode();
		UnitOfMeasure e = this.getUnitOfMeasure(code);
		if(e != null) {
			log.warn(String.format("UnitOfMeasure with the key: %s already exists.", code));
		} else {
			e = this.unitOfMeasureRepository.save(reference);
			log.info(String.format("new UnitOfMeasure: %s has been created successfully.", reference.toString()));
		}
		return e;
	}

	@PutMapping(value = "/unit-of-measure")
	public UnitOfMeasure updateUnitOfMeasure(@RequestBody @NotNull UnitOfMeasure reference) {
		String code = reference.getCode();
		UnitOfMeasure e = this.getUnitOfMeasure(code);
		if(e == null) {
			log.warn(String.format("UnitOfMeasure with the code: %s does not exist.", code));
		} else {
			e = this.unitOfMeasureRepository.save(reference);
			log.info(String.format("The UnitOfMeasure: %s has been updated successfully.", reference.toString()));
		}
		return e;
	}

	@DeleteMapping(value = "/unit-of-measure/{code}")
	public void deleteUnitOfMeasure(@PathVariable @NotBlank String code) {
		UnitOfMeasure e = this.getUnitOfMeasure(code);
		if(e == null) {
			log.warn(String.format("UnitOfMeasure with the code: %s does not exist.", code));
		} else {
			this.unitOfMeasureRepository.delete(e);
			log.info(String.format("The UnitOfMeasure with the code %s has been deleted successfully.", code));
		}
	}


}
