package com.liferay.analytics.d.document;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

public class AnalyticsSearchHelper {

	public AnalyticsSearchHelper(Client client, String index) {
		_client = client;
		_index = index;
	}

	public SearchResponse search(String query) {
		SearchRequestBuilder searchRequestBuilder =
			_client.prepareSearch(_index);

		searchRequestBuilder.setQuery(
			QueryBuilders.matchQuery(Constants.field, query));

		SearchResponse searchResponse = searchRequestBuilder.get();

		return searchResponse;
	}

	private Client _client;
	private String _index;

}