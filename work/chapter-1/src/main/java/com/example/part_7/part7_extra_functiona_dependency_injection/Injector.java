package com.example.part_7.part7_extra_functiona_dependency_injection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;

import com.example.annotations.Complexity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import static com.example.annotations.Complexity.Level.EASY;
import static com.example.annotations.Complexity.Level.MEDIUM;

@SuppressWarnings("unchecked")
public class Injector {
	static Class<Map> HOLDER_KEY = Map.class;

	@Complexity(MEDIUM)
	public static <T> Mono<T> inject(Class<T> source) {
		// TODO: retrieve Map from the context and in case of missed instance create it
		// TODO: return error stream in case if map is missed in the context
		//       using Injector.instantiate(source) method
		throw new RuntimeException("Not implemented");
	}

	@Complexity(EASY)
	public static <T> Mono<T> withInjector(Mono<T> in) {
		// TODO: provide Reactor Context with {HOLDER_KEY : ConcurrentHashMap}
		// TODO: provide additional checking to ensure that values was not erased

		return in;
	}

	@Complexity(EASY)
	public static <T> Flux<T> withInjector(Flux<T> in) {
		// TODO: provide Reactor Context with {HOLDER_KEY : ConcurrentHashMap}
		// TODO: provide additional checking to ensure that values was not erased

		return in;
	}

	/**
	 * Setting up proxy around object to give an access to Context
	 * @param instance
	 * @param baseInterfaces
	 * @param <T>
	 * @return
	 */
	public static <T> T proxy(T instance, Class<?>... baseInterfaces) {
		scanForInjection(instance);

		return (T) Proxy.newProxyInstance(
			ClassLoader.getSystemClassLoader(),
			baseInterfaces,
			handler(instance)
		);
	}

	/**
	 * Create Invocation Handler which provide actual context to the class
	 * Note, context will be available only for methods with reactive types
	 * @param instance
	 * @return
	 */
	private static InvocationHandler handler(Object instance) {
		return (proxy, method, args) -> {
			Class<?> type = method.getReturnType();

			if(Mono.class.isAssignableFrom(type)) {
				Mono result = (Mono) method.invoke(instance, args);

				return withInjector(result);
			} else if(Flux.class.isAssignableFrom(type)) {
				Flux result = (Flux) method.invoke(instance, args);

				return withInjector(result);
			} else {
				return method.invoke(instance, args);
			}
		};
	}

	/**
	 * Inject Monos with instances from the context
	 * @param instance
	 */
	private static void scanForInjection(Object instance) {
		Flux.fromArray(instance.getClass().getDeclaredFields())
		    .filter(f -> f.isAnnotationPresent(Inject.class))
		    .filter(f -> Mono.class.isAssignableFrom(f.getType()))
		    .flatMap(f ->
			    Mono.fromCallable(() -> {
					    f.set(instance, inject((Class) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0]));
					    return instance;
				    })
			        .then()
		    )
		    .blockLast();
	}

	private static <T> T instantiate(Class<T> source) {
		try {
			T instance;
			if (source.isInterface()) {
				instance = (T) source.getDeclaredMethod("instance").invoke(source);

				return proxy(instance, source);
			} else {
				instance = source.getDeclaredConstructor().newInstance();

				return proxy(instance, source.getInterfaces());
			}
		}
		catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
			throw new RuntimeException(e);
		}
	}
}
