package com.example.part_2.part2_extra_distributed_media_library_optional;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Servers.class)
public class MediaServiceTest {

	@Test
	@SuppressWarnings("unchecked")
	public void findVideoTest() {
		List<Server> servers = Servers.list()
		                              .stream()
		                              .map(MockableServer::new)
		                              .collect(Collectors.toList());
		PowerMockito.mockStatic(Servers.class);
		PowerMockito.when(Servers.list())
		            .thenReturn(servers);

		MediaService service = new MediaService();
		StepVerifier.create(service.findVideo("test"))
		            .expectSubscription()
		            .expectNextCount(1)
		            .verifyComplete();

		long count = servers.stream()
		                    .map(MockableServer.class::cast)
		                    .map(MockableServer::getProbe)
		                    .filter(PublisherProbe::wasCancelled)
		                    .count();

		Assert.assertEquals(servers.size() - 1, count);
	}

	static final class MockableServer extends Server {

		private final Server                delegate;
		private       PublisherProbe<Video> probe;

		public MockableServer(Server delegate) {
			super("");
			this.delegate = delegate;
		}

		public PublisherProbe<Video> getProbe() {
			return probe;
		}

		@Override
		public Mono<Video> searchOne(String name) {
			return (probe = PublisherProbe.of(delegate.searchOne(name))).mono();
		}
	}
}