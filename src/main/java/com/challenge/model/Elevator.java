/**
 * $Header$
 *
 * kuali - com.challenge.model
 *
 **/

package com.challenge.model;

import com.challenge.constants.ElevatorState;

/**
 * Elevator model.
 *
 * @author <a href="mailto:dcjlustria@gmail.com">Dino Lustria</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
public class Elevator {

	private final int _id;

	private final int _maxFloor;

	private final int _minFloor;

	private int _currentFloor;

	private boolean _doorOpen;

	private ElevatorState _elevatorState;

	private int _trips;

	private int _floorsPassed;

	/**
	 * Default ctor.
	 *
	 * @param id id.
	 * @param minFloor minimum floor number.
	 * @param maxFloor maximum floor number.
	 */
	public Elevator(final int id, final int minFloor, final int maxFloor) {
		_id = id;
		_minFloor = minFloor;
		_maxFloor = maxFloor;
		_currentFloor = minFloor;
		_elevatorState = ElevatorState.STOPPED;
	}

	/**
	 * @return the currentFloor
	 */
	public int getCurrentFloor() {
		return _currentFloor;
	}

	/**
	 * @return the elevatorState
	 */
	public ElevatorState getElevatorState() {
		return _elevatorState;
	}

	/**
	 * @return the floorsPassed
	 */
	public int getFloorsPassed() {
		return _floorsPassed;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return _id;
	}

	/**
	 * @return the numberOfTrips
	 */
	public int getNumberOfTrips() {
		return _trips;
	}

	/**
	 * @return the doorOpen
	 */
	public boolean isDoorOpen() {
		return _doorOpen;
	}

	/**
	 * @param elevatorState the elevatorState to set
	 */
	public void setElevatorState(ElevatorState elevatorState) {
		_elevatorState = elevatorState;
	}

	/**
	 * @param floorsPassed the floorsPassed to set
	 */
	public void setFloorsPassed(int floorsPassed) {
		_floorsPassed = floorsPassed;
	}

	/**
	 * @param numberOfTrips the numberOfTrips to set
	 */
	public void setNumberOfTrips(int numberOfTrips) {
		_trips = numberOfTrips;
	}

	/**
	 * @param targetFloor
	 */
	public void summonElevator(final int targetFloor) {
		if (targetFloor >= _minFloor && targetFloor <= _maxFloor) {
			while (_currentFloor != targetFloor) {
				moveTo(targetFloor);
			}
			stopElevator();
			openDoor();
			incrementTrips();
		} else {
			System.out.println("invalid floor");
		}
	}

	/**
	 * Close the door.
	 */
	private void closeDoor() {
		if (_doorOpen) {
			_doorOpen = false;
			reportDoor();
		}
	}

	private void incrementFloorsPassed() {
		_floorsPassed++;
	}

	private void incrementTrips() {
		_trips++;
	}

	/**
	 * @param targetFloor
	 */
	private void moveTo(int targetFloor) {
		if (targetFloor > _currentFloor) {
			if (_doorOpen) {
				closeDoor();
			}
			_currentFloor++;
			incrementFloorsPassed();
			setElevatorState(ElevatorState.MOVING_UP);
			reportFloor();
		} else if (targetFloor < _currentFloor) {
			if (_doorOpen) {
				closeDoor();
			}
			_currentFloor--;
			incrementFloorsPassed();
			setElevatorState(ElevatorState.MOVING_DOWN);
			reportFloor();
		}

	}

	/**
	 * Open the door.
	 */
	private void openDoor() {
		if (ElevatorState.STOPPED != getElevatorState()) {
			System.out.println("cannot open door of moving elevator.");
		} else if (!_doorOpen) {
			_doorOpen = true;
			reportDoor();
		}
	}

	private void reportDoor() {
		System.out.println("elevator " + _id + ": door " + (_doorOpen ? "open" : "close"));
	}

	private void reportFloor() {
		System.out.println("elevator " + _id + ": floor " + _currentFloor);
	}

	private void stopElevator() {
		_elevatorState = ElevatorState.STOPPED;
	}

}
