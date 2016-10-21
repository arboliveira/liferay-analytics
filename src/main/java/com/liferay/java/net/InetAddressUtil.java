package com.liferay.java.net;

import java.net.InetSocketAddress;

public class InetAddressUtil {

	public static InetSocketAddress toInetSocketAddress(String addressString) {
		int index = addressString.indexOf(':');

		String host = addressString.substring(0, index);
		int port = Integer.parseInt(addressString.substring(index + 1));

		return new InetSocketAddress(host, port);
	}

}