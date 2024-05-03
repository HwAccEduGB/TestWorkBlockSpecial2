package present;

import model.ChildsWorldShop;
import model.Toy;
import util.Functions;
import view.TerminalDisplay;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Present {
    ChildsWorldShop shop;
    Functions func;
    TerminalDisplay display;
    Scanner scanner;
    private List<Toy> raffledToys;

    public Present() {
        this.raffledToys = new ArrayList<>();
        this.shop = new ChildsWorldShop();
        this.func = new Functions();
        this.display = new TerminalDisplay();
        this.scanner = new Scanner(System.in);
        createDefaultSet();
    }

    public void run() {
        display.displayTerminalMessage("Добро пожаловать в программу Розыгрыш игрушек в магазине " + shop.getName());
        display.displayTerminalMenu();
        int menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case (1) -> {
                addNewToy();
                continuable();
            }
            case (2) -> {
                viewAllToysInShop();
                continuable();
            }
            case (3) -> {
                toyRaffle();
                continuable();
            }
            case (4) -> {
                editDropChanceToy();
                continuable();
            }
            case (5) -> {
                viewAllRaffleToys();
                continuable();
            }
            case (6) -> {
                saveRaffleToysToFile();
                scanner.close();
                System.exit(0);
            }
            default -> {
                display.displayTerminalMessage("Введена неверная команда");
                continuable();
            }
        }
    }

    private void addNewToy() {
        shop.getToys().add(new Toy(func.generateID(), choiceName(), func.generateChance()));
    }

    private void editDropChanceToy() {
        String currentNameToy = choiceName();
        for (Toy elem : shop.getToys()) {
            if (elem.getNameToy().equalsIgnoreCase(currentNameToy)) {
                display.displayTerminalMessage("Введите новый шанс выпадения игрушки от 1 до 99");
                int newChance = scanner.nextInt();
                if (newChance < 100 & newChance > 0) {
                    elem.setChance(newChance);
                } else {
                    display.displayTerminalMessage("Введены несоответствующие данные");
                }
            }
        }
    }

    private void viewAllToysInShop() {
        if (!shop.getToys().isEmpty()) {
            for (Toy elem : shop.getToys()) {
                display.displayTerminalMessage(elem.toString());
            }
        }else {
            display.displayTerminalMessage("Отсутствуют игрушки для розыгрыша");
        }


    }

    private void viewAllRaffleToys() {
        if (!raffledToys.isEmpty()){
            for (Toy elem : raffledToys) {
                display.displayTerminalMessage(elem.toString());
            }
        }else {
            display.displayTerminalMessage("Ни одной игрушки пока не разыграно");
        }
    }

    private void toyRaffle() {
        int rnd = func.generateChance();
        String currenNameToy = "";
        int minDiff = 100;
        Toy currentToy = null;
        if (!shop.getToys().isEmpty()) {
            for (Toy elem : shop.getToys()) {
                if (elem.getChance() - rnd < minDiff) {
                    minDiff = elem.getChance() - rnd;
                    currenNameToy = elem.getNameToy();
                }
            }
            for (Toy elem : shop.getToys()) {
                if (elem.getNameToy().equalsIgnoreCase(currenNameToy)) {
                    raffledToys.add(elem);
                    display.displayTerminalMessage("Вы выиграли игрушку " + elem.getNameToy());
                    currentToy = elem;
                }
            }
            shop.getToys().remove(currentToy);
        }else {
            display.displayTerminalMessage("Все игрушки разыграны, приходите завтра!");
        }

    }

    private String choiceName() {
        display.displayTerminalMessage("Введите название игрушки");
        return scanner.next();
    }

    private void createDefaultSet() {
        shop.getToys().add(new Toy(func.generateID(), "Bear", func.generateChance()));
        shop.getToys().add(new Toy(func.generateID(), "Doll", func.generateChance()));
        shop.getToys().add(new Toy(func.generateID(), "Car", func.generateChance()));
        shop.getToys().add(new Toy(func.generateID(), "Rabbit", func.generateChance()));
        shop.getToys().add(new Toy(func.generateID(), "Ball", func.generateChance()));
    }

    private void saveRaffleToysToFile(){
        display.displayTerminalMessage("Хотите сохранить результаты игры? Y/N");
        String wantSave = scanner.next();
        if (wantSave.equalsIgnoreCase("y")) {
            saveFile();
        } else if (wantSave.equalsIgnoreCase("n")) {
            scanner.close();
            System.exit(0);
        }else {
            display.displayTerminalMessage("Указан не понятный выбор");
            saveRaffleToysToFile();
        }
    }

    private void saveFile(){
        display.displayTerminalMessage("Введите желаемое имя файла");
        String newFileName = scanner.next();
        File newFile = new File(newFileName + ".json");
        try {
            if (newFile.createNewFile()) {
                func.addData(newFile, raffledToys);
                display.displayTerminalMessage("Результаты сохранены в файле c именем: " + newFile);
                scanner.close();
                System.exit(0);
            } else {
                display.displayTerminalMessage("файл c именем: " + newFile + " уже существует");
                saveFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void continuable() {
        display.displayTerminalMessage("Хотите продолжить? Y/N");
        String wantContinue = scanner.next();
        if (wantContinue.equalsIgnoreCase("y")) {
            run();
        } else if (wantContinue.equalsIgnoreCase("n")) {
            saveRaffleToysToFile();
            scanner.close();
            System.exit(0);
        }else {
            display.displayTerminalMessage("Указан не понятный выбор");
            continuable();
        }
    }
}
