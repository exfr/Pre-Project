package web.controller;

import model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.CarServiceImp;

import java.util.List;

@Controller
@PropertySource(value = "classpath:Bundle.properties", encoding = "UTF-8")
public class CarsController {

    @Value("${title_en}")
    private String cars;

    @Value("${title_ru}")
    private String mach;

    @GetMapping("/cars")
    public String printCars(Model model, String locale) {
        List<Car> carList = new CarServiceImp().getAllCars();
        model.addAttribute("cars", carList);
        if ("en".equals(locale)) {
            model.addAttribute("title", cars);
        } else if ("ru".equals(locale)) {
            model.addAttribute("title", mach);
        } else {
            model.addAttribute("title", "Empty");
        }
        return "cars";
    }
}
