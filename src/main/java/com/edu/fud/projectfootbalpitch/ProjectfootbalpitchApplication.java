package com.edu.fud.projectfootbalpitch;

import com.edu.fud.projectfootbalpitch.dto.ReportFootball;
import com.edu.fud.projectfootbalpitch.dto.ReportProducts;
import com.edu.fud.projectfootbalpitch.dto.ReportServices;
import com.edu.fud.projectfootbalpitch.entity.SubPitchEntity;
import com.edu.fud.projectfootbalpitch.repository.SubPitchRepository;
import com.edu.fud.projectfootbalpitch.service.FootbalPitchService;
import com.edu.fud.projectfootbalpitch.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ProjectfootbalpitchApplication implements CommandLineRunner {

        public static void main(String[] args) {
            SpringApplication.run(ProjectfootbalpitchApplication.class, args);

        }
    @Autowired
    private StatisticsService statisticsService;
    @Override
    public void run(String... args) throws Exception {
        List<ReportServices> reportFootballList= statisticsService.findAllMonthOfServices();
        reportFootballList.forEach(reportFootball ->
                        System.out.println("month:"+reportFootball.getMonth()+"price"+reportFootball.getTotalPrice())
                );
    }
}
