package com.digidata.services.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.digidata.services.rest.client.exceptions.DdrsClientException;
import com.digidata.services.rest.client.exceptions.RelNotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Associations {

	private List<Link> links;
	private List<LinkTemplate> linkTemplates;
	
	public Associations() {
		links = new ArrayList<Link>();
		linkTemplates = new ArrayList<LinkTemplate>();
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	@JsonProperty(value="link-templates")
	public List<LinkTemplate> getLinkTemplates() {
		return linkTemplates;
	}
	
	@JsonProperty(value="link-templates")
	public void setLinkTemplates(List<LinkTemplate> linkTemplates) {
		this.linkTemplates = linkTemplates;
	}
	
	public Link findLink(String rel) {
		for(Link link : links) {
			if(link.getRel().equals(rel))
				return link;
		}
		throw new RelNotFoundException(rel);
	}
	
	public Link findLinkOrNull(String rel) {
		try {
			return findLink(rel);
		} catch(DdrsClientException e) {
			return null;
		}
	}
	
	public LinkTemplate findLinkTemplate(String rel) {
		for(LinkTemplate link : linkTemplates) {
			if(link.getRel().equals(rel))
				return link;
		}
		throw new RelNotFoundException(rel);
	}
}
