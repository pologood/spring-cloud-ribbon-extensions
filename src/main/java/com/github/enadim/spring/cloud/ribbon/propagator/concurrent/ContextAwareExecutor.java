/*
 * Copyright (c) 2017 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.enadim.spring.cloud.ribbon.propagator.concurrent;

import com.github.enadim.spring.cloud.ribbon.context.ExecutionContext;
import org.springframework.core.task.TaskExecutor;

import javax.validation.constraints.NotNull;
import java.util.concurrent.Executor;

/**
 * Copies current {@link ExecutionContext} to delegate executor task.
 *
 * @author Nadim Benabdenbi
 */
public class ContextAwareExecutor implements Executor, TaskExecutor {
    /**
     * The delegate executor.
     */
    private final Executor delegate;

    /**
     * Sole Constructor.
     *
     * @param delegate the delegate executor.
     */
    public ContextAwareExecutor(@NotNull Executor delegate) {
        this.delegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void execute(Runnable command) {
        delegate.execute(ContextAwareRunnable.wrap(command));
    }
}
