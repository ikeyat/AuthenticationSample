package com.example.app.welcome;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.security.domain.service.userdetails.SampleUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes(types = {SampleForm.class})
public class HomeController {

    private static final Logger logger = LoggerFactory
            .getLogger(HomeController.class);

    @ModelAttribute(value = "smapleForm")
    public SampleForm setUpSampleForm() {
        return new SampleForm();
    }
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Locale locale, Model model, @AuthenticationPrincipal SampleUserDetails user) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("userName", user.getAccount().getUsername());

        return "welcome/home";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Model model, @AuthenticationPrincipal SampleUserDetails user) {
        model.addAttribute("userName", user.getAccount().getUsername());

        return "welcome/entry";
    }
}
