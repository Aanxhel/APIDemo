package com.dev.Support.Networking.templates;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


public abstract class SSLClientFactory {

	@SuppressWarnings("unused")
	private static boolean allowUntrusted = false;
	@SuppressWarnings("unused")
	private static final long LOGIN_TIMEOUT_SEC = 10;
	private static HttpClientBuilder closeableClientBuilder = null;

	@SuppressWarnings("deprecation")
	public static synchronized ClientHttpRequestFactory getClientHttpRequestFactory() {

		ClientHttpRequestFactory requestFactory = null;

		SSLContext sslContext = SSLClientFactory.getSSlContext();

		if (null == sslContext) {
			return requestFactory;
		}

		closeableClientBuilder = HttpClientBuilder.create();

		// Add the SSLContext and trustmanager
		closeableClientBuilder.setSslcontext(getSSlContext());
		// add the hostname verifier
		closeableClientBuilder.setSSLHostnameVerifier(gethostnameVerifier());

		requestFactory = new HttpComponentsClientHttpRequestFactory(closeableClientBuilder.build());

		return requestFactory;

	}

	private static SSLContext getSSlContext() {

		final TrustManager[] trustAllCerts = new TrustManager[] { getTrustManager() };

		SSLContext sslContext = null;
		try {

			sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		return sslContext;

	}

	private static X509TrustManager getTrustManager() {

		final X509TrustManager trustManager = new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				X509Certificate[] cArrr = new X509Certificate[0];
				return cArrr;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		};

		return trustManager;
	}

	private static HostnameVerifier gethostnameVerifier() {

		HostnameVerifier hostnameVerifier = new HostnameVerifier() {

			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		};

		return hostnameVerifier;

	}

}
