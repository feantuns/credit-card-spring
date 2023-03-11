package br.com.fiap.creditcard.batch;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class ItemSkipPolicy implements SkipPolicy {

    public boolean shouldSkip(final Throwable t, final int skipCount) throws SkipLimitExceededException {
        return true;
    }
}