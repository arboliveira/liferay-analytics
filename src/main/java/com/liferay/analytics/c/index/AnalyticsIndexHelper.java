package com.liferay.analytics.c.index;

import org.elasticsearch.client.Client;

public class AnalyticsIndexHelper {

	public AnalyticsIndexHelper(Client client) {
		_client = client;
	}

	public void create(String index) {
		_client.admin().indices().prepareCreate(index).get();
	}

	private Client _client;
}