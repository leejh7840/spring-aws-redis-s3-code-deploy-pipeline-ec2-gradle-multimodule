package com.nexient.orders.data.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTestResourceLoader {

	private DefaultResourceLoader resourceLoader;

	public UnitTestResourceLoader() {
		super();
		resourceLoader = new DefaultResourceLoader();
	}

	public String getString(String location) throws IOException {
		Resource resource = getResource(location);
		String ret = getStringFromInputStream(resource.getInputStream());
		return ret;
	}

	public List<String> getStrings(String locationPattern) throws IOException {
		List<String> strings = new ArrayList<String>();
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(resourceLoader);
		Resource[] resources = pathMatchingResourcePatternResolver.getResources(locationPattern);
		if(resources.length == 0) {
			throw new IOException("There were no resources for pattern " + locationPattern);
		}
		for(Resource resource: resources) {
			String string = getStringFromInputStream(resource.getInputStream());
			strings.add(string);
		}
		return strings;
	}

	public Resource getResource(String location) {
		return resourceLoader.getResource(location);
	}

	public String getAbsolutFilePath(String location) throws IOException {
		Resource resource = getResource(location);
		return resource.getFile().getAbsolutePath();
	}

	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;

		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		br.close();
		return sb.toString();
	}
}
