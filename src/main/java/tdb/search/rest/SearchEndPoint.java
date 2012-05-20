package tdb.search.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import tdb.search.ejb.SearchService;
import tdb.search.util.Page;

@Stateless
@Path("/search")
public class SearchEndPoint {

	@EJB
	protected SearchService searchService;

	@Produces("application/json")
	@GET
	public Response get(@QueryParam("word") String word,
			@QueryParam("page") String page) {

		// 入力チェック
		if (StringUtils.isEmpty(word)
				|| StringUtils.isEmpty(page)
				|| StringUtils.isNumeric(page)) {
			ErrorEntity error = new ErrorEntity(ErrorEntity.MESSAGE);
			return Response.status(400).entity(error).build();
		}
		Page pageObj = new Page(Integer.parseInt(page));
		return Response.status(200).entity(searchService.search(word, pageObj)).build();
	}
}