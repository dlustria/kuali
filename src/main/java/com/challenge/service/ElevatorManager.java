/**
 * $Header$
 *
 * kuali - com.challenge.service
 *
 **/

package com.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.challenge.model.Elevator;

/**
 * Elevator manager.
 *
 * @author <a href="mailto:dcjlustria@gmail.com">Dino Lustria</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
public class ElevatorManager {

	private final List<Elevator> _elevators;

	/**
	 * Default ctor.
	 */
	public ElevatorManager() {
		final int numberOfFloors = 10;
		final int numberOfElevators = 10;
		_elevators = initializeElavators(numberOfFloors, numberOfElevators);
	}

	/**
	 * @return the elevators
	 */
	public List<Elevator> getElevators() {
		return _elevators;
	}

	/**
	 * @param numberOfFloors
	 * @param numberOfElevators
	 */
	private List<Elevator> initializeElavators(int numberOfFloors, int numberOfElevators) {
		final List<Elevator> retval = new ArrayList<Elevator>();

		for (int i = 1; i <= numberOfFloors; i++) {
			retval.add(new Elevator(i, 1, numberOfFloors));
		}

		return retval;
	}

}
