/**
 * ArmorClass.java
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import de.martindreier.rpg.prd.util.I18nUtil;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
public class ArmorClass
{
	/**
	 * Armor class modifier. May be a specific type (then
	 * {@link #referenceAttribute} is <code>null</code>, or is derived from an
	 * attribute (usually Dexterity, {@link #type} is <code>null</code>).
	 *
	 * @author Martin Dreier <martin@martindreier.de>
	 */
	@Getter
	public static class Modifier implements Comparable<Modifier>
	{
		/**
		 * Type of armor bonus.
		 */
		private BonusType	type;
		/**
		 * Armor bonus derived from an attribute.
		 */
		private Attribute	referenceAttribute;
		/**
		 * Value of armor bonus. May be negative.
		 */
		private int				value;

		/**
		 * Create an armor class modifier with a specific type.
		 *
		 * @param type
		 *          Bonus type.
		 * @param value
		 *          Bonus value. May be negative.
		 */
		public Modifier(@NonNull BonusType type, int value)
		{
			this.value = value;
			this.type = type;
		}

		/**
		 * Create an armor class modifier with a specific type.
		 *
		 * @param referenceAttribute
		 *          The reference attribute.
		 * @param value
		 *          Bonus value. May be negative.
		 */
		public Modifier(@NonNull Attribute referenceAttribute, int value)
		{
			this.value = value;
			this.referenceAttribute = referenceAttribute;
		}

		@Override
		public int compareTo(Modifier other)
		{
			// Always sort attribute references before others, sort attributes
			// according to order of reference attribute
			if (referenceAttribute != null)
			{
				if (other.referenceAttribute != null)
				{
					return referenceAttribute.compareTo(other.referenceAttribute);
				}
				else
				{
					return -1;
				}
			}
			else
			{
				if (other.referenceAttribute != null)
				{
					return 1;
				}
				else
				{
					return type.compareTo(other.type);
				}
			}
		}
	}

	private SortedSet<Modifier> modifiers = new TreeSet<>();

	/**
	 * Calculate total armor class value from all modifiers.
	 *
	 * @return
	 */
	public int getTotal()
	{
		int total = 10;
		for (Modifier modifier : modifiers)
		{
			total += modifier.value;
		}
		return total;
	}

	/**
	 * Get all modifiers to armor class.
	 *
	 * @return A copy of the list of modifiers.
	 */
	public List<Modifier> getModifiers()
	{
		return new ArrayList<>(modifiers);
	}

	/**
	 * Set or update a modifier with a given type. If a modifier with the same
	 * type is already set, the value is update. In all other cases a new modifier
	 * is added. Exception: Bonuses of type {@link BonusType#DODGE} are always
	 * added and never updated.
	 *
	 * @param type
	 *          Bonus type.
	 * @param newValue
	 *          New value of the modifier.
	 */
	public void updateModifier(@NonNull BonusType type, int newValue)
	{
		// Step 1: Find existing modifier of same type and update.
		if (type != BonusType.DODGE)
		{
			for (Modifier modifier : modifiers)
			{
				if (type == modifier.type)
				{
					modifier.value = newValue;
					return;
				}
			}
		}
		// Step 2: If modifier with given type is not yet present, add
		// new modifer.
		modifiers.add(new Modifier(type, newValue));
	}

	/**
	 * Set or update a modifier with a given type. If a modifier with the same
	 * type is already set, the value is update. In all other cases a new modifier
	 * is added. Exception: Bonuses of type {@link BonusType#DODGE} are always
	 * added and never updated.
	 *
	 * @param type
	 *          Bonus type.
	 * @param newValue
	 *          New value of the modifier.
	 */
	public void updateModifier(@NonNull Attribute referenceAttribute, int newValue)
	{
		// Step 1: Find existing modifier for given attribute and update.
		for (Modifier modifier : modifiers)
		{
			if (referenceAttribute == modifier.referenceAttribute)
			{
				modifier.value = newValue;
				return;
			}
		}
		// Step 2: If modifier with given attribute is not yet present, add
		// new modifer.
		modifiers.add(new Modifier(referenceAttribute, newValue));
	}

	/**
	 * Remove all modifiers for the given type.
	 *
	 * @param type
	 *          Bonus type of modifiers to be removed.
	 */
	public void removeModifer(@NonNull BonusType type)
	{
		Iterator<Modifier> allModifiers = modifiers.iterator();
		while (allModifiers.hasNext())
		{
			Modifier modifier = allModifiers.next();
			if (modifier.type == type)
			{
				allModifiers.remove();
			}
		}
	}

	/**
	 * Returns a simple output of &quot;AC&quot; plus the total AC value.
	 *
	 * @return Total AC.
	 */
	@Override
	public String toString()
	{
		return "AC " + getTotal();
	}

	/**
	 * Get the armor class line, including total and all modifiers.
	 *
	 * @return Armor class line for output.
	 */
	public String getArmorClassLine()
	{
		StringBuilder out = new StringBuilder();
		out.append(getTotal());
		if (!modifiers.isEmpty())
		{
			out.append(" (");
			Iterator<Modifier> allModifiers = modifiers.iterator();
			while (allModifiers.hasNext())
			{
				Modifier modifier = allModifiers.next();
				out.append(I18nUtil.formatBonus(modifier.value));
				out.append(" ");
				if (modifier.referenceAttribute != null)
				{
					out.append(modifier.referenceAttribute.getAbbreviation());
				}
				else
				{
					out.append(modifier.type.getBonusName());
				}
				if (allModifiers.hasNext())
				{
					out.append(", ");
				}
			}
			out.append(")");
		}
		return out.toString();
	}
}
