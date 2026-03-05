package org.lldProblems.vendingMachine;

import org.lldProblems.vendingMachine.enums.Coin;
import org.lldProblems.vendingMachine.state.VendingMachineState;

public class VendingMachineClient {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();


        vendingMachine.addItem("A1", "Coke", 25, 3);
        vendingMachine.addItem("A2", "Pepsi", 25, 2);
        vendingMachine.addItem("B1", "Water", 35, 5);

        System.out.println("--- Step 1: Select an item ---");

        vendingMachine.selectItem("B1");

        System.out.println("--- Step 2: Insert coins ---");
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.DIME);

        System.out.println("--- Step 3: Dispense item ---");
        vendingMachine.dispense();

        System.out.println("--- Step 4: Select another item ---");
        vendingMachine.selectItem("A2");

        System.out.println("--- Step 5: Insert more than needed ---");
        vendingMachine.insertCoin(Coin.QUARTER);

        System.out.println("--- Step 6: Dispense and return change ---");
        vendingMachine.dispense();
    }
}
