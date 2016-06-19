/**
 * EnergyType.java
 * Created: 19.06.2016
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
import lombok.Getter;

/**
 * Known energy types.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@Getter
public enum EnergyType
{
	ACID, COLD, ELECTRICITY, FIRE, FORCE, NEGATIVE, POSITIVE, SONIC;

	private String energy;

	private EnergyType()
	{
		energy = I18nUtil.translateEnumValue(this, "energy");
	}
}
