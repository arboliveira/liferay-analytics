
package com.liferay.analytics.b.client;

import com.liferay.java.net.InetAddressUtil;

import java.util.Arrays;
import java.util.Collection;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class AnalyticsClientHelper {

	public Client connect() {
		TransportClient.Builder transportClientBuilder =
			TransportClient.builder();

		Settings.Builder settingsBuilder = Settings.builder();

		settingsBuilder.put("cluster.name", _clusterName);
		settingsBuilder.put("http.enabled", true);
		settingsBuilder.put("node.client", true);
		settingsBuilder.put("node.data", false);

		transportClientBuilder.settings(settingsBuilder);

		TransportClient transportClient = transportClientBuilder.build();

		_transportAddresses.stream().map(
			InetAddressUtil::toInetSocketAddress).map(
				InetSocketTransportAddress::new).forEach(
					transportClient::addTransportAddress);

		return transportClient;
	}

	public void setClusterName(String clusterName) {
		_clusterName = clusterName;
	}

	public void setTransportAddresses(String... transportAddresses) {
		_transportAddresses = Arrays.asList(transportAddresses);
	}

	private String _clusterName;
	private Collection<String> _transportAddresses;

}
