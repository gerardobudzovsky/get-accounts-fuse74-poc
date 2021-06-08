package org.mycompany;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAccountsService {
	
	private String baseAccountsUri = "restlet:http://get-accounts-mock-component-gdbudzovsky-dev.apps.sandbox.x8i5.p1.openshiftapps.com/accounts";
	
	@EndpointInject(uri = "direct:api-get-accounts:start")
	private FluentProducerTemplate producer;
	
	@RequestMapping(value="/accounts", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProvincia(@RequestParam String cbu_cvu, @RequestParam String alias) {
		
		String accountsUri = baseAccountsUri;
		
		if(cbu_cvu != null && !cbu_cvu.isEmpty() && alias != null && !alias.isEmpty())
			accountsUri += "?cbu_cvu=" + cbu_cvu + "&alias=" + alias;
		
		producer.withHeader("accountsUri", accountsUri);
		
		return producer.request(String.class);
	}
	
}
