package openwis.pilot.awisc.server.manager.util.es;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import openwis.pilot.awisc.server.manager.util.Util;

public class ElasticsearchResultsSorter<T, N> {

	private Map<String, ElasticseaerchResultEntry<T, N>> hitsById = new HashMap<String, ElasticseaerchResultEntry<T, N>>();
	private Map<String, Double> hitScores = new HashMap<String, Double>();
	private Map<String,T> mainHitObjects = new HashMap<String, T>(); 
	
	private static double UNHIT_SCORE = 0.000000001D;

	/**
	 * Adds a hit and its inner_hits
	 * @param mainHit the main hit
	 * @param innerHits the inner hits
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void addHits(ElasticsearchHit<T> mainHit, List<ElasticsearchInnerHit<N>> innerHits)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		T mainHitObject = mainHit.get_source();
		mainHitObjects.put(mainHit.get_id(), mainHitObject);
		
		for (ElasticsearchInnerHit<N> innerHit : innerHits) {
			N innerHitObject = innerHit.get_source();

			Field innerHitIdfield = innerHitObject.getClass().getDeclaredField(innerHit.getIdProperty());
			innerHitIdfield.setAccessible(true);
			String innerHitId = (String) innerHitIdfield.get(innerHitObject);

			String compositeId = getCompositeId(mainHit.get_id(), innerHitId);
			ElasticseaerchResultEntry<T, N> result = hitsById.get(compositeId);
			if (result == null) {
				result = new ElasticseaerchResultEntry<T, N>();
				result.setMainObject(mainHitObject);
				result.setNestedObject(innerHitObject);
				result.setId(compositeId);
				result.setScore(innerHit.get_score());
			}
			hitsById.put(compositeId, result);
			hitScores.put(compositeId, innerHit.get_score());
		}
	}
		
	public void addMissingNestedHits(String mainHitId, List<N> nestedElements, String nestedElementIdPropertyName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		for(N nested:nestedElements) {
			Field innerHitIdfield = nested.getClass().getDeclaredField(nestedElementIdPropertyName);
			innerHitIdfield.setAccessible(true);
			String innerHitId = (String) innerHitIdfield.get(nested);

			String compositeId = getCompositeId(mainHitId, innerHitId);
			ElasticseaerchResultEntry<T, N> result = hitsById.get(compositeId);
			if (result == null) {
				result = new ElasticseaerchResultEntry<T, N>();
				result.setMainObject(mainHitObjects.get(mainHitId));
				result.setNestedObject(nested);
				result.setId(compositeId);
				result.setScore(UNHIT_SCORE);
				hitsById.put(compositeId, result);
				hitScores.put(compositeId, UNHIT_SCORE);
			}
			
		}
		
		
	}

	/**
	 * Creates a composite Id
	 * @param mainHitId
	 * @param innerHitId
	 * @return
	 */
	private String getCompositeId(String mainHitId, String innerHitId) {
		return mainHitId + ":" + innerHitId;
	}
	
	/**
	 * @return the hits sorted by score in descending order 
	 */
	public List<ElasticseaerchResultEntry<T, N>> getOrderedHits(){
		List<ElasticseaerchResultEntry<T, N>> orderedHits = new ArrayList<ElasticseaerchResultEntry<T, N>>();
		Map<String, Double> orderedScores = Util.sortByValue(hitScores);
		for(String key:orderedScores.keySet()) {
			orderedHits.add(hitsById.get(key));
		}
		return orderedHits;
	}


}
