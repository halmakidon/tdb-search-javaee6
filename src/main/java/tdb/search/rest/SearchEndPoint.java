package tdb.search.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import tdb.search.ejb.SearchResult;
import tdb.search.ejb.SearchService;
import tdb.search.util.Page;

@Stateless
@Path("/search")
public class SearchEndPoint {

	@EJB
	protected SearchService searchService;

    @Produces("application/json")
    @GET
    public SearchResult get(
    		@QueryParam("word") String word,
    		@QueryParam("page") int page) {
    	Page pageObj = new Page(page);
    	return searchService.search(word, pageObj);
    }
//    @Consumes("application/x-www-form-urlencoded")
//    @Produces("application/json")
//    @POST
//    public void login(String login) {
//
//    }
}