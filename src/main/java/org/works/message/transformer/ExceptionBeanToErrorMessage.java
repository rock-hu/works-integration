package org.works.message.transformer;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;
import org.works.message.bean.ErrorMessage;
import org.works.message.bean.ExceptionBean;

public class ExceptionBeanToErrorMessage extends AbstractTransformer {

	public ExceptionBeanToErrorMessage() {
		registerSourceType(DataTypeFactory.create(ExceptionBean.class));
	}

	@Override
	public Object doTransform(Object src, String encoding)
			throws TransformerException {
		try {
			return new ErrorMessage((ExceptionBean) src);
		} catch (InstantiationException e) {
			throw new TransformerException(this, e);
		}
	}

}
