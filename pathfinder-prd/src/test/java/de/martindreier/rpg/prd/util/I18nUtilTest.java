/**
 * I18nUtilTest.java
 * Created: 18.06.2016
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
 * @author Martin Dreier <martin@martindreier.de>
 */
package de.martindreier.rpg.prd.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import de.martindreier.rpg.prd.util.I18nUtil.Bundles;

/**
 * Tests for the internationalization utility class.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@RunWith(JUnit4.class)
public class I18nUtilTest
{
	@Test
	public void bundleLoading()
	{
		assertNotNull("Bundle not loaded", I18nUtil.getResourceBundle(Bundles.RULES));
		assertNotNull("Text not found", I18nUtil.getResourceBundle(Bundles.RULES).getString("attribute.strength"));
	}

	@Test
	public void bonusFormat()
	{
		assertEquals("Incorrect bonus format", "+1", I18nUtil.formatBonus(1));
		assertEquals("Incorrect bonus format", "+0", I18nUtil.formatBonus(0));
		assertEquals("Incorrect bonus format", "-1", I18nUtil.formatBonus(-1));
	}
}
