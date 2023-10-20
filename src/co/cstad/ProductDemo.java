package co.cstad;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.*;

public class ProductDemo {

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int currentPage = 1;
        int rowsPerPage = 3;

        productList.add(new Product(1,"Pepsi", 50,1500.0, LocalDate.now()) );
        productList.add(new Product(2,"Coca", 50,1600.0, LocalDate.now()) );
        productList.add(new Product(3,"Ize", 50,1700.0, LocalDate.now()) );
        productList.add(new Product(4,"Wurkz", 50,1800.0, LocalDate.now()) );
        productList.add(new Product(5,"Champion", 50,1800.0, LocalDate.now()) );
        productList.add(new Product(6,"Vigor", 50,1800.0, LocalDate.now()) );
        productList.add(new Product(7,"Pocari Sweat", 50,1800.0, LocalDate.now()) );
        productList.add(new Product(8,"Sting", 50,1800.0, LocalDate.now()) );
        productList.add(new Product(9,"Samurize", 50,1800.0, LocalDate.now()) );
        productList.add(new Product(10,"Olate", 50,1800.0, LocalDate.now()) );

        System.out.println("\n"+
                " ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     \n" +
                "██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    \n" +
                "██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    \n" +
                "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    \n" +
                "╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    \n" +
                " ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    \n" );
        System.out.println("#".repeat(10)+" stock management system ".toUpperCase()+"#".repeat(10)+"\n");

