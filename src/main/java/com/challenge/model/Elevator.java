/**
 * $Header$
 *
 * kuali - com.challenge.model
 *
 **/

package com.challenge.model;

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
	}

	/**
	 * @return the doorOpen
	 */
	public boolean isDoorOpen() {
		return _doorOpen;
	}

	/**
	 * @param targetFloor
	 */
	public void summonElevator(final int targetFloor) {
		if (targetFloor >= _minFloor && targetFloor <= _maxFloor) {
			while (_currentFloor != targetFloor) {
				moveTo(targetFloor);
			}
			openDoor();
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

	/**
	 * @param targetFloor
	 */
	private void moveTo(int targetFloor) {
		if (targetFloor > _currentFloor) {
			if (_doorOpen) {
				closeDoor();
			}
			_currentFloor++;
		} else if (targetFloor < _currentFloor) {
			if (_doorOpen) {
				closeDoor();
			}
			_currentFloor--;
		}

	}

	/**
	 * Open the door.
	 */
	private void openDoor() {
		if (!_doorOpen) {
			_doorOpen = true;
			reportDoor();
		}
	}

	private void reportDoor() {
		System.out.println("elevator " + _id + ": door " + (_doorOpen ? "open" : "close"));
	}

}
