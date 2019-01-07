package com.bookshelf;

import java.util.List;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Service;

@Service
public class GoodreadsServiceImpl implements GoodreadsService {

	private final String goodreadsURL = "https://www.goodreads.com/book/title.xml?key=yXZIGleYDqexQC7C40PFg&title=";
	private Bookshelf bookshelf;
	private Calculator calculator;

	@Override
	public List<Book> addGoodreadsBooks(List<Book> books) {
		bookshelf = new Bookshelf(books);
		Thread[] threads = new Thread[bookshelf.getNumberOfBooks()];
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(5); // increase max total connection to 20
		cm.setDefaultMaxPerRoute(5); // increase max connection per route to 20
		RequestConfig localConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		
		for (int i = 0; i < threads.length; i++) {
			var currentBook = bookshelf.getBook(i);
			HttpGet httpgetGoodreads = new HttpGet(goodreadsURL + currentBook.getISBN());
			/*try {
				httpgetGoodreads = new HttpGet(goodreadsURL + URLEncoder.encode(currentBook.getTitle(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				httpgetGoodreads = new HttpGet(
						goodreadsURL + currentBook.getISBN());
			}*/
			httpgetGoodreads.setConfig(localConfig);
			threads[i] = new Thread(new GetGoodreadsTask(httpClient, httpgetGoodreads, currentBook));
			threads[i].start();
		}
		// wait for all the threads to finish
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		calculator = new Calculator(bookshelf);
		return bookshelf.getBooks();
	}

}
