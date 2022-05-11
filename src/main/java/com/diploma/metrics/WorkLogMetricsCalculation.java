package com.diploma.metrics;

import com.diploma.dao.implementation.WorkLogDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import com.diploma.models.WorkLog;
import com.diploma.models.enumeration.WorkLogAction;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

public class WorkLogMetricsCalculation {

    private static final String[] columns = {"User", "Number of Sessions"};
    private static WorkLogDaoImplementation wdi;
    private static Collection<WorkLog> currentUserWorkLogs;

    public static String getPath(Pane currentPane) {
        DirectoryChooser chooser = new DirectoryChooser();
        Stage stage = (Stage) currentPane.getScene().getWindow();
        File chosenDirectory = chooser.showDialog(stage);
        return chosenDirectory.getPath();
    }

    public static void generateReport(User user, String path) throws Exception {
        wdi = new WorkLogDaoImplementation(FormHelper.connection);
        currentUserWorkLogs = wdi.getWorkLogs(user.getUserId());

        Workbook workbook = new XSSFWorkbook();
        Sheet userSheet = workbook.createSheet(user.getFirstName() + " " + user.getLastName());

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 17);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = userSheet.createRow(0);
        for(int i = 0; i < columns.length; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        Row userInfoRow = userSheet.createRow(1);
        userInfoRow.createCell(0).setCellValue(user.getFirstName() + " " + user.getLastName());
        userInfoRow.createCell(1).setCellValue(getNumberOfSessions());

        for(int i = 0; i < columns.length; i++){
            userSheet.autoSizeColumn(i);
        }

        System.out.println(path + user.getFirstName() + " " + user.getLastName() + ".xlsx");

        FileOutputStream reportOutput = new FileOutputStream(path + "\\" + user.getFirstName() + " " + user.getLastName() + ".xlsx");
        workbook.write(reportOutput);
        reportOutput.close();
        workbook.close();
    }

    private static Integer getNumberOfSessions(){
        Integer numOfSessions = 0;
        for(WorkLog workLog : currentUserWorkLogs){
            if(workLog.getAction().equals(WorkLogAction.LOGIN)){
                numOfSessions++;
            }
        }
        return numOfSessions;
    }
}
