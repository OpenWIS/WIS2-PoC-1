package openwis.pilot.awisc.server.manager.util.es;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ElasticsearchUtil {
	
	public static <T> ElasticsearchResponse<T> translateResponse(String elasticSearchResponseJson, Class<T> dtoClass){
		Gson gson = new Gson();
				
		Type t = TypeToken.getParameterized(ElasticsearchResponse.class, dtoClass).getType();
		//Type t = new TypeToken<ElasticsearchResponse<T>>() {}.getType();
		return gson.fromJson(elasticSearchResponseJson, t);
	}
	
	public static <T> List<ElasticsearchHit<T>> getHits(ElasticsearchResponse<T> response){
		if(response.getHits()!=null && response.getHits().getHits()!=null) {
			return response.getHits().getHits();
		}
		return new ArrayList<ElasticsearchHit<T>>();		
	}

	public static <T> List<T> getHitObjects(ElasticsearchResponse<T> response){
		List<T> list = new ArrayList<T>();
		
		if(response.getHits()!=null && response.getHits().getHits()!=null) {
			for(ElasticsearchHit<T> hit: response.getHits().getHits()) {
				T o = hit.get_source();
				list.add(o);
			}
		}
		
		return list;
	}
}
