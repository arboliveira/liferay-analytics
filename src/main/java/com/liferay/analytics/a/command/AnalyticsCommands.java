package com.liferay.analytics.a.command;

import com.liferay.analytics.b.client.AnalyticsClientHelper;
import com.liferay.analytics.c.index.AnalyticsIndexHelper;
import com.liferay.analytics.d.document.AnalyticsDocumentHelper;
import com.liferay.analytics.d.document.AnalyticsSearchHelper;

import java.util.Map;

import org.elasticsearch.client.Client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(
	immediate=true,
	service = Object.class,
	property = {
					"osgi.command.function=createIndex",
					"osgi.command.function=indexDocument",
					"osgi.command.function=search",
					"osgi.command.scope=analytics"
	}
)
public class AnalyticsCommands {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		AnalyticsClientHelper helper = new AnalyticsClientHelper();

		// TODO Export ConnectionMetadata from portal-search-elasticsearch

		helper.setClusterName("LiferayElasticsearchCluster");
		helper.setTransportAddresses("localhost:9300");

		_client = helper.connect();

		System.out.println("Analytics Create Index Command is ready...");
	}

	/**
	 * analytics:createIndex
	 */
	public void createIndex() {
		AnalyticsIndexHelper helper = new AnalyticsIndexHelper(_client);

		helper.create(INDEX);

		System.out.println("Created Index: " + INDEX);
	}

	/**
	 * analytics:indexDocument "One Two Three"
	 */
	public void indexDocument(String content) {
		AnalyticsDocumentHelper helper =
			new AnalyticsDocumentHelper(_client, INDEX);

		helper.indexDocument(content);

		System.out.println("Indexed Document: " + content);
	}

	/**
	 * analytics:search THREE
	 */
	public void search(String query) {
		AnalyticsSearchHelper helper =
			new AnalyticsSearchHelper(_client, INDEX);

		System.out.println(helper.search(query));
	}

	private Client _client;

	private static final String INDEX = "liferay-analytics";

}