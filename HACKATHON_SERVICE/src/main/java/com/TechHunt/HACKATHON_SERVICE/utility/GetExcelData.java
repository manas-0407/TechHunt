package com.TechHunt.HACKATHON_SERVICE.utility;

import com.TechHunt.HACKATHON_SERVICE.model.RegisterDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.util.*;
import java.io.*;

@Component
public class GetExcelData {

    private final String[] headers = {"Full Name" , "Email" ,
            "Phone No" , "Address" ,
            "Skills" , "ProfileLinks"};

    public ByteArrayInputStream dataToExcel(List<RegisterDto> registerDtos) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);

        for(int i=0;i<headers.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int row_pointer = 1;
        for(RegisterDto dto : registerDtos){

            row = sheet.createRow(row_pointer);

            row.createCell(0).setCellValue(dto.getFull_name());
            row.createCell(1).setCellValue(dto.getEmail());
            row.createCell(2).setCellValue(dto.getPhone());
            row.createCell(3).setCellValue(dto.getAddress());
            row.createCell(4).setCellValue(dto.getSkills());
            row.createCell(5).setCellValue(dto.getLink());

            row_pointer ++;
        }

        workbook.write(out);

        ByteArrayInputStream ret = new ByteArrayInputStream(out.toByteArray());

        workbook.close();
        out.close();

        return ret;
    }
}
