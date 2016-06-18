/**
 * SkillRank.java
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * This class represents a given number of ranks in the skill, resulting in a
 * bonus.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@Getter
@AllArgsConstructor
public class SkillRank
{
	/**
	 * The skill in which this rank is based.
	 */
	@NonNull
	private Skill		baseSkill;

	/**
	 * Specialization, e.g. for Professions.
	 */
	private String	specialization;

	/**
	 * Bonus value.
	 */
	private int			bonus;

	@Override
	public String toString()
	{
		return String.format("%s (%s) %s", baseSkill.getSkillName(), baseSkill.getBaseAttribute().getAbbreviation(),
						I18nUtil.formatBonus(bonus));
	}
}
