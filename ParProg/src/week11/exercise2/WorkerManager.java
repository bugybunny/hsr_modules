package week11.exercise2;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;

/**
 * Die Klasse WorkerManager nimmt Schedule-Messages entgegen und verteilt die
 * Arbeit auf eine fixe Anzahl Worker-Actors. Der Manager merkt sich dabei,
 * welche Worker gerade beschäftigt sind und welche nichts zu tun haben.
 */
public class WorkerManager extends UntypedActor {

    public static class Schedule {
        private final WorkItem work;

        public Schedule(WorkItem work) {
            this.work = work;
        }

        public WorkItem getWork() {
            return work;
        }
    }

    public static class NoWorkersAvailable {
    }

    /**
     * Eine Queue der unbeschäftigten Worker.
     */
    private final Deque<ActorRef>                                   idle    = new LinkedList<>();

    /**
     * Zusätzlich zu den beschäftigten Worker merken wir uns auch den
     * ursprünglichen "Auftraggeber" des WorkItems, damit wir diesem das
     * Resultat zurückschicken können:
     */
    private final Map<ActorRef /* Worker */, ActorRef /* Sender */> working = new HashMap<>();

    public WorkerManager(int numberOfWorkers) {
        for (int i = 1; i <= numberOfWorkers; i++) {
            ActorRef worker = getContext().system().actorOf(
                    Props.create(Worker.class).withDispatcher(
                            "pinned-dispatcher"), "W" + i);
            idle.add(worker);
        }
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Schedule && idle.isEmpty()) {
            getSender().tell(new NoWorkersAvailable(), getSelf());
        } else if (message instanceof Schedule) {
            ActorRef worker = idle.poll();
            working.put(worker, getSender());
            worker.tell(((Schedule) message).getWork(), getSelf());

        } else if (message instanceof WorkItemResult) {
            ActorRef originalSender = working.remove(getSender());
            idle.offer(getSender());
            originalSender.tell(message, getSelf());
        }
    }

    protected void childHasCrashed(ActorRef sender) {
        // Der Worker wurde bereits neu gestartet, wir müssen ihn aber noch
        // zusätzlich von der idle in die working Liste umteilen.
        working.remove(sender);
        idle.add(sender);
    }

    /**
     * Konfiguration für das Supervisor-Verhalten.
     * 
     * Egal was fehlschlägt, wir starten unsere Workers einfach neu
     * (SupervisorStrategy.restart()). Man könnte hier aber auch Aufgrund der
     * geworfenene Exception eine andere Strategie implementieren.
     */
    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(-1 /* Restart forever */, Duration.Inf(),
                new Function<Throwable, Directive>() {
                    @Override
                    public Directive apply(Throwable t) {
                        childHasCrashed(getSender());
                        return SupervisorStrategy.restart();
                    }
                });
    }

}