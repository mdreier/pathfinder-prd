/**
 * Skill.java
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
 * Representation of a skill.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
@Getter
public enum Skill
{
	Acrobatics(null, Attribute.DEXTERITY, true, false), //
	Appraise(null, Attribute.INTELLIGENCE, false, false), //
	Bluff(null, Attribute.CHARISMA, false, false), //
	Climb(null, Attribute.STRENGTH, true, false), //
	Craft(null, Attribute.WISDOM, false, true), //
	Diplomacy(null, Attribute.CHARISMA, false, false), //
	DisableDevice(null, Attribute.DEXTERITY, true, false), //
	Disguise(null, Attribute.CHARISMA, false, false), //
	EscapeArtist(null, Attribute.DEXTERITY, true, false), //
	Fly(null, Attribute.DEXTERITY, true, false), //
	HandleAnimal(null, Attribute.CHARISMA, false, false), //
	Heal(null, Attribute.WISDOM, false, false), //
	Intimidate(null, Attribute.CHARISMA, false, false), //
	KnowledgeArcana("knowlede_arcana", Attribute.INTELLIGENCE, false, false), //
	KnowledgeDungeoneering("knowlede_dungeoneering", Attribute.INTELLIGENCE, false, false), //
	KnowledgeEngineering("knowlede_engineering", Attribute.INTELLIGENCE, false, false), //
	KnowledgeGeography("knowlede_geography", Attribute.INTELLIGENCE, false, false), //
	KnowledgeHistory("knowlede_history", Attribute.INTELLIGENCE, false, false), //
	KnowledgeLocal("knowlede_local", Attribute.INTELLIGENCE, false, false), //
	KnowledgeNature("knowlede_nature", Attribute.INTELLIGENCE, false, false), //
	KnowledgeNobility("knowlede_nobility", Attribute.INTELLIGENCE, false, false), //
	KnowledgePlanes("knowlede_planes", Attribute.INTELLIGENCE, false, false), //
	KnowledgeReligion("knowlede_religion", Attribute.INTELLIGENCE, false, false), //
	Linguistics(null, Attribute.INTELLIGENCE, false, false), //
	Perception(null, Attribute.WISDOM, false, false), //
	Perform(null, Attribute.CHARISMA, false, true), //
	Profession(null, Attribute.WISDOM, false, true), //
	Ride(null, Attribute.DEXTERITY, true, false), //
	SenseMotive(null, Attribute.WISDOM, false, false), //
	SleightOfHand(null, Attribute.DEXTERITY, true, false), //
	Spellcraft(null, Attribute.INTELLIGENCE, false, false), //
	Stealth(null, Attribute.DEXTERITY, true, false), //
	Survival(null, Attribute.WISDOM, false, false), //
	Swim(null, Attribute.STRENGTH, true, false), //
	UseMagicDevice(null, Attribute.CHARISMA, false, false);

	private Attribute	baseAttribute;

	/**
	 * Name of the skill.
	 */
	private String		skillName;

	/**
	 * Short skill description.
	 */
	private String		description;

	/**
	 * <code>true</code> if armor check penalty applies to this skill.
	 */
	private boolean		armorCheckPenalty;

	/**
	 * <code>true</code> if this skill is typed. This means that the skill has one
	 * or specification, e.g. Craft or Profession.
	 */
	private boolean		typed;

	/**
	 * Create a new skill.
	 *
	 * @param nameKey
	 *          The key for the translation of the name. May be <code>null</code>,
	 *          then the lower case enum name will be used as key.
	 * @param baseAttribute
	 *          Base attribute for this skill;
	 * @param hasArmorCheckPenalty
	 *          <code>true</code> if the skill is affected by armor check penalty.
	 * @param isTyped
	 *          <code>true</code> if the skill can has specializations, e.g. Craft
	 *          of Profession.
	 */
	private Skill(String nameKey, Attribute baseAttribute, boolean hasArmorCheckPenalty, boolean isTyped)
	{
		this.baseAttribute = baseAttribute;
		armorCheckPenalty = hasArmorCheckPenalty;
		typed = isTyped;
		if (nameKey == null)
		{
			nameKey = name().toLowerCase().replaceAll("\\w", "");
		}
		skillName = I18nUtil.getResourceBundle(Bundles.RULES).getString(nameKey);
		// TODO: Implement descriptions
		description = "";
	}
}
