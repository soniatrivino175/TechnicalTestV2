package com.fonyou.test.mapper.config;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerMapper {
	
	@Bean
    public Mapper beanMapper() {
        return new DozerBeanMapper();
    }
    
	public static <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

		final List<U> dest = new ArrayList<U>();

		for (T element : source) {
			if (element == null) {
				continue;
			}
			dest.add(mapper.map(element, destType));
		}
		List<U> s1 = new ArrayList<U>();
		s1.add(null);
		dest.removeAll(s1);

		return dest;
	}
}
