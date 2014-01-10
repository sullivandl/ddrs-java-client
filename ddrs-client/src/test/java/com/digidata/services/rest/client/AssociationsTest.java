package com.digidata.services.rest.client;

import org.junit.Before;
import org.junit.Test;

import com.digidata.services.rest.client.exceptions.RelNotFoundException;

import static org.junit.Assert.*;

/**
 * 
 * @author dan.sullivan
 *
 */
public class AssociationsTest extends DdrsTest {

	@Before
	@Override
	public void setup()  {
		super.setup();
	}
	
	@Test
	public void findLink_success() {
		Associations assoc = new Associations();
		Link link = new Link();
		link.setRel("findLink_success");
		assoc.getLinks().add(link);
		assertSame(link, assoc.findLink(link.getRel()));
	}
	
	@Test(expected=RelNotFoundException.class)
	public void findLink_throws() {
		Associations assoc = new Associations();
		assoc.findLink("findLink_throws");
	}
	
	@Test
	public void findLinkOrNull_success() {
		Associations assoc = new Associations();
		Link link = new Link();
		link.setRel("findLinkOrNull_success");
		assoc.getLinks().add(link);
		assertSame(link, assoc.findLinkOrNull(link.getRel()));
	}
	
	@Test
	public void findLinkOrNull_null() {
		Associations assoc = new Associations();
		Link link = assoc.findLinkOrNull("findLinkOrNull_null");
		assertNull(link);
	}
	
	@Test
	public void findTemplate_success() {
		Associations assoc = new Associations();
		LinkTemplate template = new LinkTemplate();
		template.setRel("findTemplate_success");
		assoc.getLinkTemplates().add(template);
		assertSame(template,  assoc.findLinkTemplate(template.getRel()));
	}
	
	@Test(expected=RelNotFoundException.class)
	public void findTemplate_throws() {
		Associations assoc = new Associations();
		assoc.findLinkTemplate("findTemplate_throws");
	}
	
	@Test
	public void findTemplateOrNull_success() {
		Associations assoc = new Associations();
		LinkTemplate template = new LinkTemplate();
		template.setRel("findTemplateOrNull_success");
		assoc.getLinkTemplates().add(template);
		assertSame(template,  assoc.findLinkTemplateOrNull(template.getRel()));
	}
	
	@Test
	public void findTemplateOrNull_null() {
		Associations assoc = new Associations();
		LinkTemplate template = assoc.findLinkTemplateOrNull("findTemplateOrNull_null");
		assertNull(template);
	}
}