        do {
            Table table = new Table(9,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
            table.addCell(" ".repeat(2)+"| *)Display"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| W)rite"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| R)ead"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| U)pdate"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| D)elete"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| F)irst"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| P)revious"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| N)ext"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| L)ast"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| S)earch"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| G)o to"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| Se)t row"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| H)elp"+" ".repeat(2));
            table.addCell(" ".repeat(2)+"| E)xit"+" ".repeat(2));
            System.out.println(table.render());
            System.out.println();
            System.out.print("Command ———>\t");
            String options = sc.nextLine();
            switch (options) {
                case "*" -> display(productList, currentPage, rowsPerPage);
                case "w", "W" -> write(productList);
                case "r", "R" -> read(productList);
                case "u", "U" -> update(productList);
                case "d", "D" -> delete(productList);
                case "f", "F" -> currentPage = first(currentPage, rowsPerPage, productList);
                case "p", "P" -> currentPage = previous(currentPage, rowsPerPage, productList);
                case "n", "N" -> currentPage = next(currentPage, rowsPerPage, productList);
                case "l", "L" -> currentPage = last(currentPage, rowsPerPage, productList);
                case "s", "S" -> search(productList, currentPage, rowsPerPage);
                case "g", "G" -> currentPage = goTo(currentPage, rowsPerPage, productList);
                case "se", "Se" -> rowsPerPage = setRow(productList);
                case "h", "H" -> help();
                case "e", "E" -> {
                    System.out.println("\n" +
                            " _______                 __      __                                    __                           __      __              __    \n" +
                            "|     __|.-----.-----.--|  |    |  |--.--.--.-----.    .---.-.-----.--|  |    .-----.-----.-----.--|  |    |  |.--.--.----.|  |--.\n" +
                            "|    |  ||  _  |  _  |  _  |    |  _  |  |  |  -__|    |  _  |     |  _  |    |  _  |  _  |  _  |  _  |    |  ||  |  |  __||    < \n" +
                            "|_______||_____|_____|_____|    |_____|___  |_____|    |___._|__|__|_____|    |___  |_____|_____|_____|    |__||_____|____||__|__|\n" +
                            "                                      |_____|                                 |_____|                                             \n");
                    System.exit(0);
                }
                default -> {
                    String[] shortCut = options.split("#");
                    switch ( shortCut[0] ) {
                        case "w", "W" -> {
                            try {
                                Product lastProduct = productList.get(productList.size() - 1);
                                int proID = lastProduct.getProductCode() + 1;

                                String[] afterShortCut = shortCut[1].split("-");

                                if (afterShortCut.length != 3) {
                                    System.out.println("Invalid format for adding a product. Use 'w#Name-Price-Quantity'.");
                                    break;
                                }

                                String newName = afterShortCut[0];
                                double newPrice = Double.parseDouble(afterShortCut[1]);
                                int newQty = Integer.parseInt(afterShortCut[2]);

                                Product proD = new Product(proID, newName, newQty, newPrice, LocalDate.now());

                                Table tb = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                tb.addCell(" ID            : " + proID + " ".repeat(10));
                                tb.addCell(" Name          : " + proD.getProductName() + " ".repeat(10));
                                tb.addCell(" Unit price    : " + proD.getPrice() + " ".repeat(10));
                                tb.addCell(" Qty           : " + proD.getQty() + " ".repeat(10));
                                tb.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                                System.out.println(tb.render());

                                System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                                String yn = sc.nextLine();
                                switch (yn) {
                                    case "y","Y" -> {
                                        productList.add(proD);
                                        Table tbl = new Table(1, BorderStyle.DESIGN_CAFE);
                                        tbl.addCell("  Product with ID [" + proID + "] added successfully  ");
                                        System.out.println(tbl.render());
                                    }
                                    case "n","N" -> {
                                        Table tbl = new Table(1, BorderStyle.DESIGN_CAFE);
                                        tbl.addCell("  Product with ID [" + proID + "] is not added  ");
                                        System.out.println(tbl.render());
                                    }
                                    default -> System.out.println("Invalid response. The product was not added.");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        case "r","R" -> {

                            try {

                                int proID = Integer.parseInt(shortCut[1]);
                                boolean isFound = false;
                                for (Product product : productList) {
                                    if ( product.getProductCode().equals(proID) ) {
                                        Table tb = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                                        tb.addCell(" ID            : "+proID+" ".repeat(10));
                                        tb.addCell(" Name          : "+product.getProductName()+" ".repeat(10));
                                        tb.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                                        tb.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                                        tb.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                                        System.out.println(tb.render());
                                        isFound = true;
                                        break;
                                    }
                                }
                                if (!isFound){
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with ID : "+shortCut[1]+" is not found  ");
                                    System.out.println(tb.render());
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        case "d","D" -> {

                            try {
                                int proID = Integer.parseInt(shortCut[1]);
                                boolean isFound = false;
                                for (Product product : productList) {
                                    if (product.getProductCode().equals(proID)) {
                                        Table tbl = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                                        tbl.addCell(" ID            : "+proID+" ".repeat(10));
                                        tbl.addCell(" Name          : "+product.getProductName()+" ".repeat(10));
                                        tbl.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                                        tbl.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                                        tbl.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                                        System.out.println(tbl.render());
                                        System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                                        String opt = sc.nextLine();
                                        switch (opt) {
                                            case "y","Y" -> {
                                                productList.remove(product);
                                                Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                                tb.addCell("  Product with ID " + proID + " is deleted  ");
                                                System.out.println(tb.render());
                                            }
                                            case "n","N" -> {
                                                Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                                tb.addCell("  Product with ID " + proID + " is removed  ");
                                                System.out.println(tb.render());
                                            }
                                            default -> System.out.println("Invalid options");
                                        }
                                        isFound = true;
                                        break;
                                    }
                                }
                                if (!isFound) {
                                    System.out.println("Invalid options");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        default -> System.err.println("Invalid Options");
                    }
                }
            }
        }while (true);
    }
    public static void display(List<Product> productList, int currentPage, int rowsPerPage) {
        int startIndex = (currentPage - 1) * rowsPerPage;
        int endIndex = Math.min(startIndex + rowsPerPage, productList.size());

        Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        tableDisplay.addCell("    " + "ID"            + "    ");
        tableDisplay.addCell("    " + "Name"          + "    ");
        tableDisplay.addCell("    " + "Unit Price"    + "    ");
        tableDisplay.addCell("    " + "Qty"           + "    ");
        tableDisplay.addCell("    " + "Imported Date" + "    ");

        for (int i = startIndex; i < endIndex; i++) {
            Product product = productList.get(i);
            tableDisplay.addCell("    " + product.getProductCode().toString() +"    ");
            tableDisplay.addCell("    " + product.getProductName()            +"    ");
            tableDisplay.addCell("    " + product.getPrice().toString()       +"    ");
            tableDisplay.addCell("    " + product.getQty().toString()         +"    ");
            tableDisplay.addCell("    " + product.getImportDate().toString()  +"    ");
        }

        System.out.println(tableDisplay.render());

        Table tableForPage = new Table(1,BorderStyle.DESIGN_CURTAIN_WIDE,ShownBorders.SURROUND);
        int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);
        tableForPage.addCell("  Page " + currentPage + " of " + totalPages + " ".repeat(40) + "Total records : " + productList.size()+"  ");
        System.out.println(tableForPage.render());
    }
    public static void write(List<Product> productList){

        Product lastProduct = productList.get(productList.size() - 1);
        Integer productID = lastProduct.getProductCode()+1;

        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Product ID       : "+productID+"\n");
            System.out.print("Product name     : ");
            String productName = sc.nextLine();
            System.out.print("Product Price    : ");
            Double productPrice = Double.parseDouble(sc.nextLine());
            System.out.print("Product Quantity : ");
            Integer productQty = parseInt(sc.nextLine());

            Product product = new Product(productID, productName, productQty, productPrice, LocalDate.now());
            productList.add(product);

            do {
                Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                table.addCell(" ID            : "+productID+" ".repeat(10));
                table.addCell(" Name          : "+productName+" ".repeat(10));
                table.addCell(" Unit price    : "+productPrice+" ".repeat(10));
                table.addCell(" Qty           : "+productQty+" ".repeat(10));
                table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                System.out.println(table.render());
                System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                String options = sc.nextLine();
                switch (options) {
                    case "y","Y" -> {
                        Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                        tb.addCell("  Product with id ["+productID+"] is added successfully  ");
                        System.out.println(tb.render());
                        return;
                    }
                    case "n","N" -> {
                        productList.remove(product);
                        Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                        tb.addCell("  Product with id ["+productID+"] is not added  ");
                        System.out.println(tb.render());
                        return;
                    }
                    default -> System.err.println("Invalid options. Choose [y/Y] & [n/N] : ");
                }
            }while (true);
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        }
    }
    public static void read(List<Product> productList){

        Scanner sc = new Scanner(System.in);
        boolean isFound = false;

        try {
            System.out.print("Read by ID : ");
            Integer productID = parseInt(sc.nextLine());
            for (Product product : productList) {
                if (product.getProductCode().equals(productID)) {
                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                    table.addCell(" ID            : "+productID+" ".repeat(10));
                    table.addCell(" Name          : "+product.getProductName()+" ".repeat(10));
                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                    System.out.println(table.render());
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                tb.addCell("  Product with ID : "+productID+" is not found  ");
                System.out.println(tb.render());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void update(List<Product> productList){

        Scanner sc = new Scanner(System.in);
        boolean isFound = false;

        try {
            System.out.print("Enter ID to update : ");
            Integer idToUpdate = parseInt(sc.nextLine());

            for (Product product : productList) {
                if (product.getProductCode().equals(idToUpdate)) {
                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                    table.addCell(" ID            : "+idToUpdate+" ".repeat(10));
                    table.addCell(" Name          : "+product.getProductName()+" ".repeat(10));
                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                    System.out.println(table.render());
                    isFound = true;
                    break;
                }
            }


            if (!isFound) {
                System.out.println("Product with ID : " + idToUpdate + " is not found");
                return;
            }

            System.out.println("  What do you want to update?");
            Table tableToUpdate = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND);
            tableToUpdate.addCell(" ".repeat(2)+"1. All"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"2. Name"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"3. Quantity"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"4. Price"+" ".repeat(2));
            tableToUpdate.addCell(" ".repeat(2)+"5. Back to menu"+" ".repeat(2));
            System.out.println(tableToUpdate.render());
            try {
                Product updateProduct = new Product() ;
                System.out.print("Choose option (1-5) : ");
                int op = parseInt(sc.nextLine());
                switch (op) {
                    case 1 -> {
                        try {
                            System.out.print("Enter new product name: ");
                            String newProductName = sc.nextLine();
                            System.out.print("Enter new quantity: ");
                            Integer newQuantity = parseInt(sc.nextLine());
                            System.out.print("Enter new price: ");
                            Double newPrice = Double.parseDouble(sc.nextLine());

                            updateProduct.setProductName(newProductName);
                            updateProduct.setQty(newQuantity);
                            updateProduct.setPrice(newPrice);
                            updateProduct.setProductCode(idToUpdate);

                            Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                            updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                            updatedTable.addCell(" Name          : " + newProductName + " ".repeat(10));
                            updatedTable.addCell(" Unit price    : " + newPrice + " ".repeat(10));
                            updatedTable.addCell(" Qty           : " + newQuantity + " ".repeat(10));
                            updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                            System.out.println(updatedTable.render());

                            System.out.print("Are you sure to update this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y", "Y" -> {
                                    productList.forEach(product -> {
                                        if( product.getProductCode().equals(idToUpdate) ){
                                            updateProduct.setImportDate(  product.getImportDate() );
                                            productList.set( productList.indexOf(product), updateProduct );
                                        }
                                    });
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is updated successfully  ");
                                    System.out.println(tb.render());
                                }
                                case "n", "N" -> {
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is not updated  ");
                                    System.out.println(tb.render());
                                }
                                default -> System.out.println("Invalid options.");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }


                    case 2 -> {
                        try {
                            System.out.print("Enter new product name : ");
                            String newProductName = sc.nextLine();

                            updateProduct.setProductName(newProductName);
                            for (Product product : productList) {
                                if (product.getProductCode().equals(idToUpdate)) {
                                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                                    table.addCell(" ID            : "+idToUpdate+" ".repeat(10));
                                    table.addCell(" Name          : "+newProductName+" ".repeat(10));
                                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                                    System.out.println(table.render());
                                    break;
                                }
                            }
                            System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y", "Y" -> {
                                    productList.forEach(product -> {
                                        if( product.getProductCode().equals(idToUpdate) ){
                                            updateProduct.setImportDate(  product.getImportDate() );
                                            updateProduct.setProductCode(product.getProductCode() );
                                            updateProduct.setPrice(  product.getPrice() );
                                            updateProduct.setQty(  product.getQty() );
                                            productList.set( productList.indexOf(product), updateProduct );
                                        }
                                    });
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is updated successfully  ");
                                    System.out.println(tb.render());
                                }
                                case "n", "N" -> {
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is not updated  ");
                                    System.out.println(tb.render());
                                }
                                default -> System.out.println("Invalid options.");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case 3 -> {
                        try {
                            System.out.print("Enter new product Price : ");
                            Double newProductPrice = Double.parseDouble(sc.nextLine());

                            updateProduct.setPrice(newProductPrice);
                            for (Product product : productList) {
                                if (product.getProductCode().equals(idToUpdate)) {
                                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                                    table.addCell(" ID            : "+idToUpdate+" ".repeat(10));
                                    table.addCell(" Name          : "+product.getProductName()+" ".repeat(10));
                                    table.addCell(" Unit price    : "+newProductPrice+" ".repeat(10));
                                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                                    System.out.println(table.render());
                                    break;
                                }
                            }
                            System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y", "Y" -> {
                                    productList.forEach(product -> {
                                        if( product.getProductCode().equals(idToUpdate) ){
                                            updateProduct.setImportDate(  product.getImportDate() );
                                            updateProduct.setProductCode(product.getProductCode() );
                                            updateProduct.setQty(  product.getQty() );
                                            updateProduct.setProductName(  product.getProductName() );
                                            productList.set( productList.indexOf(product), updateProduct );
                                        }
                                    });
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is updated successfully  ");
                                    System.out.println(tb.render());
                                }
                                case "n", "N" -> {
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is not updated  ");
                                    System.out.println(tb.render());
                                }
                                default -> System.out.println("Invalid options.");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case 4 -> {
                        try {
                            System.out.print("Enter new product Qty : ");
                            Integer newProductQty = parseInt(sc.nextLine());

                            updateProduct.setQty(newProductQty);
                            for (Product product : productList) {
                                if (product.getProductCode().equals(idToUpdate)) {
                                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                                    table.addCell(" ID            : "+idToUpdate+" ".repeat(10));
                                    table.addCell(" Name          : "+product.getProductName()+" ".repeat(10));
                                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                                    table.addCell(" Qty           : "+newProductQty+" ".repeat(10));
                                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                                    System.out.println(table.render());
                                    break;
                                }
                            }
                            System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                            String options = sc.nextLine();
                            switch (options) {
                                case "y", "Y" -> {
                                    productList.forEach(product -> {
                                        if( product.getProductCode().equals(idToUpdate) ){
                                            updateProduct.setImportDate(  product.getImportDate() );
                                            updateProduct.setProductCode(product.getProductCode() );
                                            updateProduct.setPrice(  product.getPrice() );
                                            updateProduct.setProductName(  product.getProductName() );
                                            productList.set( productList.indexOf(product), updateProduct );
                                        }
                                    });
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is updated successfully  ");
                                    System.out.println(tb.render());
                                }
                                case "n", "N" -> {
                                    Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                                    tb.addCell("  Product with id ["+idToUpdate+"] is not updated  ");
                                    System.out.println(tb.render());
                                }
                                default -> System.out.println("Invalid options.");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    case 5 -> {
                        Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                        tb.addCell("  Back to menu ———>  ");
                        System.out.println(tb.render());
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + op);
                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
    public static void delete(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the ID of the product you want to delete: ");
            Integer productId = parseInt(scanner.nextLine());
            for (Product product : productList) {
                if (product.getProductCode().equals(productId)) {
                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                    table.addCell(" ID            : "+product.getProductCode()+" ".repeat(10));
                    table.addCell(" Name          : "+product.getProductName()+" ".repeat(10));
                    table.addCell(" Unit price    : "+product.getPrice()+" ".repeat(10));
                    table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                    table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                    System.out.println(table.render());
                    System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                    String options = scanner.nextLine();
                    switch (options) {
                        case "y","Y" -> {
                            productList.remove(product);
                            Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                            tb.addCell("  Product with ID " + productId + " is deleted  ");
                            System.out.println(tb.render());
                            return;
                        }
                        case "n","N" -> {
                            Table tb = new Table(1,BorderStyle.DESIGN_CAFE);
                            tb.addCell("  Product with ID " + productId + " is removed  ");
                            System.out.println(tb.render());
                            return;
                        }
                        default -> System.out.println("Invalid options");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static int first(int currentPage, int rowsPerPage, List<Product> productList) {
        if (currentPage == 1) {
            System.out.println("You are already on the first page.");
        } else {
            currentPage = 1;
            display(productList, currentPage, rowsPerPage);
        }
        return currentPage;
    }
    public static int previous(int currentPage, int rowsPerPage, List<Product> productList) {
        if (currentPage > 1) {
            currentPage--;
        } else {
            currentPage = (int) Math.ceil((double) productList.size() / rowsPerPage);
        }
        display(productList, currentPage, rowsPerPage);
        return currentPage;
    }
    public static int next(int currentPage, int rowsPerPage, List<Product> productList) {
        int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
        } else {
            currentPage = 1;
        }
        display(productList, currentPage, rowsPerPage);
        return currentPage;
    }
    public static int last(int currentPage, int rowsPerPage, List<Product> productList) {
        int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);
        if (currentPage == totalPages) {
            System.out.println("You are already on the last page.");
        } else {
            currentPage = totalPages;
            display(productList, currentPage, rowsPerPage);
        }
        return currentPage;
    }
    public static void search(List<Product> productList, int currentPage, int rowsPerPage) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("  Search product by keyword : ");
        String searchKeyword = scanner.nextLine().toLowerCase();

        List<Product> matchingProducts = new ArrayList<>();

        for (Product product : productList) {
            String productName = product.getProductName().toLowerCase();

            if (productName.contains(searchKeyword)) {
                matchingProducts.add(product);
            }
        }

        int totalPages = (int) Math.ceil((double) matchingProducts.size() / rowsPerPage);
        if (matchingProducts.isEmpty()) {
            System.out.println("No products found containing the keyword '" + searchKeyword + "'.");
        } else {
            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > totalPages) {
                currentPage = totalPages;
            }

            int startIndex = (currentPage - 1) * rowsPerPage;
            int endIndex = Math.min(startIndex + rowsPerPage, matchingProducts.size());

            Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
            tableDisplay.addCell(" ".repeat(2) + "ID" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Name" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Unit Price" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Qty" + " ".repeat(2));
            tableDisplay.addCell(" ".repeat(2) + "Imported Date" + " ".repeat(2));

            for (int i = startIndex; i < endIndex; i++) {
                Product product = matchingProducts.get(i);
                tableDisplay.addCell(" ".repeat(2) + product.getProductCode().toString());
                tableDisplay.addCell(" ".repeat(2) + product.getProductName());
                tableDisplay.addCell(" ".repeat(2) + product.getPrice().toString());
                tableDisplay.addCell(" ".repeat(2) + product.getQty().toString());
                tableDisplay.addCell(" ".repeat(2) + product.getImportDate().toString());
            }

            System.out.println(tableDisplay.render());
            Table tableSearch = new Table(1,BorderStyle.DESIGN_CURTAIN_WIDE,ShownBorders.SURROUND );
            tableSearch.addCell("Page " + currentPage + " of " + totalPages + " ".repeat(40) + "Total matching products: " + matchingProducts.size());
            System.out.println(tableSearch.render());
        }
    }
    public static int goTo(int currentPage, int rowsPerPage, List<Product> productList) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the page number you want to go to : ");
            int targetPage = parseInt(scanner.nextLine());
            int totalPages = (int) Math.ceil((double) productList.size() / rowsPerPage);

            if (targetPage >= 1 && targetPage <= totalPages) {
                currentPage = targetPage;
                display(productList, currentPage, rowsPerPage);
            } else {
                System.out.println("Invalid page number. Please enter a page number between 1 and " + totalPages + ".");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return currentPage;
    }
    public static int setRow(List<Product> productList) {
        Scanner sc = new Scanner(System.in);
        int r;
        try {
            System.out.print("  Enter number of row(s) you want to display :  ");
            int numRows = Integer.parseInt(sc.nextLine());
            if ( numRows > 0 && numRows <= productList.size()) {
                 r = numRows;
                 Table table = new Table(1,BorderStyle.DESIGN_CAFE) ;
                 table.addCell("  Number of row(s) : ["+r+"] is set successful  ");
                System.out.println(table.render());
            } else {
                r = productList.size();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            r = 3;
        }
        return r;
    }
    public static void help(){
        Table tableShowHelp = new Table(1,BorderStyle.CLASSIC_COMPATIBLE_LIGHT_WIDE,ShownBorders.SURROUND);
        tableShowHelp.addCell("1. \tPress\t * : Display all record of products");
        tableShowHelp.addCell("2. \tPress\t w : Add new product");
        tableShowHelp.addCell("   \tPress\t w ->#proName-unitPrice-qty : shortcut to add new product");
        tableShowHelp.addCell("3. \tPress\t r : Read Content any content");
        tableShowHelp.addCell("   \tPress\t r ->#proID : shortcut to read product by ID");
        tableShowHelp.addCell("4. \tPress\t u : Update data");
        tableShowHelp.addCell("5. \tPress\t d : Delete data");
        tableShowHelp.addCell("   \tPress\t d ->#proID : shortcut to delete product by ID");
        tableShowHelp.addCell("6. \tPress\t f : Display first page");
        tableShowHelp.addCell("7. \tPress\t p : Display previous page");
        tableShowHelp.addCell("8. \tPress\t n : Display next page");
        tableShowHelp.addCell("9. \tPress\t l : Display last page");
        tableShowHelp.addCell("10.\tPress\t s : Search product by name");
        tableShowHelp.addCell("11.\tPress\t h : Help");
        System.out.println(tableShowHelp.render());
    }
}
