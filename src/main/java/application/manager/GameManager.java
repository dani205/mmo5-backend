package application.manager;

import application.model.*;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GameManager {

  private final Gson gson;
  private final AtomicInteger playerCounter;
  private final Map<Session, Player> sessionPlayerMap;
  private final BoardGame boardGame;

  public GameManager() {
    this.sessionPlayerMap = Maps.newConcurrentMap();
    this.playerCounter = new AtomicInteger(0);
    this.gson = new Gson();
    this.boardGame = new BoardGame();
  }

  public void handleIncomingMessage(Session session, String msgJson) {
    Message<?> msg = this.gson.fromJson(msgJson, Message.class);
    switch (msg.getMsgType()) {
      case PlayerMove:
        handlePlayerMove((PlayerMove) msg.getData(), session);
        break;
    }
  }

  public void handleLoginPlayer(Session session) {
    Player player = new Player(this.playerCounter.incrementAndGet());
    this.sessionPlayerMap.put(session, player);
    sendMessage(session, new Message<Player>(MsgType.PlayerLogin, player));
  }

  public void handleLogoutPlayer(Session session) {
    Player loggedOutPlayer = this.sessionPlayerMap.remove(session);
    Message<PlayerLoggedOut> playerLoggedOutMessage = new Message<>(MsgType.PlayerLoggedOut, new PlayerLoggedOut(loggedOutPlayer));
    broadcastMessage(playerLoggedOutMessage);
  }

  private void broadcastMessage(Message<?> message) {
    for (Session playerSession : this.sessionPlayerMap.keySet()) {
      sendMessage(playerSession, message);
    }
  }

  private void sendMessage(Session session, Message<?> message) {
    try {
      session.getRemote().sendString(this.gson.toJson(message));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handlePlayerMove(PlayerMove playerMove, Session session) {
    updateBoardGame(playerMove);
    Message playerLoggedOutMessage = new Message<>(MsgType.PlayerMove, playerMove);
    broadcastMessage(playerLoggedOutMessage);
  }

  private void updateBoardGame(PlayerMove playerMove) {
    // TODO: this.
  }
}
