package view;

public class TerminalDisplay {

    public void displayTerminalMenu() {
        System.out.println("""
                для добавления в магазин новой игрушки нажмите 1
                для просмотра оставшихся в магазине игрушек нажмите 2
                для розыгрыша игрушки нажмите 3
                для редактирования шанса у какой то игрушки в магазине нажмите 4
                для просмотра уже разыгранных игрушек нажмите 5
                для выхода из программы нажмите 6""");
    }
    public void displayTerminalMessage(String msg) {
        System.out.println(msg);
    }
}
