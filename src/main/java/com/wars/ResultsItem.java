package com.wars;

import java.util.List;

public record ResultsItem(
	List<String> films,
	String name
) {
}