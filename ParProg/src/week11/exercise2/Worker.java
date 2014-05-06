package week11.exercise2;

import akka.actor.UntypedActor;

/**
 * Der Worker Actor berechnet das Resultat eines WorkItems und retourniert das
 * Resultat zusammen mit der description des WorkItems an den Sender.
 */
public class Worker extends UntypedActor {

    @Override
    public void onReceive(Object message) {
        if (message instanceof WorkItem) {
            WorkItem work = (WorkItem) message;
            Long result;
            try {
                result = work.call();
            }
            catch (Exception e) {
                // Alle Exceptions die beim Berechnen auftreten könnten werden
                // in eine RuntimeException verpackt und weitergeworfen.
                throw new RuntimeException(e);
            }
            final WorkItemResult workItemResult = new WorkItemResult(
                    work.getDescription(), result);
            getSender().tell(workItemResult, getSelf());
        }
    }
}