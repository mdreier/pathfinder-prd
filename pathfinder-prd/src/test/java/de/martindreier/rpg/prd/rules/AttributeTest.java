/**
 * AttributeTest.java
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
package de.martindreier.rpg.prd.rules;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for the attribute class.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@RunWith(JUnit4.class)
public class AttributeTest
{
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test that all attributes are present.
	 */
	@Test
	public void onlySixAttributes()
	{
		assertEquals("Incorrect number of attributes", 6, Attribute.values().length);
	}

	/**
	 * Test that attribute bonuses are calculated correctly.
	 */
	@Test
	public void bonusCalculation()
	{
		assertEquals("Incorrect bonus calculation", -5, Attribute.calculateBonus(0));
		assertEquals("Incorrect bonus calculation", -5, Attribute.calculateBonus(1));
		assertEquals("Incorrect bonus calculation", -4, Attribute.calculateBonus(2));
		assertEquals("Incorrect bonus calculation", -1, Attribute.calculateBonus(9));
		assertEquals("Incorrect bonus calculation", 0, Attribute.calculateBonus(10));
		assertEquals("Incorrect bonus calculation", 0, Attribute.calculateBonus(11));
		assertEquals("Incorrect bonus calculation", 1, Attribute.calculateBonus(12));
		assertEquals("Incorrect bonus calculation", 5, Attribute.calculateBonus(20));
		assertEquals("Incorrect bonus calculation", 5, Attribute.calculateBonus(21));
		assertEquals("Incorrect bonus calculation", 6, Attribute.calculateBonus(22));

		thrown.expect(IllegalArgumentException.class);
		Attribute.calculateBonus(-1);
	}
}
