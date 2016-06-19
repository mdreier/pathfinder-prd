/**
 * Resistances.java
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Handles all resistances of a creature: Spell resistance, damage reductions,
 * energy resistances.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
public class Resistances
{
	/**
	 * Spell resistance.
	 */
	@Getter
	private final AtomicInteger										spellResistance		= new AtomicInteger(0);

	/**
	 * Energy resistances (types and amounts).
	 */
	private final Map<EnergyType, AtomicInteger>	energyResistances	= new HashMap<>();

	/**
	 * Damage reduction line.
	 */
	@Getter
	@Setter
	private String																damageReduction		= null;

	/**
	 * Turn resistance.
	 */
	@Getter
	private final AtomicInteger										turnResistance		= new AtomicInteger(0);

	/**
	 * Vulnerability types.
	 */
	@Getter
	private final Set<EnergyType>									vulnerabilities		= new HashSet<>();

	/**
	 * Get the energy resistance for a specific energy type.
	 *
	 * @param type
	 *          The energy type.
	 * @return The resistance, or <code>0</code> if the creature is not resistant
	 *         against the energy type.
	 */
	public int getEnergyResistance(@NonNull EnergyType type)
	{
		if (energyResistances.containsKey(type))
		{
			return energyResistances.get(type).get();
		}
		else
		{
			return 0;
		}
	}

	/**
	 * Set the energy resistance for a specific energy type.
	 *
	 * @param type
	 *          The energy type.
	 * @param newValue
	 *          The new value. Must be 0 or positive.
	 */
	public void setEnergyResistance(@NonNull EnergyType type, int newValue)
	{
		if (newValue < 0)
		{
			throw new IllegalArgumentException("Resistance value must be 0 or positive");
		}
		else if (newValue == 0)
		{
			// For resistance value 0, remove resistance from set
			energyResistances.remove(type);
		}
		else
		{
			if (energyResistances.containsKey(type))
			{
				energyResistances.get(type).set(newValue);
			}
			else
			{
				energyResistances.put(type, new AtomicInteger(newValue));
			}
		}
	}

	/**
	 * Get output for the resistances line.
	 *
	 * @return Print output of all energy resistances.
	 */
	public String getEnergyResistances()
	{
		Iterator<EnergyType> resistances = energyResistances.keySet().iterator();
		StringBuilder out = new StringBuilder();
		while (resistances.hasNext())
		{
			EnergyType energyType = resistances.next();
			out.append(energyType.getEnergy());
			out.append(" ");
			out.append(energyResistances.get(energyType).get());
			if (resistances.hasNext())
			{
				out.append(", ");
			}
		}
		return out.toString();
	}

	/**
	 * Check if a creature has spell resistance.
	 *
	 * @return <code>true</code> iff the spell resistance is set to a positive
	 *         amount.
	 */
	public boolean hasSpellResistance()
	{
		return spellResistance.get() > 0;
	}

	/**
	 * Check if a creature has energy resistance.
	 *
	 * @return <code>true</code> iff at least one energy resistance is set.
	 */
	public boolean hasEnergyResistance()
	{
		return !energyResistances.isEmpty();
	}

	/**
	 * Check if a creature has turn resistance.
	 *
	 * @return <code>true</code> iff the turn resistance is set to a positive
	 *         amount.
	 */
	public boolean hasTurnResistance()
	{
		return turnResistance.get() > 0;
	}

	/**
	 * Check if a creature has damage reduction.
	 *
	 * @return <code>true</code> iff the damage reduction is set to a non-empty
	 *         String.
	 */
	public boolean hasDamageReduction()
	{
		return damageReduction != null && damageReduction.trim().length() > 0;
	}

	/**
	 * Check if a creature has vulnerabilities.
	 *
	 * @return <code>true</code> iff at least one vulnerability is set.
	 */
	public boolean hasVulnerabilities()
	{
		return !vulnerabilities.isEmpty();
	}
}
