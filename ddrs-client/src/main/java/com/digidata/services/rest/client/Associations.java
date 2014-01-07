package com.digidata.services.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.digidata.services.rest.client.exceptions.RelNotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing Associations Aspects.
 * @author dan.sullivan
 *
 */
public class Associations {

	private List<Link> links;
	private List<LinkTemplate> linkTemplates;
	
	/**
	 * Construct a new, empty associations.
	 */
	public Associations() {
		links = new ArrayList<Link>();
		linkTemplates = new ArrayList<LinkTemplate>();
	}
	
	/**
	 * Gets the set of links present in this associations.
	 * @return the links.
	 */
	public List<Link> getLinks() {
		return links;
	}
	
	/**
	 * Sets the set of links in this associations.
	 * @param links
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	/**
	 * Gets the set of link templates in this associations.
	 * @return the link templates.
	 */
	@JsonProperty(value="link-templates")
	public List<LinkTemplate> getLinkTemplates() {
		return linkTemplates;
	}
	
	/**
	 * Sets the set of link templates in this associations.
	 * @param linkTemplates
	 */
	@JsonProperty(value="link-templates")
	public void setLinkTemplates(List<LinkTemplate> linkTemplates) {
		this.linkTemplates = linkTemplates;
	}
	
	/**
	 * Searches the set of all links in this associations for a link with rel
	 * {@code rel}.
	 * 
	 * @param rel the link to find.
	 * @return the associated link.
	 * @throws RelNotFoundException a link with the given rel value was not found. 
	 */
	public Link findLink(String rel) {
		for(Link link : links) {
			if(link.getRel().equals(rel))
				return link;
		}
		throw new RelNotFoundException(rel);
	}
	
	/**
	 * Searches the set of all links in this associations for a link with rel
	 * {@code rel}. If no link found, {@code null} is returned.
	 * 
	 * @param rel the link to find.
	 * @return the associated link.
	 */
	public Link findLinkOrNull(String rel) {
		try {
			return findLink(rel);
		} catch(RelNotFoundException e) {
			return null;
		}
	}
	
	/**
	 * Searches the set of all links in this associations for a link-template with rel
	 * {@code rel}.
	 * 
	 * @param rel the link-template to find.
	 * @return the associated link-template.
	 * @throws RelNotFoundException a link-template with the given rel value was not found. 
	 */
	public LinkTemplate findLinkTemplate(String rel) {
		for(LinkTemplate link : linkTemplates) {
			if(link.getRel().equals(rel))
				return link;
		}
		throw new RelNotFoundException(rel);
	}
	
	/**
	 * Searches the set of all links in this associations for a link-template with rel
	 * {@code rel}. If no link-template is found {@code null} is returned.
	 * 
	 * @param rel the link-template to find.
	 * @return the associated link-template.
	 */
	public LinkTemplate findLinkTemplateOrNull(String rel) {
		try {
			return findLinkTemplate(rel);
		} catch(RelNotFoundException e) {
			return null;
		
		}
	}
}
