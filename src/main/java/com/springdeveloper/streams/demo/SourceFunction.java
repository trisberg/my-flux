/*
 * Copyright 2018 the original author or authors.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.springdeveloper.streams.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SocketUtils;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Thomas Risberg
 **/
public class SourceFunction implements Function<Flux<String>, Flux<String>> {
// public class SourceFunction implements Supplier<Flux<String>> {

	public static Logger logger = LoggerFactory.getLogger(SourceFunction.class);

	EmitterProcessor<String> records = EmitterProcessor.create();

	static int count = 1;

	@Override
	public Flux<String> apply(Flux<String> stringFlux) {
	// public Flux<String> get() {
			logger.info("*** [FUNC] Called!");
			records.onNext("Init: " + count++);
			try {
				listen();
			}
			catch (Exception e) {
				logger.error("** ERROR " + e);
			}
			return records;
	}

	public void listen() throws Exception {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			logger.info("*** Message Created!");
			records.onNext("Test: " + count++);
		  }
		}, 0, 2, TimeUnit.SECONDS);
	}

}
