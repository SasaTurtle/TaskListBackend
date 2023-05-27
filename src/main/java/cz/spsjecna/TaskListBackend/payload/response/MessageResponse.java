package cz.spsjecna.TaskListBackend.payload.response;

/**
 * Register response DTO
 */
public final class MessageResponse {
  private String message;

  public MessageResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
