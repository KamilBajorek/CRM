package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.domain.PurchaseStatistics;
import com.bajorek_kalandyk.crm.service.PurchaseStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.bajorek_kalandyk.crm.enumerations.StatisticsType.getById;

@RestController
public class PurchaseStatisticsRestController
{
    private static final String ENDPOINT = "purchaseStatistics";

    @Autowired
    private PurchaseStatisticsService statisticsService;

    @RequestMapping(value = ENDPOINT + "/getByType/{typeId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<PurchaseStatistics> getByType(@PathVariable("typeId") Long type)
    {
        return new ResponseEntity<>(statisticsService.getStatisticsByType(getById(type)), HttpStatus.OK);
    }
}
