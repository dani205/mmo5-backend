package application;

import application.controller.WebSocketHandler;
import application.manager.GameManager;
import spark.Spark;

public class Mmo5Server {


  public static void main(String[] args) {
    Spark.port(getHerokuAssignedPort());
    Spark.staticFiles.location("/public"); //index.html is served at localhost:4567 (default port)
    Spark.staticFiles.expireTime(20000);
    Spark.webSocket("/mmo5", new WebSocketHandler(new GameManager()));
    Spark.get("/", (req, res) -> "MMO5 Server is Up and running!");
    Spark.init();
  }

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567;
  }


}
