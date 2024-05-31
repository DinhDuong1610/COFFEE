package EnCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExportToExcel {
//	public static void exportToExcel(JTable table) {
//        try {
//            // Tạo một Workbook mới
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            
//            // Tạo một Sheet mới trong Workbook
//            XSSFSheet sheet = workbook.createSheet("Data");
//            
//            // Lấy số lượng hàng và cột từ JTable
//            int rowCount = table.getRowCount();
//            int columnCount = table.getColumnCount();
//            
//            // Lặp qua các hàng và cột của JTable, ghi dữ liệu vào Workbook
//            for (int i = 0; i < rowCount; i++) {
//                XSSFRow row = sheet.createRow(i);
//                for (int j = 0; j < columnCount; j++) {
//                    XSSFCell cell = row.createCell(j);
//                    cell.setCellValue(table.getValueAt(i, j).toString());
//                }
//            }
//            
//            // Mở hộp thoại lưu file
//            JFileChooser fileChooser = new JFileChooser();
//            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
//                
//                // Ghi Workbook vào file Excel
//                FileOutputStream outputStream = new FileOutputStream(fileName);
//                workbook.write(outputStream);
//                workbook.close();
//                outputStream.close();
//                
//                System.out.println("Dữ liệu đã được xuất ra file Excel thành công!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
	
	
    public static void export(JTable table, File file) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            TableModel m = table.getModel();
            for (int i = 0; i < m.getColumnCount(); i++) {
                bw.write(m.getColumnName(i) + "\t");
            }
            bw.write("\n");
            for (int i = 0; i < m.getRowCount(); i++) {
                for (int j = 0; j < m.getColumnCount(); j++) {
                    bw.write(m.getValueAt(i, j).toString() + "\t");
                }
                bw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void exportToExcel(JTable table, File file) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            TableModel m = table.getModel();

            // Ghi header
            for (int i = 0; i < m.getColumnCount(); i++) {
                bw.write(m.getColumnName(i) + "\t");
                System.out.println(m.getColumnName(i));
            }
            bw.write("\n");

            // Ghi dữ liệu
            for (int i = 0; i < m.getRowCount(); i++) {
                for (int j = 0; j < m.getColumnCount(); j++) {
                    Object value = m.getValueAt(i, j);
                    if (value != null) {
                        bw.write(value.toString() + "\t");
                    } else {
                        bw.write("\t");
                    }
                }
                bw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public static void writeToExcell(JTable table, Path path) throws FileNotFoundException, IOException {
//        new WorkbookFactory();
//        XSSFWorkbook wb = new XSSFWorkbook(); //Excell workbook
//        XSSFSheet sheet = wb.createSheet(); //WorkSheet
//        XSSFRow row = sheet.createRow(2); //Row created at line 3
//        TableModel model = table.getModel(); //Table model
//
//
//        XSSFRow headerRow = sheet.createRow(0); //Create row at line 0
//        for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
//            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
//        }
//
//        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
//            for(int cols = 0; cols < table.getColumnCount(); cols++){ //For each table column
//                row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
//            }
//
//            //Set the row to the next one in the sequence 
//            row = sheet.createRow((rows + 3)); 
//        }
//        wb.write(new FileOutputStream(path.toString()));//Save the file     
//    }
//    
//    public static void export(JTable table, String filePath) {
//        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
//            XSSFSheet sheet = workbook.createSheet("Data");
//            TableModel model = table.getModel();
//            
//            // Tạo hàng tiêu đề
//            XSSFRow headerRow = sheet.createRow(0);
//            for (int i = 0; i < model.getColumnCount(); i++) {
//                XSSFCell cell = headerRow.createCell(i);
//                cell.setCellValue(model.getColumnName(i));
//            }
//            
//            // Ghi dữ liệu vào các hàng
//            for (int i = 0; i < model.getRowCount(); i++) {
//                XSSFRow row = sheet.createRow(i + 1);
//                for (int j = 0; j < model.getColumnCount(); j++) {
//                    XSSFCell cell = row.createCell(j);
//                    Object value = model.getValueAt(i, j);
//                    if (value != null) {
//                        cell.setCellValue(value.toString());
//                    }
//                }
//            }
//            
//            // Ghi workbook vào FileOutputStream với mã UTF-8
//            try (FileOutputStream fos = new FileOutputStream(filePath)) {
//                workbook.write(fos);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
