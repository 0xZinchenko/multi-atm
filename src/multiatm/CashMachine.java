package multiatm;

public class CashMachine {
    public static void main(String[] args) {
        String currencyCode = ConsoleHelper.askCurrencyCode();

        String[] digits = ConsoleHelper.getValidTwoDigits(currencyCode);
        int denomination = Integer.parseInt(digits[0]);
        int count = Integer.parseInt(digits[1]);

        CurrencyManipulator manipulatorByCurrencyCode = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        manipulatorByCurrencyCode.addAmount(denomination, count);
        System.out.println(manipulatorByCurrencyCode.getTotalAmount());
    }
}
