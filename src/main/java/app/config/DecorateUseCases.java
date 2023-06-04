package app.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import port.UseCase;

public class DecorateUseCases implements BeanPostProcessor {
	
	@Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof UseCase)) {
            // We are only interested in OurService beans
            return bean;
        }

        if (bean instanceof Tx) {
            // The bean has already been decorated
            return bean;
        }

        return new Tx((UseCase) bean);
    }
}
