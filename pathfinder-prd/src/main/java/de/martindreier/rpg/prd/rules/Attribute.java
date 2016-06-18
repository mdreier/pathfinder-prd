/**
 * Attribute.java
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

import de.martindreier.rpg.prd.util.I18nUtil;
import de.martindreier.rpg.prd.util.I18nUtil.Bundles;
import lombok.Getter;

/**
 * Representation of an attribute. Do not instantiate.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@Getter
public enum Attribute
{
	/**
	 * Strength
	 */
	STRENGTH,
	/**
	 * Dexterity
	 */
	DEXTERITY,
	/**
	 * Dexterity
	 */
	CONSTITUTION,
	/**
	 * Dexterity
	 */
	INTELLIGENCE,
	/**
	 * Dexterity
	 */
	WISDOM,
	/**
	 * Dexterity
	 */
	CHARISMA;

	/**
	 * Abbreviation of the feat.
	 */
	private String	abbreviation;

	/**
	 * Name of the feat.
	 */
	private String	attributeName;

	/**
	 * Create a new attribute.
	 */
	private Attribute()
	{
		String textKey = "attribute." + name().toLowerCase();
		this.abbreviation = I18nUtil.getResourceBundle(Bundles.RULES).getString(textKey + ".abbreviation");
		this.attributeName = I18nUtil.getResourceBundle(Bundles.RULES).getString(textKey);
	}

	/**
	 * Calculate the bonus for a given attribute score.
	 *
	 * @param attributescore
	 *          The attribute score. Must be zero or positive.
	 * @return The corresponding attribute bonus.
	 */
	public static int calculateBonus(int attributescore)
	{
		if (attributescore < 0)
		{
			throw new IllegalArgumentException("Attribute value must be zero or positive");
		}
		switch (attributescore)
		{
			case 0:
			case 1:
				return -5;
			case 2:
			case 3:
				return -4;
			case 4:
			case 5:
				return -3;
			case 6:
			case 7:
				return -2;
			case 8:
			case 9:
				return -1;
			default:
				return (attributescore - 10) / 2;
		}

	}
}
