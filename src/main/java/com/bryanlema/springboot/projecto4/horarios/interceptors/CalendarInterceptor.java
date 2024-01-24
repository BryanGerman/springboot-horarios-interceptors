package com.bryanlema.springboot.projecto4.horarios.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor{

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if( hour >= open && hour <= close){
            StringBuilder message = new StringBuilder("Bienvenidos al sistemas de horarios");
            message.append("atendemos de las");
            message.append(open);
            message.append("hrs. ");
            message.append("hasta las ");
            message.append(close);
            message.append("hrs. ");
            message.append("Gracias por su visita");
            request.setAttribute("message", message.toString());
            return true;
        }

        ObjectMapper om = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        StringBuilder message = new StringBuilder("Cerrado, fuera del horario de atención");
        message.append("por favor visitanos desde las: ");
        message.append(open);
        message.append("hrs. ");
        message.append("hasta las ");
        message.append(close);
        message.append("hrs. Gracias.");
        data.put("message", message.toString());
        data.put("date", new Date().toString());
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write(om.writeValueAsString(data));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    }

    

    
}
