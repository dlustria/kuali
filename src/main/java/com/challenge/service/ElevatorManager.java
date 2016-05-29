/**
 * $Header$
 *
 * kuali - com.challenge.service
 *
 **/

package com.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.challenge.constants.ElevatorState;
import com.challenge.model.Elevator;

/**
 * Elevator manager.
 *
 * @author <a href="mailto:dcjlustria@gmail.com">Dino Lustria</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
public class ElevatorManager {

	private List<Elevator> _elevators;

	/**
	 * Default ctor.
	 */
	public ElevatorManager() {
		final int numberOfFloors = 10;
		final int numberOfElevators = 10;
		initializeElavators(numberOfFloors, numberOfElevators);
	}

	/**
	 * @return the elevators
	 */
	public List<Elevator> getElevators() {
		return _elevators;
	}

	/**
	 * @param fromFloor
	 * @param toFloor
	 */
	public void requestElevator(final int fromFloor, final int toFloor) {
		final Elevator elevator = locateElevator(fromFloor, toFloor);

		if (elevator != null) {
			System.out.println("requested elevator " + elevator.getId());
		}
	}

	/**
	 * @param numberOfFloors
	 * @param numberOfElevators
	 */
	private void initializeElavators(int numberOfFloors, int numberOfElevators) {
		_elevators = new ArrayList<Elevator>();
		for (int i = 1; i <= numberOfFloors; i++) {
			_elevators.add(new Elevator(i, 1, numberOfFloors));
		}
	}

	/**
	 * @return
	 */
	private Elevator locateElevator(final int fromFloor, final int toFloor) {
		final ElevatorState requestedDirection = toFloor - fromFloor > 0 ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
		Elevator retval = null;
		Elevator closestMovingElevator = null;
		for (final Elevator elevator : _elevators) {
			if (elevator.getCurrentFloor() == fromFloor) {
				retval = elevator;
				break;
			}

			if (elevator.getCurrentFloor() < fromFloor && (elevator.isStopped() || elevator.isMovingUp()) && requestedDirection == ElevatorState.MOVING_UP) {
				if (closestMovingElevator == null) {
					closestMovingElevator = elevator;
				}
			}
		}

		return retval;
	}

}
