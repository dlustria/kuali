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
	 * Request an elevator.
	 *
	 * @param fromFloor floor to pickup passenger
	 * @param toFloor floor to dropoff passenger
	 * @return the allocated elevator
	 */
	public Elevator requestElevator(final int fromFloor, final int toFloor) {
		final Elevator elevator = locateElevator(fromFloor, toFloor);

		if (elevator == null) {
			System.out.println("no available elevator");
		} else {
			System.out.println("requested elevator " + elevator.getId());
		}

		return elevator;
	}

	private Elevator chooseCloserElevator(final Elevator elevator1, final Elevator elevator2, final int floor) {
		return Math.abs(elevator2.getCurrentFloor() - floor) < Math.abs(elevator1.getCurrentFloor() - floor) ? elevator2 : elevator1;
	}

	private void initializeElavators(int numberOfFloors, int numberOfElevators) {
		_elevators = new ArrayList<Elevator>();
		for (int i = 1; i <= numberOfFloors; i++) {
			_elevators.add(new Elevator(i, 1, numberOfFloors));
		}
	}

	private Elevator locateElevator(final int fromFloor, final int toFloor) {
		final ElevatorState requestedDirection = toFloor - fromFloor > 0 ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
		Elevator sameFloorElevator = null;
		Elevator closestStoppedElevator = null;
		Elevator closestMovingElevator = null;
		for (final Elevator elevator : _elevators) {
			if (elevator.getCurrentFloor() == fromFloor && elevator.isStopped()) {
				// stopped elevator at current floor is highest priority
				sameFloorElevator = elevator;
				break;
			}

			// don't check for stopped elevator if a moving one is on the way
			if (closestMovingElevator == null && elevator.isStopped()) {
				// keep track of closest stopped elevator in a different floor
				if (closestStoppedElevator == null) {
					closestStoppedElevator = elevator;
				} else {
					closestStoppedElevator = chooseCloserElevator(closestStoppedElevator, elevator, fromFloor);
				}
			} else {
				// keep track of closest moving elevator
				if (elevator.getCurrentFloor() < fromFloor && elevator.isMovingUp() && requestedDirection == ElevatorState.MOVING_UP) {
					// elevator is below pickup floor and is moving up, just
					// like the requested direction
					if (closestMovingElevator == null) {
						closestMovingElevator = elevator;
					} else {
						closestMovingElevator = chooseCloserElevator(closestMovingElevator, elevator, fromFloor);
					}
				} else if (elevator.getCurrentFloor() > fromFloor && elevator.isMovingDown() && requestedDirection == ElevatorState.MOVING_DOWN) {
					// elevator is above pickup floor and is moving down, just
					// like the requested direction
					if (closestMovingElevator == null) {
						closestMovingElevator = elevator;
					} else {
						closestMovingElevator = chooseCloserElevator(closestMovingElevator, elevator, fromFloor);
					}
				}
			}
		}

		return sameFloorElevator != null ? sameFloorElevator : closestMovingElevator != null ? closestMovingElevator : closestStoppedElevator;
	}

}
