/**
 * SourceTest.java
 * Created: 30.04.2016
 * (c) 2016 Martin Dreier
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.martindreier.rpg.prd;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for the {@link Source} enumeration. Ensures consistency of the
 * tags and completeness of information.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
@RunWith(JUnit4.class)
public class SourceTest
{
	/**
	 * Test that all sources have name, tag and author set.
	 */
	@Test
	public void allSourcesComplete()
	{
		for (Source source : Source.values())
		{
			assertNotNull(String.format("Source %s is missing author", source.name()), source.getAuthor());
			assertNotNull(String.format("Source %s is missing tag", source.name()), source.getTag());
			assertNotNull(String.format("Source %s is missing name", source.name()), source.getSourceName());
		}
	}

	/**
	 * Test that tags are unique.
	 */
	@Test
	public void noDuplicateTags()
	{
		Map<String, Source> seenTags = new HashMap<>();
		for (Source source : Source.values())
		{
			if (seenTags.containsKey(source.getTag()))
			{
				Source otherSource = seenTags.get(source.getTag());
				fail(String.format("Sources %s and %s have same tag %s", source.name(), otherSource.name(), source.getTag()));
			}
			seenTags.put(source.getTag(), source);
		}
	}
}
