package de.olehrstm.school.nim;

import de.olehrstm.school.nim.service.InputService;

public class NimGame {

    private static final int DEFAULT_COUNT = 21;
    private static final int DEFAULT_MAX_TAKE = 3;
    private final InputService inputService;
    private int count;
    private int maxTake;
    private int currentTake;

    public NimGame(InputService inputService) {
        this.inputService = inputService;
    }

    public void init() {
        this.count = this.inputService.input("Wie viele Hölzchen gibt es am Anfang? (Standard: " + DEFAULT_COUNT + ")", Integer.class, input -> input > 0)
                .orElseGet(() -> {
                    this.inputService.showDialog("Deine Eingabe ist ungültig. Der Standardwert wird verwendet.");
                    return DEFAULT_COUNT;
                });

        this.maxTake = this.inputService.input("Wie viele Hölzchen darf man maximal ziehen? (Standard: " + DEFAULT_MAX_TAKE + ")", Integer.class, input -> input > 0)
                .orElseGet(() -> {
                    this.inputService.showDialog("Deine Eingabe ist ungültig. Der Standardwert wird verwendet.");
                    return DEFAULT_MAX_TAKE;
                });

        this.inputService.showDialog("Es gibt " + this.count + " Hölzchen.\nMan darf maximal " + this.maxTake + " Hölzchen ziehen.");

        start();
    }

    private void start() {
        while (this.count > 0) {
            playerTick();

            if (this.count > 0) {
                botTick();
            } else {
                this.inputService.showDialog("Spieler 1 hat verloren!");
                return;
            }

            if (this.count <= 0) {
                this.inputService.showDialog("Spieler 2 hat verloren!");
            }
        }
    }

    private void playerTick() {
        this.inputService.input("Wie viele Hölzchen ziehst du, Spieler 1?", Integer.class, this::isValidNumber).ifPresentOrElse(take -> {
            this.currentTake = take;
            this.count -= take;

            this.inputService.showDialog("Es sind noch " + this.count + " Hölzchen übrig.");
        }, () -> this.inputService.showDialog("Deine Eingabe ist ungültig. Beachte, dass du maximal nur " + this.maxTake + " Hölzchen ziehen kannst."));
    }

    private void botTick() {
        this.currentTake = 4 - this.currentTake;
        if (!isValidNumber(this.currentTake)) {
            return;
        }

        this.count -= this.currentTake;
        this.inputService.showDialog("Der Computer zieht " + this.currentTake + " Hölzchen.\nEs sind noch " + this.count + " Hölzchen übrig.");
    }

    private boolean isValidNumber(int number) {
        return number > 0 && number <= this.maxTake;
    }
}
