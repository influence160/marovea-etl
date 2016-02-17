package com.otiliouine.marovea.ui.util;

import java.util.Arrays;

public class IntSuite {

    private int[] currentOrder;
    private int movedElement;
    private int movedFrom;
    private int movedTo;

    public IntSuite(int[] order) {
	this.currentOrder = order;
    }

    public boolean move(int[] order) {
	if (order.length != length()) {
	    throw new IllegalArgumentException("invalid move input "
		    + Arrays.toString(order)
		    + ", length are not same, currentOrder = "
		    + Arrays.toString(currentOrder));
	}
	if (!sameElements(order)) {
	    throw new IllegalArgumentException("invalid move input "
		    + Arrays.toString(order)
		    + ", elements are not same, currentOrder = "
		    + Arrays.toString(currentOrder));
	}

	int currentIndex = 0;
	while (currentIndex < length()
		&& order[currentIndex] == currentOrder[currentIndex]) {
	    currentIndex++;
	}

	if (currentIndex == length()) {
	    return false;
	} else {
	    int firstChanged = currentIndex;
	    if (order[firstChanged] == currentOrder[firstChanged + 1]) {
		movedElement = currentOrder[firstChanged];
		movedFrom = firstChanged;
		movedTo = indexOf(order, firstChanged + 1, movedElement);
		if (movedTo == -1) {
		    throw new IllegalArgumentException("invalid move input "
			    + Arrays.toString(order) + ", moved element "
			    + movedElement + " is not found, currentOrder = "
			    + Arrays.toString(currentOrder));
		}
	    } else if (currentOrder[firstChanged] == order[firstChanged + 1]) {
		movedTo = firstChanged;
		movedElement = order[movedTo];
		movedFrom = indexOf(currentOrder, firstChanged + 1,
			movedElement);
		if (movedFrom == -1) {
		    throw new IllegalArgumentException("invalid move input "
			    + Arrays.toString(order)
			    + ", moved index  is not found, currentOrder = "
			    + Arrays.toString(currentOrder));
		}
	    } else {
		throw new IllegalArgumentException(
			"invalid move input "
				+ Arrays.toString(order)
				+ ", cant match a single move operation, currentOrder = "
				+ Arrays.toString(currentOrder));
	    }
	}
	this.currentOrder = order;
	return true;
    }

    private int indexOf(int[] t, int startsFrom, int element) {
	int index = startsFrom;
	while (index < t.length) {
	    if (t[index] == element) {
		return index;
	    }
	    index++;
	}
	return -1;
    }

    public boolean sameElements(int[] order) {
	if (order.length != length()) {
	    return false;
	}
	for (int x : currentOrder) {
	    if (indexOf(order, 0, x) == -1) {
		return false;
	    }
	}
	return true;
    }

    public int getMovedElement() {
	return movedElement;
    }

    public int getMovedFrom() {
	return movedFrom;
    }

    public int getMovedTo() {
	return movedTo;
    }

    private int length() {
	return currentOrder.length;
    }
}
