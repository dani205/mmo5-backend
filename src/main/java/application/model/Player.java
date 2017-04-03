package application.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Player {

  private final int playerId;

  public Player(int playerId) {
    this.playerId = playerId;
  }

  public int getPlayerId() {
    return playerId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return playerId == player.playerId;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(playerId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("playerId", playerId)
            .toString();
  }

}
