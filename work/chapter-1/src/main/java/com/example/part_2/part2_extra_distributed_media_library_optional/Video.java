package com.example.part_2.part2_extra_distributed_media_library_optional;

public class Video {
	private final String name;
	private final String location;
	private final String description;

	public Video(String name, String location, String description) {
		this.name = name;
		this.location = location;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}
}
