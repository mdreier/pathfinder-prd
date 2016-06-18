package de.martindreier.rpg.prd.loader;

import java.util.HashSet;
import java.util.Set;
import de.martindreier.rpg.prd.BaseEntity;
import de.martindreier.rpg.prd.loader.paizoprd.FeatLoader;

/**
 * Main class calling all known loaders and printing the output to the console.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public class PrdLoader
{
	/**
	 * All known loaders.
	 */
	public Set<Loader<? extends BaseEntity>> loaders;

	/**
	 * Fill list of known loaders.
	 */
	public PrdLoader()
	{
		loaders = new HashSet<>();
		loaders.add(new FeatLoader());
	}

	/**
	 * Execute all known loaders.
	 */
	public void loadAll()
	{
		for (Loader<?> loader : loaders)
		{
			loader.initialize();
			try
			{
				System.out.println(loader.call());
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		new PrdLoader().loadAll();
	}

}
