/**
 * I18nUtil.java
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
package de.martindreier.rpg.prd.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import lombok.NonNull;

/**
 * Utility class for internationalization.
 *
 * @author Martin Dreier <martin@martindreier.de>
 */
public class I18nUtil
{
	/**
	 * Available resource bundles.
	 *
	 * @author Martin Dreier <martin@martindreier.de>
	 */
	public static enum Bundles
	{
		/**
		 * Translations for rules objects.
		 */
		RULES("Rules");

		/**
		 * Base name of the resource bundle.
		 */
		private String resourceBundleName;

		/**
		 * Create new bundle reference.
		 *
		 * @param resourceBundleName
		 *          Base name of the resource bundle.
		 */
		private Bundles(@NonNull String resourceBundleName)
		{
			this.resourceBundleName = resourceBundleName;
		}

		@Override
		public String toString()
		{
			return name() + " (" + resourceBundleName + ".properties)";
		}
	}

	/**
	 * Cache for already loaded resource bundles.
	 */
	private static final Map<Bundles, ResourceBundle> resourceBundleCache = new HashMap<>();

	/**
	 * Get a resource bundle.
	 *
	 * @param bundle
	 *          The bundle key.
	 * @return The resource bundle for the given key.
	 */
	public static ResourceBundle getResourceBundle(@NonNull Bundles bundle)
	{
		// Lookup bundle in cache first
		if (resourceBundleCache.containsKey(bundle))
		{
			return resourceBundleCache.get(bundle);
		}
		else
		{
			// Load bundle and store in cache
			ResourceBundle resource = ResourceBundle.getBundle("i18n." + bundle.resourceBundleName);
			resourceBundleCache.put(bundle, resource);
			return resource;
		}
	}

	/**
	 * Format a bonus with leading sign.
	 *
	 * @param bonus
	 *          The bonus value.
	 * @return Formatted output.
	 */
	public static String formatBonus(int bonus)
	{
		return String.format("%+d", bonus);
	}

	/**
	 * Provide a translation for an enum value from the {@link Bundles#RULES Rules
	 * bundle}. Used by constructors for enum types.
	 *
	 * @param enumValue
	 *          The value to translate.
	 * @param prefix
	 *          Type-specific prefix of the translation key. Provided without
	 *          separator (.).
	 * @return Translated value.
	 */
	public static String translateEnumValue(Enum<?> enumValue, String prefix)
	{
		return getResourceBundle(Bundles.RULES).getString(prefix + "." + enumValue.name().toLowerCase());
	}
}
