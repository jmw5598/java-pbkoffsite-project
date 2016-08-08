package com.pbkoffsite.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pbkoffsite.web.bean.Sku;

public class ShelfAuditUtil {
	
	/**
	 * The compare method takes two parameters, the expected skus on the shelf
	 * and the actual skus counted on the shelf.  It returns the difference between
	 * the expected and actual as Map<Sku, Integer> with the Integer being a counter.
	 * The count will be negative if the sku is missing and positive if the sku is excess.
	 * 
	 * @param expected	The skus that are expected on the shelf.
	 * @param actual 	The skus that were actually counted on the shelf.
	 * @return			The different between the expected and the actual.
	 */
	public static Map<Sku, Integer> compare(List<Sku> expected, List<Sku> actual) {
		
		Map<Sku, Integer> result = new HashMap<>();
		//need to override equals method of Sku
		int count;
		
		for(Sku  sku: expected) {
			count = (result.containsKey(sku)) ? result.get(sku) : 0;
			result.put(sku, --count);
		}
		
		for(Sku sku: actual) {
			count = (result.containsKey(sku)) ? result.get(sku) : 0;
			result.put(sku, ++count);
		}
		
		return result;
	}
}
