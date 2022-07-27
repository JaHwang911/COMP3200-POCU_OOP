package academy.pocu.comp2500.lab11;

import academy.pocu.comp2500.lab11.pocu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.EnumSet;

public class App {
    private final ArrayList<WarehouseType> warehouseTypes;
    private WarehouseType inputWareHouseType;

    public App() {
        this.warehouseTypes = new ArrayList<>();
        this.warehouseTypes.addAll(EnumSet.allOf(WarehouseType.class));
    }

    public void run(BufferedReader in, PrintStream out, PrintStream err) {
//        this.inputWareHouseType = null;

//        while (true) {
//            InputErrorCode errCode = chooseWareHouse(in, out);
//
//            if (errCode == InputErrorCode.NONE) {
//                break;
//            } else if (errCode == InputErrorCode.EXIT) {
//                return;
//            }
//        }

        this.inputWareHouseType = WarehouseType.APPLE;
        assert (this.inputWareHouseType != null);

        User user = new User();
        SafeWallet wallet;

        try {
            wallet = new SafeWallet(user);
            // Generated exception when the user's department is HUMAN_RESOURCES
        } catch (IllegalAccessException e) {
            err.println("AUTH_ERROR");
            return;
        }

        Warehouse warehouse = new Warehouse(this.inputWareHouseType);

        while (true) {
            out.printf("BALANCE: %d%s", wallet.getAmount(), System.lineSeparator());

            InputErrorCode errorCode = chooseProduct(warehouse, wallet, in, out);

            if (errorCode == InputErrorCode.EXIT) {
                return;
            }
        }
    }

    private InputErrorCode chooseWareHouse(BufferedReader in, PrintStream out) {
        out.println("WAREHOUSE: Choose your warehouse!");

        for (int i = 0; i < this.warehouseTypes.size(); ++i) {
            out.printf("%d. %s%s", i + 1, this.warehouseTypes.get(i), System.lineSeparator());
        }

        try {
            String input = in.readLine();

            if (input.equals("exit")) {
                return InputErrorCode.EXIT;
            }

            int inputNum = Integer.parseInt(input);

            if (inputNum > this.warehouseTypes.size() || inputNum == 0) {
                return InputErrorCode.INVALID;
            }

            this.inputWareHouseType = this.warehouseTypes.get(inputNum - 1);
        } catch (IOException | NumberFormatException e) {
            return InputErrorCode.INVALID;
        }

        return InputErrorCode.NONE;
    }

    private InputErrorCode chooseProduct(Warehouse warehouse, Wallet wallet, BufferedReader in, PrintStream out) {
        out.println("PRODUCT_LIST: Choose the product you want to buy!");

        ArrayList<Product> products = warehouse.getProducts();

        for (int i = 0; i < products.size(); ++i) {
            out.printf("%d. %-20s%2d%s", i + 1, products.get(i).getName(), products.get(i).getPrice(), System.lineSeparator());
        }

        try {
            String input = in.readLine();

            if (input.equals("exit")) {
                return InputErrorCode.EXIT;
            }

            int inputNum = Integer.parseInt(input);

            if (inputNum > products.size() || inputNum == 0) {
                return InputErrorCode.INVALID;
            }

            try {
                Product product = products.get(inputNum - 1);

                warehouse.removeProduct(product.getId());

                if (!wallet.withdraw(product.getPrice())) {
                    return InputErrorCode.INVALID;
                }
            } catch (ProductNotFoundException e) {
                return InputErrorCode.INVALID;
            }
        } catch (IOException | NumberFormatException e) {
            return InputErrorCode.INVALID;
        }

        return InputErrorCode.NONE;
    }
}
