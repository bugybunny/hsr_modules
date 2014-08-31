package week11.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

  public static void main(String[] args) throws IOException {

    ActorSystem system = ActorSystem.create("CalculatorService");

    ActorRef manager = system.actorOf(
        Props.create(WorkerManager.class, 4 /* Worker */), "WorkerManager");

    ActorRef calculator = system.actorOf(
        Props.create(Calculator.class, manager), "Calculator");

    final BufferedReader in = new BufferedReader(new InputStreamReader(
        System.in));
    while (in.readLine() != null) {
      // Der Calculator soll ein neues WorkItem schedulen
      calculator.tell(new Calculator.Calculate(), ActorRef.noSender());
    }

    system.shutdown();
  }
}
