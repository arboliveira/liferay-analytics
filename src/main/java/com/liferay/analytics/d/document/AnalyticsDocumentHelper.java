package com.liferay.analytics.d.document;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;

public class AnalyticsDocumentHelper {

	public AnalyticsDocumentHelper(Client client, String index) {
		_client = client;
		_index = index;
	}

	public void indexDocument(String content) {
		IndexRequestBuilder indexRequestBuilder =
			_client.prepareIndex(_index, Constants.type);

		indexRequestBuilder.setSource(Constants.field, content);

		indexRequestBuilder.get();
	}

	private Client _client;

	private String _index;

}