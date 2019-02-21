package com.bookshelf;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class GetGoodreadsTask implements Runnable {
	private final CloseableHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpget;
	private GoodreadsBook book;

	//Use Generics. Create an abstract book class that these inherit from
	public GetGoodreadsTask(CloseableHttpClient httpClient, HttpGet httpget, Book currentBook) {
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
		this.httpget = httpget;
		//this.book = currentBook;
	}

	public GetGoodreadsTask(CloseableHttpClient httpClient, HttpGet httpget, GoodreadsBook currentBook) {
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
		this.httpget = httpget;
		this.book = currentBook;
	}
	
	@Override
	public void run() {
		try {
			CloseableHttpResponse response = httpClient.execute(httpget, context);
			try {
				HttpEntity httpEntity = response.getEntity();
				String res = EntityUtils.toString(httpEntity, "UTF-8");

				EntityUtils.consume(httpEntity);

				getDocument(res);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDocument(String docString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		InputSource is;
		try {
			builder = factory.newDocumentBuilder();
			is = new InputSource(new StringReader(docString));
			Document doc = builder.parse(is);
			
			var average_rating = doc.getElementsByTagName("average_rating").item(0).getTextContent();
			var ratings_count = doc.getElementsByTagName("ratings_count").item(0).getTextContent();
			book.setGoodreadsAverageRating(Double.parseDouble(average_rating));
			book.setGoodreadsRatingsCount(Integer.parseInt(ratings_count));
		} catch (NullPointerException e) {
			System.out.println("Failed book " + book);
			//Main.failedBooks.addBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}