package com.deavensoft.operationsportal.adapters.api.controller;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.Project;
import com.deavensoft.operationsportal.core.entity.Worklog;
import com.deavensoft.operationsportal.core.exception.MissingParameterException;
import com.deavensoft.operationsportal.core.usecase.EmployeeUseCase;
import com.deavensoft.operationsportal.core.usecase.ProjectUseCase;
import com.deavensoft.operationsportal.core.usecase.WorklogUseCase;
import com.deavensoft.operationsportal.core.util.ExcelExportUtil;
import com.deavensoft.operationsportal.core.util.WorklogExportDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("export")
@RequiredArgsConstructor
public class ExportController {

    private final WorklogUseCase worklogUseCase;
    private final EmployeeUseCase employeeUseCase;
    private final ProjectUseCase projectUseCase;

    @GetMapping(value = "/worklogs", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> getExportFile(HttpServletRequest request) throws IOException, IllegalAccessException {
        if (!request.getParameterMap().containsKey("employeeId") || !request.getParameterMap().containsKey("projectId")) {
            throw new MissingParameterException("You must specify at least 1 employee and at least 1 project");
        }
        List<Worklog> worklogs = worklogUseCase.listWorklogByAllCriteria(request.getParameterMap());
        List<Employee> employees = employeeUseCase.findAllEmployeesByIds(
                Arrays.stream(request.getParameterMap().get("employeeId")).map(UUID::fromString).collect(
                        Collectors.toList()));
        List<Project> projects = projectUseCase.findAllProjectsByIds(
                Arrays.stream(request.getParameterMap().get("projectId")).map(UUID::fromString).collect(
                        Collectors.toList()));
        List<WorklogExportDTO> exportDTOS = worklogUseCase.prepareWorklogsForExport(worklogs, employees, projects);

        if (exportDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        File file = ExcelExportUtil.writeExcel(exportDTOS);
        String filename = file.getName();
        byte[] bytes = ExcelExportUtil.convertFileToByteArray(file);
        Files.deleteIfExists(Path.of(file.getAbsolutePath()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(bytes);
    }

}
