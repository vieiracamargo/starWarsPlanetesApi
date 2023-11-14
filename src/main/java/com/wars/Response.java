package com.wars;

import java.util.List;

public record Response(
	List<ResultsItem> results
) {
}