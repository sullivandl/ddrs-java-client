package com.digidata.services.rest.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.digidata.services.rest.client.exceptions.DdrsClientException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=false)
public class LinkTemplate extends Link {

	@JsonIgnore
	public Map<String, String> getEmptyTemplateMap() {
		Map<String, String> result = new HashMap<String, String>();
		
		Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
		Matcher matcher = pattern.matcher(this.getHref());

		while(matcher.find()) {
			result.put(matcher.group(1), "");
		}
		
		return result;
	}
	
	public Link bind(String name, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(name, value);
		return bind(map);
	}
	public Link bind(Map<String, String> bindings) {
		Link link = new Link();
		link.setHref(getHref());
		link.setRel(getRel());
		link.setType(getType());
		link.setHreflang(getHreflang());
		link.setBase(getBase());
		
		Map<String, String> allBindings = getEmptyTemplateMap();
		for(String key : bindings.keySet()) {
			try {
				String encoded = URLEncoder.encode(bindings.get(key), "UTF-8");
				encoded = encoded.replace("+", "%20");
				allBindings.put(key, encoded);
			} catch(UnsupportedEncodingException e) {
				throw new DdrsClientException(e.getMessage(), e);	
			}
		}
		
		for(String key : allBindings.keySet()) {
			link.setHref(link.getHref().replace("{" + key + "}", allBindings.get(key)));
		}
		return link;
	}
}
