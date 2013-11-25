package org.works.message.transformer;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.works.batch.domain.Customer;

public class CustomerTransformer extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		// FIXME: jaxb to object items.
		return (String)src;
	}
	
	private void enrichItem(Customer item){
		
	}

}
