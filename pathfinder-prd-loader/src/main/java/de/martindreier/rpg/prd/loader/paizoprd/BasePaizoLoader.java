/**
 * BasePaizoLoader.java
 * Created: 18.06.2016
 * (c) 2016 Martin Dreier
 */
package de.martindreier.rpg.prd.loader.paizoprd;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import de.martindreier.rpg.prd.Source;

/**
 * Base for all loader implementations accessing the Paizo PRD website.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class BasePaizoLoader
{
	/**
	 * Base URI of the Paizo PRD document.
	 */
	protected final URI		baseURI	= URI.create("http://paizo.com/pathfinderRPG/prd/");

	/**
	 * Base URL in links on the site.
	 */
	private final String	baseUrl	= "/pathfinderRPG/prd/";

	/**
	 * Cached book list.
	 */
	private Set<String>		bookList;

	/**
	 * Get available books from the homepage. Returns the list of available book.
	 *
	 * @return The list of books available on the PRD website.
	 * @throws IOException
	 */
	protected Set<String> getBooks() throws IOException
	{
		if (bookList == null)
		{
			Document rootDocument = get("");
			bookList = new HashSet<>();

			// Structure of menu:
			// <ul class="level-2">
			// .. <li>
			// .... <a href="..." />
			// .. </li>
			// ...
			// </ul>

			// Get all <ul> elements
			Elements menuEntries = rootDocument.getElementsByClass("level-2");
			for (Element menuEntry : menuEntries)
			{
				// Loop over entries in list
				for (Element child : menuEntry.children())
				{
					// First child is link
					Element link = child.children().first();
					if (link != null)
					{
						String targetUrl = link.attr("href");
						if (targetUrl != null && targetUrl.startsWith(baseUrl))
						{
							String bookName = targetUrl.substring(baseUrl.length(), targetUrl.indexOf("/", baseUrl.length() + 1));
							bookList.add(bookName);
						}
					}
				}
			}
		}
		return bookList;
	}

	/**
	 * Get the content of a document.
	 *
	 * @param path
	 *          Document path relative to the base URL
	 * @return Parsed content.
	 * @throws IOException
	 *           Error reading the document.
	 */
	protected Document get(String path) throws IOException
	{
		URI requestUri = baseURI.resolve(path);
		String content = Request.Get(requestUri).execute().returnContent().asString();
		return Jsoup.parse(content);
	}

	/**
	 * Determine source from URL.
	 *
	 * @param url
	 *          The URL.
	 * @return The source determined from the URL.
	 */
	protected Source getSourceFromUrl(String url)
	{
		if (!url.startsWith(baseUrl))
		{
			return null;
		}
		String bookPart = url.substring(baseUrl.length(), url.indexOf("/", baseUrl.length() + 1));
		bookPart = bookPart.replaceAll("([A-Z])", "_$1").toUpperCase();
		// Special handling for some links
		if (bookPart.equals("BESTIARY"))
		{
			return Source.BESTIARY1;
		}
		// Default conversion
		return Source.valueOf(bookPart);
	}
}
