/**
 * Loader.java
 * Created: 18.06.2016
 * (c) 2016 Martin Dreier
 */
package de.martindreier.rpg.prd.loader;

import java.util.List;
import java.util.concurrent.Callable;
import de.martindreier.rpg.prd.BaseEntity;

/**
 * Interface for a loader for a certain entity type. Actual loading is executeed
 * when calling the {@link Callable#call()} method.
 *
 * @author Martin Dreier <martin@martindreier.de>
 *
 */
public interface Loader<EntityType extends BaseEntity> extends Callable<List<EntityType>>
{
	/**
	 * Initialize the loader.
	 */
	public void initialize();
}
