package com.google.sps.data;

/** A message submission. */
public final class PortfolioSubmission {

  private final long id;
  private final String message;
  private final long timestamp;

  public PortfolioSubmission(long id, String message, long timestamp) {
    this.id = id;
    this.message = message;
    this.timestamp = timestamp;
  }
}
