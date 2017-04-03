package application.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class PlayerMove {
  private final int playerId;
  private final int posX;
  private final int posY;

  public PlayerMove(int playerId, int posX, int posY) {
    this.playerId = playerId;
    this.posX = posX;
    this.posY = posY;
  }

  public int getPlayerId() {
    return playerId;
  }

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlayerMove that = (PlayerMove) o;
    return playerId == that.playerId &&
            posX == that.posX &&
            posY == that.posY;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(playerId, posX, posY);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("playerId", playerId)
            .add("posX", posX)
            .add("posY", posY)
            .toString();
  }
}
