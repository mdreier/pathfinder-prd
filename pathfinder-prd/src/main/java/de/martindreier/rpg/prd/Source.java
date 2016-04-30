/**
 * Source.java
 * Created: 30.04.2016
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
 */
package de.martindreier.rpg.prd;

/**
 * Enumeration of known sources.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public enum Source
{
	// Comment markers at end of line used to prevent reformatting of list by IDE
	CORE_RULEBOOK("Pathfinder Core Rulebook", "core", "Paizo, Inc."), //
	BESTIARY1("Bestiary", "b1", "Paizo, Inc."), //
	BESTIARY2("Bestiary 2", "b2", "Paizo, Inc."), //
	BESTIARY3("Bestiary 3", "b3", "Paizo, Inc."), //
	BESTIARY4("Bestiary 4", "b4", "Paizo, Inc."), //
	BESTIARY5("Bestiary 5", "b5", "Paizo, Inc."), //
	BONUS_BESTIARY("Bonus Bestiary", "bb", "Paizo, Inc."), //
	GAME_MASTERY_GUIDE("GameMastery Guide", "gmg", "Paizo, Inc."), //
	ADVANCED_PLAYERS_GUIDE("Advanced Players Guide", "apg", "Paizo, Inc."), //
	ULTIMATE_COMBAT("Ultimate Combat", "uc", "Paizo, Inc."), //
	ULTIMATE_MAGIC("Ultimate Magic", "um", "Paizo, Inc."), //
	ADVANCED_RACE_GUIDE("Advanced Race Guide", "arg", "Paizo, Inc."), //
	ULTIMATE_EQUIPMENT("Ultimate Equipment", "ue", "Paizo, Inc."), //
	NPC_CODEX("NPC Codex", "", "Paizo, Inc."), //
	ULTIMATE_CAMPAIGN("Ultimate Campaign", "ucamp", "Paizo, Inc."), //
	GM_SCREEN("GM Screen", "gm-screen", "Paizo, Inc."), //
	PLAYER_CHARACTER_FOLIO("Player Character Folio", "pcf", "Paizo, Inc."), //
	MYTHIC_ADVENTURES("Mythic Adventures", "ma", "Paizo, Inc."), //
	ADVANCED_CLASS_GUIDE("Advanced Class Guide", "acg", "Paizo, Inc."), //
	MONSTER_CODEX("Monster Codex", "mc", "Paizo, Inc."), //
	STRATEGY_GUIDE("Strategy Guide", "sg", "Paizo, Inc."), //
	UNCHAINED("Pathfinder Unchained", "unch", "Paizo, Inc."), //
	OCCULT_ADVENTURES("Occult Adventures", "occ", "Paizo, Inc."), //
	ULTIMATE_INTRIGUE("Ultimate Intrigue", "ui", "Paizo, Inc."), //
	;

	/**
	 * Long name of the source.
	 */
	private String	sourceName;
	/**
	 * Tag to be used to identify objects belonging to a source. Must be unique
	 * within this class to ensure consistency of object names.
	 */
	private String	tag;
	/**
	 * Source author.
	 */
	private String	author;

	/**
	 * Create new source object.
	 *
	 * @param sourceName
	 *          Name of the source
	 * @param tag
	 *          Tag to identify objects belonging to this source.
	 * @param author
	 *          Source author.
	 */
	private Source(String sourceName, String tag, String author)
	{
		this.sourceName = sourceName;
		this.tag = tag;
		this.author = author;
	}

	/**
	 * Get the name of the source. This name is suitable for display.
	 *
	 * @return The source name.
	 */
	public String getSourceName()
	{
		return sourceName;
	}

	/**
	 * Get the tag identifying objects belonging to this source. This tag is used
	 * to make object names unique.
	 *
	 * @return Source tag.
	 */
	public String getTag()
	{
		return tag;
	}

	/**
	 * Get the source author.
	 *
	 * @return Author name.
	 */
	public String getAuthor()
	{
		return author;
	}
}
