package openwis.pilot.awisc.server.manager.util.es;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ElasticsearchUtil {

	/**
	 * Structures the json returned by elastic under an ElasticsearchResponse<T>
	 * @param elasticSearchResponseJson the json returned by elastic 
	 * @param dtoClass the dto class of the main result object 
	 * @return the ElasticsearchResponse<T>
	 */
	public static <T> ElasticsearchResponse<T> translateResponse(String elasticSearchResponseJson, Class<T> dtoClass) {
		Gson gson = new Gson();

		Type t = TypeToken.getParameterized(ElasticsearchResponse.class, dtoClass).getType();
		return gson.fromJson(elasticSearchResponseJson, t);
	}

	/**
	 * Returns the first-level hits as List<ElasticsearchHit<T>>
	 * @param response the response from translateResponse()
	 * @return
	 */
	public static <T> List<ElasticsearchHit<T>> getHits(ElasticsearchResponse<T> response) {
		if (response.getHits() != null && response.getHits().getHits() != null) {
			return response.getHits().getHits();
		}
		return new ArrayList<ElasticsearchHit<T>>();
	}

	public static <T> List<T> getHitObjects(ElasticsearchResponse<T> response) {
		List<T> list = new ArrayList<T>();

		if (response.getHits() != null && response.getHits().getHits() != null) {
			for (ElasticsearchHit<T> hit : response.getHits().getHits()) {
				T o = hit.get_source();
				list.add(o);
			}
		}

		return list;
	}

	/**
	 * Get the inner_hits of the an elasticsearch hit 
	 * @param hit the original hit
	 * @param innerHitClass the class that the inner_hit content will be cast to
	 * @param innerHitsArrayPropertyName the property under which the inner hits are stored (in the ealsticsearch results json)
	 * @param innerHitIdPropertyName the property of the innerHitClass that will be used as an ID (for comparisons and other actions)
	 * @return
	 */
	public static <T, N> List<ElasticsearchInnerHit<N>> getInnerHits(ElasticsearchHit<T> hit, Class<N> innerHitClass,
			String innerHitsArrayPropertyName, String innerHitIdPropertyName) {
		List<ElasticsearchInnerHit<N>> list = new ArrayList<ElasticsearchInnerHit<N>>();
		Gson gson = new Gson();

		Type t = TypeToken.getParameterized(ElasticsearchInnerHit.class, innerHitClass).getType();

		JsonParser parser = new JsonParser();
		JsonObject innerHits = hit.getInner_hits();
		if (innerHits == null || innerHits.size() == 0) {
			return new ArrayList<ElasticsearchInnerHit<N>>();
		}
		JsonArray innerHitsArray = innerHits.getAsJsonObject(innerHitsArrayPropertyName).getAsJsonObject("hits").getAsJsonArray("hits");
		for (JsonElement je : innerHitsArray) {
			ElasticsearchInnerHit<N> ih = gson.fromJson(je, t);
			ih.setIdProperty(innerHitIdPropertyName);
			list.add(ih);
		}

		return list;

	}

}
