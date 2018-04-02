package com.example.part_2.part2_extra_distributed_media_library_optional;

import java.util.List;

import com.example.annotations.Complexity;
import com.example.annotations.Optional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.annotations.Complexity.Level.MEDIUM;

public class MediaService {

	private final List<Server> availableContentServers = Servers.list();

	@Optional
	@Complexity(MEDIUM)
	public Mono<Video> findVideo(String videoName) {
		// TODO: using provided content servers search for video in every server and
		//       return first found result
		// HINT: consider Mono.first

		throw new RuntimeException("Not implemented");
	}
}
