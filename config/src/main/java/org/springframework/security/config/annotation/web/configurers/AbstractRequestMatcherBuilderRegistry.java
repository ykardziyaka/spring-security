/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.config.annotation.web.configurers;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.web.util.matcher.RequestMatcher;

abstract class AbstractRequestMatcherBuilderRegistry<C> extends AbstractRequestMatcherRegistry<C> {

	private final RequestMatcherBuilder builder;

	AbstractRequestMatcherBuilderRegistry(ApplicationContext context) {
		this(context, RequestMatcherBuilders.createDefault(context));
	}

	AbstractRequestMatcherBuilderRegistry(ApplicationContext context, RequestMatcherBuilder builder) {
		setApplicationContext(context);
		this.builder = builder;
	}

	@Override
	public final C requestMatchers(String... patterns) {
		return requestMatchers(null, patterns);
	}

	@Override
	public final C requestMatchers(HttpMethod method, String... patterns) {
		return requestMatchers(this.builder.matchers(method, patterns).toArray(RequestMatcher[]::new));
	}

	@Override
	public final C requestMatchers(HttpMethod method) {
		return requestMatchers(method, "/**");
	}

}
