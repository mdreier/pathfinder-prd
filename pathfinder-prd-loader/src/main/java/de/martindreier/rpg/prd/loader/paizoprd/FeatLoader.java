/**
 * FeatLoader.java
 * Created: 18.06.2016
 * (c) 2016 Martin Dreier
 */
package de.martindreier.rpg.prd.loader.paizoprd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import de.martindreier.rpg.prd.Source;
import de.martindreier.rpg.prd.loader.Loader;
import de.martindreier.rpg.prd.rules.Feat;
import de.martindreier.rpg.prd.rules.Feat.FeatType;

/**
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class FeatLoader extends BasePaizoLoader implements Loader<Feat>
{

	/**
	 * CSS class prefix: type of feat.
	 */
	private static final String	FEAT_TYPE_CLASS_PREFIX		= "link-type-";
	/**
	 * CSS class prefix: feat source.
	 */
	private static final String	FEAT_SOURCE_CLASS_PREFIX	= "link-book-";

	@Override
	public void initialize()
	{}

	/**
	 * Read all feats from the Paizo PRD.
	 *
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public List<Feat> call() throws Exception
	{
		// Read feat index from PRD
		Document featIndex = get("indices/feats.html");
		// Entries in feat table
		Elements rowCells = featIndex.select("tr");
		List<Feat> feats = new ArrayList<>(rowCells.size());
		for (Element row : rowCells)
		{
			String rowClass = row.attr("class");
			// Filter out other tables on the page and the header row
			if (rowClass == null || !rowClass.startsWith(FEAT_SOURCE_CLASS_PREFIX))
			{
				continue;
			}
			// Get elements from row
			// | Feat name + link | Prerequisites | Feat Description |
			Element featName = row.child(0);
			Element prerequisitesElement = row.child(1);
			Element descriptionElement = row.child(2);
			// Build feat and add to result list
			Feat feat = new Feat(featName.text(), prerequisitesElement.text(), descriptionElement.text(),
							determineTypes(rowClass), determineSource(row, featName));
			feats.add(feat);
		}

		return feats;
	}

	/**
	 * Determine the source of the feat from the link in the feat name table cell
	 * or from the row class.
	 *
	 * @param row
	 *          The table row containing the feat.
	 * @param nameCell
	 *          The table cell containing the feat name.
	 * @return The source, or <code>null</code> if no source could be determined
	 *         from the link.
	 */
	private Source determineSource(Element row, Element nameCell)
	{
		if (nameCell.children().size() > 0)
		{
			Element link = nameCell.child(0);
			String featUrl = link.attr("href");
			if (featUrl != null)
			{
				return getSourceFromUrl(featUrl);
			}
		}
		// Some feats from the technology guide are not linked correctly
		// Alternative source determination from table row CSS classes
		String cssClasses = row.attr("class");
		for (String cssClass : cssClasses.split("\\s"))
		{
			if (cssClass.startsWith(FEAT_SOURCE_CLASS_PREFIX))
			{
				String bookId = cssClass.substring(FEAT_SOURCE_CLASS_PREFIX.length()).trim();
				if (bookId.equals("techguide"))
				{
					return Source.TECHNOLOGY_GUIDE;
				}
				else
				{
					System.out.println("Unkown: " + bookId);
				}
			}
		}
		return null;
	}

	/**
	 * Determine the feat type from the CSS class of the table row. The row may
	 * have class starting with <code>link-type-</code>, where the rest of the
	 * class name determines the feat type.
	 *
	 * @param rowClass
	 *          The row class.
	 * @return All recognized feat types. May be empty but never <code>null</code>
	 *         .
	 */
	private Set<FeatType> determineTypes(String rowClass)
	{
		Set<FeatType> types = new HashSet<>();
		for (String cssClass : rowClass.split("\\s"))
		{
			if (cssClass.startsWith(FEAT_TYPE_CLASS_PREFIX))
			{
				String type = cssClass.substring(FEAT_TYPE_CLASS_PREFIX.length());
				types.add(FeatType.valueOf(type.trim().toUpperCase().replaceAll("-", "_")));
			}
		}
		return types;
	}

}
