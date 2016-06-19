/**
 * Saves.java
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import de.martindreier.rpg.prd.util.I18nUtil;
import lombok.Getter;
import lombok.NonNull;

/**
 * The saving throw bonuses of an entity. Implementation without any
 * intelligence, just stores, updates and returns the fixed values.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
public class Saves
{
	/**
	 * Represents the three save types: Fortitude, Reflex and Will.
	 *
	 * @author Martin Dreier <martin@martindreier.de>
	 */
	@Getter
	public static enum SaveType
	{
		FORTITUDE, REFLEX, WILL;

		private String type;

		private SaveType()
		{
			type = I18nUtil.translateEnumValue(this, "save");
		}
	}

	/**
	 * Values of saves.
	 */
	private final Map<SaveType, AtomicInteger> saves = new HashMap<>();

	/**
	 * Create a new set of saves with initial values.
	 *
	 * @param fortitude
	 *          Initial fortitude saving throw bonus.
	 * @param reflex
	 *          Initial reflex saving throw bonus.
	 * @param will
	 *          Initial will saving throw bonus.
	 */
	public Saves(int fortitude, int reflex, int will)
	{
		saves.put(SaveType.FORTITUDE, new AtomicInteger(fortitude));
		saves.put(SaveType.REFLEX, new AtomicInteger(reflex));
		saves.put(SaveType.WILL, new AtomicInteger(will));
	}

	/**
	 * Get the fortitude save.
	 *
	 * @return The fortitude save bonus.
	 */
	public int getFortitudeSave()
	{
		return saves.get(SaveType.FORTITUDE).get();
	}

	/**
	 * Get the reflex save.
	 *
	 * @return The reflex save bonus.
	 */
	public int getReflexSave()
	{
		return saves.get(SaveType.REFLEX).get();
	}

	/**
	 * Get the will save.
	 *
	 * @return The will save bonus.
	 */
	public int getWillSave()
	{
		return saves.get(SaveType.WILL).get();
	}

	/**
	 * Change the current value of a saving throw bonus.
	 *
	 * @param type
	 *          The type of saving throw.
	 * @param modification
	 *          The amount by which the current value is to be modified.
	 * @see #setSave(SaveType, int)
	 */
	public void updateSave(@NonNull SaveType type, int modification)
	{
		saves.get(type).addAndGet(modification);
	}

	/**
	 * Change the current value of a saving throw bonus to a new value.
	 *
	 * @param type
	 *          The type of saving throw.
	 * @param newValue
	 *          The new value of the saving throw.
	 * @see #updateSave(SaveType, int)
	 */
	public void setSave(@NonNull SaveType type, int newValue)
	{
		saves.get(type).set(newValue);
	}
}
