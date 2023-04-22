package com.springboot.social_media.advanced_concepts;

import java.util.Arrays;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springboot.social_media.advanced_concepts.models.DynamicFilteringModel;
import com.springboot.social_media.advanced_concepts.models.PersonV1;
import com.springboot.social_media.advanced_concepts.models.PersonV2;
import com.springboot.social_media.advanced_concepts.models.StaticFilteringModel;

@RestController
public class AdvancedConceptsController {

	MessageSource messageSource;

	private AdvancedConceptsController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	// ---------------- Internalization ----------------------

	@GetMapping("/hello-world-i18n")
	public String getHelloWorldI18n() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default message", locale);
	}

	// ---------------- Versioning ----------------------

	/** 1. UI mapping **/

	// http://localhost:8080/v1/person
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1(" Goerge Kristansa");
	}

	// http://localhost:8080/v2/person
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2("Goerge", "Kristansa");
	}

	// ** 2. Request parameter versioning **/

	// http://localhost:8080/person?version=1
	@GetMapping(path = "/person", params = "version=1")
	public Object getPersonWithRequestParamV1() {
		return new PersonV1(" Goerge Kristansa rp");
	}

	// http://localhost:8080/person?version=2
	@GetMapping(path = "/person", params = "version=2")
	public Object getPersonWithRequestParamV2() {
		return new PersonV2("Goerge", "Kristansa rp");
	}

	// ** 3. Header versioning

	// http://localhost:8080/person and add header X-API-VERSION=1
	@GetMapping(path = "/person", headers = "X-API-VERSION=1")
	public Object getPersonWithHeaderV1() {
		return new PersonV1(" Goerge Kristansa h");
	}

	// http://localhost:8080/person and add header X-API-VERSION=2
	@GetMapping(path = "/person", headers = "X-API-VERSION=2")
	public Object getPersonWithHeaderV2() {
		return new PersonV2("Goerge h", "Kristansa h");
	}

	// 4. Media type versioning

	// http://localhost:8080/person and add header Accept =
	// application/getPersonForV1+json
	@GetMapping(path = "/person", produces = "application/getPersonForV1+json")
	public Object getPersonWithAcceptV1() {
		return new PersonV1(" Goerge Kristansa A");
	}

	// http://localhost:8080 add header Accept = application/getPersonForV2+json
	@GetMapping(path = "/person", produces = "application/getPersonForV2+json")
	public Object getPersonWithAcceptV2() {
		return new PersonV2("Goerge A", "Kristansa A");
	}

	// ---------------- Filtering ----------------------

	// ** 1. Static filtering **/
	@GetMapping(path = "/filteOutField3")
	public StaticFilteringModel getFilteringIgnoreFieldsStatic() {
		return new StaticFilteringModel("Goerge h", "Kristansa h", " Eleane");
	}

	// ** 2. Static filtering for list **/
	@GetMapping(path = "/filteOutFieldsInList")
	public java.util.List<StaticFilteringModel> getFilteringIgnoreFieldsForListStatic() {
		StaticFilteringModel[] filteringModelArray = { new StaticFilteringModel("Goerge h", "Kristansa h", "Eleane"),
				new StaticFilteringModel("Jerry", "Sienfield", "gilmore"),
				new StaticFilteringModel("Kramer", "cereal", "fruit") };

		return Arrays.asList(filteringModelArray);
	}

	// ** 3. Dynamic filtering **/
	@GetMapping(path = "/filteOutFieldsDynamically")
	public MappingJacksonValue getFilteringIgnoreFieldsDynamic() {
		DynamicFilteringModel dynamicFilteringModel= new DynamicFilteringModel("Goerge h", "Kristansa h", " Eleane");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicFilteringModel);
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilteringOnDynamicFilteringModel", simpleBeanPropertyFilter);
		mappingJacksonValue.setFilters(filterProvider);
	
		return mappingJacksonValue;
	}
	
	// ** 4. Dynamic filtering for list**/
		@GetMapping(path = "/filteOutFieldsInListDynamically")
		public MappingJacksonValue getFilteringIgnoreFieldsForListDynamic() {
			DynamicFilteringModel[] dynamicFilteringModelList = { new DynamicFilteringModel("Goerge h", "Kristansa h", "Eleane"),
					new DynamicFilteringModel("Jerry", "Sienfield", "gilmore"),
					new DynamicFilteringModel("Kramer", "cereal", "fruit") };
			
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicFilteringModelList);
			SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilteringOnDynamicFilteringModel", simpleBeanPropertyFilter);
			mappingJacksonValue.setFilters(filterProvider);
		
			return mappingJacksonValue;
		}

}
