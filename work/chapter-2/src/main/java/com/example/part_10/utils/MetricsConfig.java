package com.example.part_10.utils;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.dropwizard.DropwizardConfig;
import io.micrometer.core.instrument.dropwizard.DropwizardMeterRegistry;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;

public final class MetricsConfig {

	private MetricsConfig() {
	}

	public static void init() {
		final DropwizardConfig config = new DropwizardConfig() {
			@Override
			public String prefix() {
				return "dropwizard";
			}

			@Override
			public String get(String key) {
				return null;
			}
		};

		MetricRegistry metricRegistry = new MetricRegistry();
		final DropwizardMeterRegistry registry = new DropwizardMeterRegistry(config,
				metricRegistry,
				HierarchicalNameMapper.DEFAULT,
				Clock.SYSTEM) {
			@Override
			protected Double nullGaugeValue() {
				return Double.NaN;
			}
		};
		Metrics.addRegistry(registry);
		ConsoleReporter.forRegistry(metricRegistry)
		               .filter((name, metric) -> metric instanceof Meter)
		               .build()
		               .start(1, TimeUnit.SECONDS);
	}
}
