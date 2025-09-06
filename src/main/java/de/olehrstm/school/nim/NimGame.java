package de.olehrstm.school.nim;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class NimGame {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            /*log.info("Wie viele Hölzchen gibt es am Anfang? (Standard: 21)");
            int count = input.nextInt();
            log.info("Es gibt {} Hölzchen.", count);*/
            int count = 21;

            /*log.info("Wie viele Hölzchen darfst du maximal ziehen? (Standard: 3)");
            int maxTake = input.nextInt();
            log.info("Du darfst maximal {} Hölzchen ziehen.", maxTake);*/

            while (count > 0) {
                log.info("Wie viele Hölzchen ziehst du, Spieler 1?");
                int take = input.nextInt();
                /*if (take > maxTake) {
                    log.warn("Du darfst nur maximal {} Hölzchen ziehen!", maxTake);
                    continue;
                }*/
                count -= take;
                log.info("Es sind noch {} Hölzchen übrig.", count);

                if (count > 0) {
                    take = 4 - take;
                    /*if (take > maxTake) {
                        log.warn("Du darfst nur maximal {} Hölzchen ziehen!", maxTake);
                        continue;
                    }*/
                    count -= take;
                    log.info("Der Computer zieht {} Hölzchen.", take);
                    log.info("Es sind noch {} Hölzchen übrig.", count);

                    if (count <= 0) {
                        log.info("Spieler 2 hat verloren!");
                    }
                } else {
                    log.info("Spieler 1 hat verloren!");
                }
            }
        } catch (Exception e) {
            log.error("Ein fehler ist aufgetreten: {}", e.getMessage());
        }
    }
}
