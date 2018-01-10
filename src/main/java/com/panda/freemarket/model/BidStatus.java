package com.panda.freemarket.model;

/**
 * Created by gzge. All Rights Reserved
 */
public enum BidStatus {
    OPEN("open"),
    LOSS("loss"),
    WIN("win");

    private String type;

    BidStatus(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * Given a string, check if it is a valid status type
     * @param statusType
     * @return
     */
    public static boolean isValid(String statusType) {
        for (BidStatus s : BidStatus.values()) {
            if (s.getType().equalsIgnoreCase(statusType)) {
                return true;
            }
        }
        return false;
    }
}
