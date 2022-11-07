package emyo.jamin.jej.crefoilo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import emyo.jamin.jej.crefoilo.service.PortfolioService;

@Controller
public class PortfolioListController {

    @Autowired
    private PortfolioService portfolioListService;

    @GetMapping(value = "/hello")
    public String portfolio() {
        portfolioListService.findPortfolioList(null);
        return "login";
    }

    @GetMapping("/deletePortfolio")
    public void deletePortfolio() {
        portfolioListService.deletePortfolio(1L);
    }

}
