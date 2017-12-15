package com.example.cryptotrading.external;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
// HINT use PowerMock to mock static method behaviour and verify in integrity
@PrepareForTest(ReactiveCryptoListener.class)
public class CryptoConnectionHolderTest {

    @Test
    public void verifyThatSupportMultiSubscribers() {
        throw new RuntimeException("FIXME");
    }

    @Test
    public void verifyThatSupportIsolationAndResilience() {
        throw new RuntimeException("FIXME");
    }

    @Test
    public void verifyThatSupportReplayMode() {
        throw new RuntimeException("FIXME");
    }
}
