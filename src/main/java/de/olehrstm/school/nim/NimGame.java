package de.olehrstm.school.nim;

import de.olehrstm.school.nim.service.InputService;

public class NimGame {

    private final InputService inputService;
    private static final int DEFAULT_COUNT = 21;
    private static final int DEFAULT_MAX_TAKE = 3;

    private int count;
    private int maxTake;
    private int currentTake;

    public NimGame(InputService inputService) {
        this.inputService = inputService;
    }

    public void init() {
        count = inputService.input("Wie viele Hölzchen gibt es am Anfang? (Standard: " + DEFAULT_COUNT + ")", Integer.class, input -> input > 0)
                .orElseGet(() -> {
                    inputService.showDialog("Deine Eingabe ist ungültig. Der Standardwert wird verwendet.");
                    return DEFAULT_COUNT;
                });

        maxTake = inputService.input("Wie viele Hölzchen darf man maximal ziehen? (Standard: " + DEFAULT_MAX_TAKE + ")", Integer.class, input -> input > 0)
                .orElseGet(() -> {
                    inputService.showDialog("Deine Eingabe ist ungültig. Der Standardwert wird verwendet.");
                    return DEFAULT_MAX_TAKE;
                });

        inputService.showDialog("Es gibt " + count + " Hölzchen.\nMan darf maximal " + maxTake + " Hölzchen ziehen.");

        start();
    }

    private void start() {
        while (count > 0) {
            playerTick();

            if (count > 0) {
                botTick();
            } else {
                inputService.showDialog("Spieler 1 hat verloren!");
                return;
            }

            if (count <= 0) {
                inputService.showDialog("Spieler 2 hat verloren!");
            }
        }
    }

    private void playerTick() {
        inputService.input("Wie viele Hölzchen ziehst du, Spieler 1?", Integer.class, this::isValidNumber).ifPresentOrElse(take -> {
            currentTake = take;
            count -= take;

            inputService.showDialog("Es sind noch " + count + " Hölzchen übrig.");
        }, () -> inputService.showDialog("Deine Eingabe ist ungültig. Beachte, dass du maximal nur " + maxTake + " Hölzchen ziehen kannst."));
    }

    private void botTick() {
        currentTake = 4 - currentTake;
        if (!isValidNumber(currentTake)) {
            return;
        }

        count -= currentTake;
        inputService.showDialog("Der Computer zieht " + currentTake + " Hölzchen.\nEs sind noch " + count + " Hölzchen übrig.");
    }

    private boolean isValidNumber(int number) {
        return number > 0 && number <= maxTake;
    }
}
