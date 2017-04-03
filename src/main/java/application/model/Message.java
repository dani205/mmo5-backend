package application.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Message<T> {

  protected final MsgType msgType;
  protected final T data;

  public Message(MsgType msgType, T data) {
    this.msgType = msgType;
    this.data = data;
  }

  public MsgType getMsgType() {
    return msgType;
  }

  public T getData() {
    return data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Message<?> message = (Message<?>) o;
    return msgType == message.msgType &&
            Objects.equal(data, message.data);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(msgType, data);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("msgType", msgType)
            .add("data", data)
            .toString();
  }
}
