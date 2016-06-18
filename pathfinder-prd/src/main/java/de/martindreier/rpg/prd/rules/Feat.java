/**
 * Feat.java
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

import java.util.Collections;
import java.util.Set;
import de.martindreier.rpg.prd.BaseEntity;
import de.martindreier.rpg.prd.Source;
import lombok.Getter;
import lombok.NonNull;

/**
 * Representation of a feat.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@Getter
public class Feat extends BaseEntity
{
	/**
	 * Types of feats. A feat may have none, one, or multiple types.
	 *
	 * @author Martin Dreier <martin@martindreier.de>
	 */
	public static enum FeatType
	{
		ALIGNMENT, COMBAT, CRITICAL, ESOTERIC, GRIT, ITEM_CREATION, METAMAGIC, MYTHIC, PANACHE, PERFORMANCE, STARE, STORY,
		STYLE, TEAMWORK
	}

	/**
	 * Feat prerequisites.
	 */
	private final String				prerequisites;

	/**
	 * List of feat types. Empty for generic feats. Cannot be modified.
	 */
	private final Set<FeatType>	types;

	/**
	 * Create a new feat.
	 *
	 * @param name
	 *          Feat name.
	 * @param prerequisites
	 *          Prerequisites of the feat. May be <code>null</code>.
	 * @param description
	 *          Description of the feat.
	 * @param source
	 *          Feat source. May be <code>null</code>.
	 */
	public Feat(@NonNull String name, String prerequisites, @NonNull String description, Set<FeatType> types,
					Source source)
	{
		super(name, description, source);
		this.prerequisites = prerequisites;
		if (types == null)
		{
			this.types = Collections.emptySet();
		}
		else
		{
			this.types = Collections.unmodifiableSet(types);
		}
	}
}
