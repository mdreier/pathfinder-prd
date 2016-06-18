/**
 * BaseEntity.java
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
package de.martindreier.rpg.prd;

import lombok.Data;

/**
 * Base for all PRD entities. Provides name, description and source.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@Data
public abstract class BaseEntity
{
	/**
	 * Name of this entity.
	 */
	private final String	name;

	/**
	 * Description of this entity.
	 */
	private final String	description;

	/**
	 * Source of this entity.
	 */
	private final Source	source;

	/**
	 * Print the name and the source of this entity.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		if (source == null)
		{
			return name;
		}
		return String.format("%s (%s)", name, source.getSourceName());
	}
}
