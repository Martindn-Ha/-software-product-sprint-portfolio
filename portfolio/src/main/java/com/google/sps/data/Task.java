package com.google.sps.data;

public final class Task {

    private final long id;
    private final String emailInput;
    private final String msgInput;
    private final long timestamp;
  
    public Task(long id, String emailInput, String msgInput, long timestamp) {
      this.id = id;
      this.emailInput = emailInput;
      this.msgInput = msgInput;
      this.timestamp = timestamp;
    }
  }