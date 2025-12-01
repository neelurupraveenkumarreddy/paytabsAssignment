package com.poc.bank.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RoutingService {

    private static final char ACCEPTED_PREFIX = '4';
    private static final int MIN_CARD_LENGTH = 12; // minimal sanity check
    private static final int MASK_SHOW_LAST = 4;

    /**
     * Check whether the card number meets routing rules (e.g., starts with '4').
     * Returns false for null/empty/invalid length.
     */
    public boolean isRoutable(String cardNumber) {
        if (!StringUtils.hasText(cardNumber)) return false;
        String normalized = cardNumber.replaceAll("\\s+", "");
        if (normalized.length() < MIN_CARD_LENGTH) return false;
        return normalized.charAt(0) == ACCEPTED_PREFIX;
    }

    /**
     * Mask the card number for safe logging/display: show only last 4 digits.
     * If cardNumber is short or empty, returns "****".
     *
     * Examples:
     *   4123 4567 8901 2345 -> ************2345
     */
    public String maskCard(String cardNumber) {
        if (!StringUtils.hasText(cardNumber)) return "****";
        String digits = cardNumber.replaceAll("\\s+", "");
        int len = digits.length();
        if (len <= MASK_SHOW_LAST) return "****" + digits;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len - MASK_SHOW_LAST; i++) sb.append('*');
        sb.append(digits.substring(len - MASK_SHOW_LAST));
        return sb.toString();
    }
}
