package com.jensens.daniel;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import com.sportsing.api.Match;

@Named
@ViewScoped
public class SportsingBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/Sportsing-Webservice/matchService");
	private String sport;
	
	public List<Match> getMatches() {
		return target.request(MediaType.APPLICATION_XML).get(new GenericType<List<Match>>(){});		
	}
	
	public void createMatch() {
		target.request(MediaType.APPLICATION_XML).post(Entity.xml(new Match(getMatches().size(), "aa")));
		System.out.println("Test " + target.path("Sport").request(MediaType.APPLICATION_XML).get(new GenericType<String>(){}));
	}
	
	/*public void editMatch() {
		
	}*/
	
	public void deleteMatch(Match m) {
		target.path("{id}").resolveTemplate("id", m.getId()).request(MediaType.APPLICATION_XML).delete();
	}
	public String getSport() { return sport; }
	public void setSport(String sport) { this.sport = sport; }
}