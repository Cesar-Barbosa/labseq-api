package com.labseq;

import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class LabseqService {

    private final ConcurrentHashMap<Integer, BigInteger> cache = new ConcurrentHashMap<>();

    public BigInteger calculate(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be >= 0");
		}

		// Cache parou (ou 0 se vazio)
		int start = cache.isEmpty()
			? 0
			: cache.keySet().stream().max(Integer::compare).orElse(0) + 1;

		// Calculando valores até n no cache
		for (int i = start; i <= n; i++) {
			BigInteger value;
			if (i == 0) value = BigInteger.ZERO;
			else if (i == 1 || i == 3) value = BigInteger.ONE;
			else if (i == 2) value = BigInteger.ZERO;
			else value = cache.get(i - 4).add(cache.get(i - 3));

			cache.put(i, value);
		}
		return cache.get(n);
	}
	
    public void clearCache() {
        cache.clear();
    }
}
